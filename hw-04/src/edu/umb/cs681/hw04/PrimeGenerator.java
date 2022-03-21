package edu.umb.cs681.hw04;
import java.util.LinkedList;
public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primeNumbers = new LinkedList<Long>();
    public PrimeGenerator(long from, long to){
        if(from >= 1 && to > from){
			this.from = from;
			this.to = to; 
		}else{
			throw new RuntimeException("Input values invalid. from must be >=1 and to must be > from.\n from=" + from + " to="+ to);
		} 
    }
	public LinkedList<Long> getPrimes(){ 
		return primeNumbers; 
	};
	protected boolean isEven(long n){
	    if(n%2 == 0){ return true; }
	    else{ return false; }
	}
	protected boolean isPrimeNumber(long n){
		if(n <= 1){ 
			return false; 
		}
		if( n > 2 && isEven(n) ){ 
			return false; 
		}
		for (long i = 2; i < Math.sqrt(n); i++) {  
	        if (n % i == 0) {  
	            return false;  
	        }  
	    }
		return true;
	}
	public void generatePrimes(){
	    for (long n = from; n <= to; n++) {
	    	if( isPrimeNumber(n) ){ 
	    		primeNumbers.add(n); 
	    	} 
	    }
	}

}
