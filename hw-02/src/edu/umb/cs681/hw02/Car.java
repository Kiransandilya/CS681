package edu.umb.cs681.hw02;
import java.util.ArrayList; import java.util.List;
public class Car {
	private String carMake;
	private String carModel;
    private int carMileage;
    private int carYear;
    private int carPrice;
    private int dominationCount;
    public Car(String make, String model, int year, int mileage, int price, int dominationCount) {
		this.carMake = make;
		this.carModel = model;
		this.carMileage = year;
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
    public String getModel() {
    	return this.carModel; 
    }
    public int getYear() {
    	return this.carYear; 
    }
    public int getPrice() {
    	return this.carPrice; 
    }
    public int getMileage() {
    	return this.carMileage; 
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
    	List<Car> carsList = new ArrayList();
    	carsList.add(new Car("Lykn", "Hypersport", 2019, 3, 10000000, 1)); 
    	carsList.add(new Car("Venom", "GT", 2018, 4, 20000000, 3));
    	carsList.add(new Car("Buggati", "Vyron", 2020, 2, 15000000, 2));
    	carsList.add(new Car("Mercedes", "AMG", 2016, 12, 100000, 4));
		int expensive = carsList.stream().map((Car car) ->car.getPrice()).reduce(0, (result,price)->result > price ? result : price); 
		System.out.println("Price of most expensive car is $"+expensive);
		int cheapest= carsList.stream().map((Car car) ->car.getPrice()).reduce(1000000000, (result, price)->price>result ? result :price);
		System.out.println("Price of cheapest car $"+cheapest);
		int average_cost_of_all_cars = carsList.stream().map((Car car) ->car.getPrice()).reduce(0, (result,price) -> result+price, (finalResult,intermediateResult) -> finalResult)/carsList.size();
		System.out.println("Avg price for the available cars is: " + average_cost_of_all_cars);
	}
}
