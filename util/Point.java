//    Flood and evacuation simulator using multi-agent technology
//    Copyright (C) 2010 Alejandro Blanco and Manuel Gomar
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package util;

import java.io.Serializable;

public class Point implements Comparable<Point>, Serializable {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	private short z;
	private boolean isIn = true;
	private short w = 0;

	public Point(int x, int y) {
		this(x, y, (short) 0);
	}

	public Point(int x, int y, short z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point(int[] xyz) {
		this(xyz[0], xyz[1], (short) xyz[2]);
	}

	// Optional data constructors
	public Point(int x, int y, short z, boolean isIn) {
		this(x, y, z);
		this.isIn = isIn;
	}

	public Point(int x, int y, short z, short w) {
		this(x, y, z);
		this.w = w;
	}

	public boolean isIn() {
		return isIn;
	}

	@Override
	public int compareTo(Point o) {
		if (o.x == x && o.y == y)
			return 0;

		if (o.x > x)
			if (o.y > y)
				return 2;
			else
				return 1;
		return -1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			return (p.x == x) && (p.y == y);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "[(" + x + "," + y + ") " + z + "]";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public short getZ() {
		return z;
	}

	public short getW() {
		return w;
	}
}