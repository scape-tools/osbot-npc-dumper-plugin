package com.nshusa;

import java.util.Objects;

public class NpcSpawn {
	private int id;
	private Position position;
	private Position createdPosition;
	private String direction;
	private int radius = 0;

	public NpcSpawn(int id, Position position, int direction) {
		this.id = id;
		this.position = position;
		this.createdPosition = position.copy();
		this.direction = toDirection(direction);
	}

	public static String toDirection(int value) {
		return value > 255 && value < 1793
				? (value > 255 && value < 767 ? "WEST"
						: (value >= 767 && value <= 1281 ? "NORTH" : (value > 1281 && value < 1793 ? "EAST" : "SOUTH")))
				: "SOUTH";
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getCreatedPosition() {
		return this.createdPosition;
	}

	public void setCreatedPosition(Position createdPosition) {
		this.createdPosition = createdPosition;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int hashCode() {
		return Objects.hash(new Object[] { Integer.valueOf(this.id), this.position });
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof NpcSpawn) {
			NpcSpawn other = (NpcSpawn) o;
			return this.hashCode() == other.hashCode();
		} else {
			return false;
		}
	}
}
