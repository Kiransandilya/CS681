package edu.umb.cs681.hw0013;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2{
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition;
	private Condition belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2 account;
	private int acc_no;
	public ThreadSafeBankAccount2(int acc_no){
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
		this.acc_no = acc_no;
	}

	public int getAccountNumber()
	{
		return this.acc_no;
	}

	public ReentrantLock getLock()
	{
		return this.lock;
	}

	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);

			balance += amount;
			sufficientFundsCondition.signalAll();
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
		}finally{
			lock.unlock();
		}
	}

	public void withdraw(double amount) throws InterruptedException{
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				// Wait for the balance to exceed 0
				sufficientFundsCondition.await(); 
			}
			if(this.balance > amount){
					this.balance -= amount;
					belowUpperLimitFundsCondition.signalAll();
					System.out.println(Thread.currentThread().getId() + " (w): new balance: " + balance);
			} else {
				System.out.println(Thread.currentThread().getId() + " : Insufficient balance to withdraw");
			}

		}finally{
			lock.unlock();
		}
	}

	public void transfer(ThreadSafeBankAccount2 source,ThreadSafeBankAccount2 destination, double amount) throws InterruptedException
	{
		if(source.getAccountNumber() < destination.getAccountNumber())
		{
			source.getLock().lock();
			destination.getLock().lock();
			try{
				if( this.balance < amount )
				{
					throw new InterruptedException("Balance insufficient");
				}
				else{
					this.withdraw(amount);
					destination.deposit(amount);
				}
			}
			finally{
				destination.getLock().unlock();
				source.getLock().unlock();
			}
		}
		else
		{
			destination.getLock().lock();
			source.getLock().lock();
			try{
				if( this.balance < amount )
				{
					throw new InterruptedException("Balance insufficient");
				}
				else{
					this.withdraw(amount);
					destination.deposit(amount);
				}
			}
			finally{
				source.getLock().unlock();
				destination.getLock().unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException{
		ThreadSafeBankAccount2 account1 = new ThreadSafeBankAccount2(9850363);
		ThreadSafeBankAccount2 account2 = new ThreadSafeBankAccount2(9850364);

		Thread t1 = null;
		for(int i = 0; i < 5; i++){
			t1 = new Thread( account1.new DepositRunnable() );
			t1.start();
		}
		Thread t2 =	new Thread( account1.new WithdrawRunnable() );
		t2.start();
		try{
			Thread.sleep(5000);
		}catch (InterruptedException ie) {}
		t1.interrupt();
		t2.interrupt();

		System.out.println("\nThis is transfer");
		account1.transfer(account1,account2,100);
		System.out.println("amount transfered");
	}

	private class DepositRunnable implements Runnable{

		public void run(){
			try{
				for(int i = 0; i < 10; i++){
					account.deposit(400);
					Thread.sleep(4);
				}
			}
			catch(InterruptedException exception){}
		}
	}

	private class WithdrawRunnable implements Runnable{

		public void run(){
			int n = 10;
			try{
				for(int i = 0; i < n; i++){
					account.withdraw(600);
					Thread.sleep(4);
				}
			}
			catch(InterruptedException exception){}
		}
	}
}