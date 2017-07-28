package com.nshusa;

import java.util.Objects;

public class Position {
	
	private final int x;
	private final int y;
	private final int z;

	public Position(int x, int y) {
		this(x, y, 0);
	}

	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position copy() {
		return new Position(this.x, this.y, this.z);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public int hashCode() {
		return Objects.hash(new Object[] { Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z) });
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof Position) {
			Position other = (Position) o;
			return this.hashCode() == other.hashCode();
		} else {
			return false;
		}
	}
}