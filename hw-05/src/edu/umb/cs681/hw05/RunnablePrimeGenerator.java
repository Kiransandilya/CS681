package edu.umb.cs681.hw05;
public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	public void run() {
		generatePrimes();
	}
	// public static void main(String[] args) {
	// RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 2000000L);
	// Thread t1 = new Thread(gen1);
	// t1.start();
	//
	// float T1_start_time= LocalDateTime.now().getNano();
	//
	// try {
	// t1.join();
	// } catch (InterruptedException e) {}
	//
	//// gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
	//
	// float T1_end_time = LocalDateTime.now().getNano();
	//
	//
	// long len_prime = gen1.getPrimes().size();
	// System.out.println("\n" + len_prime + " prime numbers are found.");
	// System.out.println("\n" + "Time taken to execute is: " + Math.abs((T1_end_time-T1_start_time)/1000000000F) + " seconds.");
	//
	// System.out.println("\n"+"########################################################################################");
	//
	// RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(1L, 1000000L);
	// RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(1000001L,2000000L);
	// Thread t2 = new Thread(gen2);
	// Thread t3 = new Thread(gen3);
	//
	// t2.start();
	// t3.start();
	//
	// float T2_start_time = LocalDateTime.now().getNano();
	//
	// try {
	// t2.join();
	// t3.join();
	// } catch (InterruptedException e) {}
	//
	//
	// float T2_end_time= LocalDateTime.now().getNano();
	//
	// long len_prime_for_TWO_Threads = gen2.getPrimes().size() + gen3.getPrimes().size();
	// System.out.println("\n" + len_prime_for_TWO_Threads + " prime numbers are found.");
	// System.out.println("\n" + "Time taken to execute two threads is: " + Math.abs((T2_end_time - T2_start_time)/ 1000000000F) + " seconds.");
	//
	// System.out.println("\n"+"###################################################################################");
	//
	// RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(1L, 500000L);
	// RunnablePrimeGenerator gen5 = new RunnablePrimeGenerator(500001L,1000000L);
	// RunnablePrimeGenerator gen6 = new RunnablePrimeGenerator(1000001L,1500000L);
	// RunnablePrimeGenerator gen7 = new RunnablePrimeGenerator(1500001L,2000000L);
	//
	// Thread t4 = new Thread(gen4);
	// Thread t5 = new Thread(gen5);
	// Thread t6 = new Thread(gen6);
	// Thread t7 = new Thread(gen7);
	//
	// t4.start();
	// t5.start();
	// t6.start();
	// t7.start();
	//
	// float T4_start_time = LocalDateTime.now().getNano();
	//
	// try {
	// t4.join();
	// t5.join();
	// t6.join();
	// t7.join();
	// } catch (InterruptedException e) {}
	//
	//
	// float T4_end_time= LocalDateTime.now().getNano();
	//
	// long len_prime_for_four_Threads = gen4.getPrimes().size() + gen5.getPrimes().size() + gen6.getPrimes().size() + gen7.getPrimes().size();
	// System.out.println("\n" + len_prime_for_four_Threads + " prime numbers are found.");
	// System.out.println("\n" + "Time taken to execute four threads is: " + Math.abs((T4_end_time - T4_start_time)/1000000000F) + " seconds.");
	//
	// }
}