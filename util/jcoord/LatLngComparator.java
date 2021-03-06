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

package util.jcoord;

import java.util.Comparator;

/**
 * {@link LatLng} {@link Comparator}
 * 
 * @author Alejandro Blanco, Manuel Gomar
 * 
 */
public class LatLngComparator implements Comparator<LatLng> {

	@Override
	public int compare(LatLng arg0, LatLng arg1) {
		return (int) arg0.distance(arg1);
	}

	@Override
	public boolean equals(Object obj) {
		LatLng l = (LatLng) obj;
		return l.equals(this);
	}

}
