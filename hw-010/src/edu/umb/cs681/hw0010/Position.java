package edu.umb.cs681.hw0010;

import java.util.*;
public final class Position implements Runnable{
	private final double latitude,longitude,altitude;
	
	public Position(double longitude,double latitude,double altitude)
	{
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
	}
	
	public void run(){  
		Position pos=new Position(this.longitude,this.latitude,this.altitude);
		Aircraft airCraft=new Aircraft(pos);
		airCraft.setPosition(pos);
		System.out.println("Position : "+airCraft.getPosition());  
	}  
  
	public static void main(String args[]){  
	Runnable r1 = new Position(52.8,75.2,6.32);
Thread th1 = new Thread(r1, "My new thread");    	
	th1.start();  
	}  
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public double getAltitude()
	{
		return altitude;
	}
	
	
	public String toString(){
		return latitude + "," + longitude + "," + altitude;
	}
	
	public boolean equals( Position anotherPosition ){
	if( this.toString().equals(anotherPosition.toString()) ){
		return true; 
		}
	else{ 
		return false; 
		} 
	}	
	
	ArrayList<Double> getCoordinate(){
		ArrayList<Double> coOrdinates=new ArrayList<>();
		coOrdinates.add(this.latitude);
		coOrdinates.add(this.longitude);
		coOrdinates.add(this.altitude);
		return coOrdinates;
	}
	Position changeLat(double newLat){
		return new Position(this.longitude,newLat , this.altitude); 
		
		}
		
	Position changeLong(double newLong){
return new Position(newLong, this.latitude, this.altitude); }

Position changeAlt(double newAlt){
return new Position(this.longitude, this.latitude, newAlt); }

	
	
	
}
class Aircraft {
	private Position position; 
	public Aircraft(Position pos){
		position = pos; 
		}
	public void setPosition(Position pos){
	position = pos;  
	 }
	public Position getPosition(){
	return position;
	} 
}



