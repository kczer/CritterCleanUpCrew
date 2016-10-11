package model;

import java.text.DecimalFormat;

public class Position implements java.io.Serializable {
	
	private final double x,y; //makes position class immutable
	
	public Position(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the X coordinate in double.
	 */
	public double getX(){ //it's ridiculous to have stubs for these.
		return x;
	};
	
	/**
	 * Returns the Y coordinate in double.
	 */
	public double getY(){
		return y;
	};
	
	/**
	 * Returns X cast as an int.
	 */
	public int getIntX(){
		return (int)x;
	}
	
	/**
	 * Returns Y cast as an int.
	 */
	public int getIntY(){
		return (int)y;
	}
	
	/**
	 * Returns the distance between the two
	 * coordinates in double. Always positive.
	 */
	public double distanceTo(Position p){
		return Math.sqrt(Math.pow(p.getX()-x,2)+Math.pow(p.getY()-y,2));
	};
	
	/**
	 * 
	 * Returns the X component of unit vector
	 * pointed to the given position from "here"
	 */
	public double getUnitXComponent(Position p){
		double length = Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
		return (p.x-x)/length;
	}
	
	/**
	 * 
	 * Returns the Y component of unit vector
	 * pointed to the given position from "here"
	 */
	public double getUnitYComponent(Position p){
		double length = Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
		return (p.y-y)/length;
	}
	
	public String toString(){
		return "("+(new DecimalFormat("#.##").format(x))+','+
				(new DecimalFormat("#.##").format(y))+')';
	}
	
	/**
	 * Returns the integerized Position, where are decimal places
	 * are TRUNCATED.
	 * @return
	 */
	public Position intPosition(){
		return new Position((int)x, (int)y);
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Position))
			return false;
		else{
			Position p = (Position)o;
			return p.getX()==x&& p.getIntY()==y;
		}
	}
	
	public int hashCode(){
		return 137*(int)(x*Field.fieldWidth+y);
	}
}
