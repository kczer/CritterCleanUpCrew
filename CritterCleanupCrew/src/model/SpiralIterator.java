package model;

import java.util.Iterator;

public class SpiralIterator implements Iterator<Position> {
	private Position start;
	private int direction;
	private int leftInRow;
	private int sizeOfRow;
	private int X;
	private int Y;
	private int x, y, dx, dy;
    private int t;
    private int maxI;
    private int i;
	
	public SpiralIterator(Position start){
		this.start = start;
		x=y=dx=0;
		dy=-1;
//		X=Math.min(Field.fieldWidth-start.getIntX(), start.getIntX()); //how far can we go in X
//		Y=Math.min(Field.fieldHeight-start.getIntY(), start.getIntY()); //how far can we go in Y
		X=Y=Math.min(Field.fieldHeight, Field.fieldWidth);
		t=Math.max(X,Y);
		maxI=t*t;
		
		i=0; //counter
	}
	@Override
	public boolean hasNext() {
		return i<maxI; //can we spiral?
	}

	@Override
	public Position next() {
		Position toReturn=null;
		boolean toBreak =false;
		while(!toBreak){
			if ((-X/2 <= x) && (x <= X/2) && (-Y/2 <= y) && (y <= Y/2)) {
				toReturn = new Position(start.getIntX()+x,start.getIntY()+y);
				if (checkLimits(toReturn)){
					toBreak=true; //to get out of the loop
				}
			}
	
			if( (x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x == 1-y))) {
				t=dx; dx=-dy; dy=t;
			}   
			x+=dx; y+=dy;
			i++;
		}
		return toReturn;
	}

	/**
	 * Checks if the position is within game limits
	 */
	private boolean checkLimits(Position toReturn) {
		if (toReturn.getIntX()>=Field.fieldWidth)
			return false;
		if (toReturn.getIntX()<0)
			return false;
		if (toReturn.getIntY()>=Field.fieldHeight)
			return false;
		if (toReturn.getIntY()<0)
			return false;
		return true;
	}
	@Override
	public void remove() {
		//nothing to remove ever
	}
	
//	public static void main(String[] args){
//		Iterator<Position> i = new SpiralIterator(new Position(15,15));
//		
//		while (i.hasNext()){
//			System.out.println(i.next());
//		}
//	}

}
