package edu.umb.cs681.hw04;
import java.time.LocalDateTime;
public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
    public RunnablePrimeGenerator(long from, long to) {
        super(from, to);
    }
    public void run() {
        generatePrimes();
    }
	public static void main(String[] args) { 
		//Thread 1
		RunnablePrimeGenerator generatorObj1 = new RunnablePrimeGenerator(1L, 2000000L); 
		Thread thread1 = new Thread(generatorObj1);
		thread1.start();
		float Thread1_start_time = LocalDateTime.now().getNano();
		try { 
			thread1.join();
		} 
		catch (InterruptedException e) {}
		generatorObj1.getPrimes().forEach((Long prime)->System.out.print(prime + ", ")); 
		float Thread1_end_time = LocalDateTime.now().getNano();
		long len_for_1Thread = generatorObj1.getPrimes().size();
		System.out.println("\n" + len_for_1Thread + " prime numbers are found."); System.out.println("\n" + "Time taken to execute is: " +
		Math.abs((Thread1_end_time - Thread1_start_time) / 1000000000F) + " seconds.");
		System.out.println("\n" + "################################################################################## ######");
		RunnablePrimeGenerator generatorObj2 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator generatorObj3 = new RunnablePrimeGenerator(1000001L, 2000000L);
		//Thread 2
		Thread thread2 = new Thread(generatorObj2);
		//Thread3
		Thread thread3 = new Thread(generatorObj3);
		thread2.start(); 
		thread3.start();
		float Threads2_ST = LocalDateTime.now().getNano();
		try { 
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) { }
		float Threads2_ET = LocalDateTime.now().getNano();
		long len_2Threads = generatorObj2.getPrimes().size() + generatorObj3.getPrimes().size();
		System.out.println("\n" + len_2Threads + " prime numbers are found.");
		System.out.println("\n" + "Time taken to execute two threads is: " + Math.abs((Threads2_ET - Threads2_ST) / 1000000000F) + " seconds.");
		System.out.println("\n" + "################################################################################## #");
		RunnablePrimeGenerator generatorObj4 = new RunnablePrimeGenerator(1L, 500000L);
		RunnablePrimeGenerator generatorObj5 = new RunnablePrimeGenerator(500001L, 1000000L);
		RunnablePrimeGenerator generatorObj6 = new RunnablePrimeGenerator(1000001L, 1500000L);
		RunnablePrimeGenerator generatorObj7 = new RunnablePrimeGenerator(1500001L, 2000000L);
	    Thread thread4 = new Thread(generatorObj4);
	    Thread thread5 = new Thread(generatorObj5);
	    Thread thread6 = new Thread(generatorObj6);
	    Thread thread7 = new Thread(generatorObj7);
	    thread4.start(); 
	    thread5.start(); 
	    thread6.start(); 
	    thread7.start();
		float Threads4_ST = LocalDateTime.now().getNano();
		try { 
			thread4.join();
			thread5.join(); 
			thread6.join(); 
			thread7.join();
		} catch (InterruptedException e) {}
		float Threads4_ET = LocalDateTime.now().getNano();
		long len_4Threads = generatorObj4.getPrimes().size() + generatorObj5.getPrimes().size() + generatorObj6.getPrimes().size() + generatorObj7.getPrimes().size();
		System.out.println("\n" + len_4Threads + " prime numbers are found.");
		System.out.println("\n" + "Time taken to execute four threads is: " + Math.abs((Threads4_ET - Threads4_ST) / 1000000000F) + " seconds.");
	} 
}