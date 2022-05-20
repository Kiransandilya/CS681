package edu.umb.cs681.hw0017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors; 
import java.util.stream.Stream;
public class CovidDataCSV implements Runnable{
    public static void main(String[] args) {
		Thread thread1 = new Thread(new CovidDataCSV());
		thread1.start();
		Thread thread2 = new Thread(new CovidDataCSV());
		thread2.start();
    }
		
		@Override
		public void run() {
			Path file_path = Paths.get("./src/Data/CovidPVI_Data.txt"); 
			try( Stream<String> d = Files.lines(file_path) ){
				List<List<String>> matrix = d.map( data -> { 
					return Stream.of( data.split(",")).map(v->v.substring(0, v.length())).collect( Collectors.toList() ); 
				}).collect(Collectors.toList());
				List<List<String>> totalPopulation = matrix.parallelStream().filter((i) -> i.get(4).contains("Massachusetts")).collect(Collectors.toList());
				List<String> suffolkDeaths = matrix.parallelStream().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
				String massachusettsDeaths = matrix.parallelStream().filter((i) -> i.get(4).contains("Massachusetts"))
						.map((n) -> n.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));
				System.out.println("\nDeaths occurred in the Suffolk county of the Massachusetts state: " + suffolkDeaths.get(7)+ " - " + Thread.currentThread().getName());
				System.out.println("\nTotal deaths in Massachusetts state : " + massachusettsDeaths+ " - " + Thread.currentThread().getName());
				System.out.println("\nAverage number of deaths in Massachusetts state : " + Integer.parseInt(massachusettsDeaths)/totalPopulation.size()+ " - " + Thread.currentThread().getName());
			}
	        catch(IOException ex) {
	        	ex.printStackTrace(); 
	        }
		}
} 
