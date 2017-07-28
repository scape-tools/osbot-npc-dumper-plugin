package com.nshusa;

public class IntUtils {
	
	   public static int distance(Position first, Position second) {
		      int dx = first.getX() - second.getX();
		      int dy = first.getY() - second.getY();
		      return Math.abs(dx + dy);
		   }

}
