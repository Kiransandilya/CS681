package edu.umb.cs681.hw0016;

import java.util.ArrayList;

public class Client {
	public static void main(String args[])
	{
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car1 = new Car("Prius");
		Car car2 = new Car("Escape");
		Car car3 = new Car("ModelS");
		Car car4 = new Car("CX-5");
		
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		
		int carMakerNum = cars.stream()
				.parallel()
				.map( (Car car)-> car.getMaker() )
				.reduce(0,
				(result,carMaker)-> ++result,
				(finalResult,intermediateResult)->finalResult + intermediateResult);
		System.out.println("No of car makers are : " + carMakerNum);

		
	}
}