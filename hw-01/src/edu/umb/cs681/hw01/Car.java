package edu.umb.cs681.hw01;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator; 
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class Car {
    private String carMake;
    private int car_Mileage;
    private int carYear;
    private float carPrice;
    private int dominationCount;
    public Car(String make, int year, int mileage, float price, int dominationCount) {
		this.carMake = make;
		this.car_Mileage = year;
		this.carYear = mileage;
		this.carPrice = price;
		this.dominationCount = dominationCount;
    }
    public int getDomCount() {
    	return this.dominationCount; 
    }
    public String getMake() {
    	return this.carMake; 
    }
    public int getYear() {
    	return this.carYear; 
    }
    public float getPrice() {
    	return this.carPrice; 
    }
    public int getMileage() {
    	return this.car_Mileage; 
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		List<Car> carsList = new ArrayList();
		carsList.add(new Car("Lykn", 2019, 3, 1.0E7F, 1)); 
		carsList.add(new Car("Venom", 2018, 4, 2.0E7F, 3));
		carsList.add(new Car("Buggati", 2020, 2, 1.5E7F, 2)); 
		carsList.add(new Car("Mercedes", 2016, 12, 1000000.0F, 4));
		System.out.println("Ordered by Mileage:");
		PrintStream ps = System.out; 
		Objects.requireNonNull(ps); 
		List<Car> orderedByMileage = (List)carsList.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
		orderedByMileage.forEach(ps::println);
		System.out.println("Ordered by Make:");
		List<Car> orderedByMake = (List)carsList.stream().sorted(Comparator.comparing(Car::getMake)).collect(Collectors.toList());
		orderedByMake.forEach(ps::println);
		System.out.println("Ordered by Domination Count:");
		List<Car> orderedByDomCount = (List)carsList.stream().sorted(Comparator.comparingInt(Car::getDomCount)).collect(Collectors.toList()); 
		orderedByDomCount.forEach(ps::println);
		System.out.println("Ordered by Model:");
		List<Car> orderedByPrice = (List)carsList.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
		orderedByPrice.forEach(ps::println);
	} 
   }