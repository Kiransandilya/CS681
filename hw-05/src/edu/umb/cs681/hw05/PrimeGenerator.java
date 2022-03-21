package edu.umb.cs681.hw05;
import java.util.LinkedList;
public class PrimeGenerator {
	protected long from, to;
	protected LinkedList<Long> primes = new LinkedList<Long>();
	public PrimeGenerator(long from, long to) {
		if(from >= 1 && to > from) {
			this.from = from;
			this.to = to;
		}else {
			throw new RuntimeException("Check input values. from must be greater than 1 and to must be greater than from." + from + " to=" + to);
		}
	}
	public LinkedList<Long> getPrimeNumbers() { 
		return primes; 
	}
	protected boolean isPrime(long p) {
		// 1 and below numbers are not prime
        if (p <= 1) {
            return false;
        }
 
        // 2 is prime because it has only 2 factors 1 and itself
        else if (p == 2) {
            return true;
        }
 
        // all even numbers are not prime except 2
        else if (p % 2 == 0) {
            return false;
        }
 
        // Find if number has any other factor other than 1 and itself
        for (long i = 3; i <= (long) Math.sqrt(p); i += 2)
        {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
	}
	public void generatePrimes(){
		for (long i = from; i <= to; i++) {
			if( isPrime(i) ){ 
				primes.add(i); 
			}
		}
	}
	// public static void main(String[] args) {
	// // Single-threaded prime number generation (with generatePrimes())
	// PrimeGenerator generate = new PrimeGenerator(1, 100);
	// generate.generatePrimes();
	// generate.getPrimeNumbers().forEach( (Long primeNumber)-> System.out.print(primeNumber + ", ") );
	// System.out.println("\n" + gen.getPrimeNumbers().size() + " prime numbers are found.");
	//
	// // Single-threaded prime number generation (without using generatePrimes())
	// PrimeGenerator generate2 = new PrimeGenerator(1, 100);
	// List<Long> primes = LongStream.rangeClosed(generate2.from, generate2.to)
	// .filter( (long num)->generate2.isPrime(num) )
	// .boxed()
	// .collect(Collectors.toList());
	// primes.forEach( (Long primeNumber)-> System.out.print(primeNumber + ", ") );
	// System.out.println("\n" + primes.size() + " prime numbers are found.");
	//
	// // Single-threaded prime number generation (without using generatePrimes())
	// PrimeGenerator generator3 = new PrimeGenerator(1, 100);
	// long size = LongStream.rangeClosed(generator3.from, generator3.to)
	// .filter( (long n)->gen3.isPrime(n) )
	// .reduce( 0L, (long count, long n)->{
	// System.out.print(n + ", ");
	// return ++count;} );
	// System.out.println("\n" + size + " prime numbers are found.");
	//
	// }
}