package edu.umb.cs681.hw08;
import java.util.LinkedList;
// Generates prime factors of a given number (d)
// Prime factors are generated in the range of 2 and d
// from: lower bound of the range (2)
// to: upper bound of the range (d)
public class PrimeFactorizer {
	protected long q, from, to;
	protected LinkedList<Long> factors = new LinkedList<Long>();
	public PrimeFactorizer(long q){
		if(q >= 2){
			this.q = q;
			this.from = 2;
			this.to = q;
		}else{
			throw new RuntimeException("Input must be >= 2.");
		}
	}
	public long getQ() { 
		return q; 
	}
	public long getFrom(){ 
		return from; 
	}
	public long getTo(){ 
		return to; 
	}
	public LinkedList<Long> getPrimeFactors(){ 
		return factors; 
	}
	public void generatePrimeFactors() {
		long o = 2;
		while( q != 1 && o <= to ){
			if(q % o == 0) {
				factors.add(o);
				q /= o;
			}
			else {
				if(o==2)
				{ 
					o++; 
				}
				else{ 
					o += 2; 
				}
			}
		}
	}
}