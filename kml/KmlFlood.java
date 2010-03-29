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

package kml;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import util.flood.FloodHexagonalGrid;
import util.jcoord.LatLng;
import util.jcoord.LatLngComparator;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class KmlFlood {
	/**
	 * Here are all floodLevels
	 */
	private Set<Short> altitudes;
	/**
	 * Begin time of the simulation step
	 */
	protected String beginTime = null;
	/**
	 * End time of the simulation step
	 */
	protected String endTime = null;
	// no poner : que se ralla :D
	protected final String water = "Water";
	/**
	 * Kml Folder where put all inundation info
	 */
	private Folder folder;

	public KmlFlood(Folder folder) {
		altitudes = new TreeSet<Short>();
		this.folder = folder;
	}

	public void update(short[][] oldGrid, FloodHexagonalGrid fGrid,
			String beginTime, String endTime) {
		// incs for this snapshot
		double[] incs = fGrid.getIncs();
		int tileSize = fGrid.getTileSize();
		// Now we update the time for each update call
		this.endTime = endTime;
		this.beginTime = beginTime;

		Map<Short, SortedSet<LatLng>> floodSectors = new TreeMap<Short, SortedSet<LatLng>>();
		// For each tile who has changed ever, creates hexagon
		for (int col = 0; col < fGrid.getColumns(); col++) {
			for (int row = 0; row < fGrid.getRows(); row++) {
				// Por cada casilla
				if (fGrid.isFloodBorder(col, row)) {
					// Solo si esta inundada y es borde
					short floodLevel = fGrid.getValue(col, row);
					if (altitudes.add(floodLevel)) {
						// Si no tenemos esta altitud añadimos un nuevo estilo
						// con la profundidad solo de este punto
						createWaterStyleAndColor(floodLevel, fGrid.getValue(
								col, row));
					}
					SortedSet<LatLng> floodTiles = floodSectors.get(floodLevel);
					if (floodTiles == null) {
						// Si no tenemos una lista de puntos a ese nivel de
						// inundacion
						floodTiles = new TreeSet<LatLng>(new LatLngComparator());
						floodSectors.put(floodLevel, floodTiles);
					}
					// pasamos a coordenadas
					LatLng pos = fGrid.tileToCoord(col, row);
					pos.setAltitude(floodLevel);
					floodTiles.add(pos);

					// drawWaterPolygon("HEX[" + x + "," + y + "]", grid
					// .tileToCoord(x, y), (short) Math.abs(z), incs);
				}
			}
		}
		Map<Short, List<LinkedList<LatLng>>> floods = getFloodSectors(
				floodSectors, tileSize);
		for (short key : floods.keySet()) {
			List<LinkedList<LatLng>> sectors = floods.get(key);
			for (LinkedList<LatLng> sector : sectors) {
				drawWater(sector, key, incs);
			}
		}
	}

	private Map<Short, List<LinkedList<LatLng>>> getFloodSectors(
			Map<Short, SortedSet<LatLng>> floodSectors, int tileSize) {
		Map<Short, List<LinkedList<LatLng>>> floodLand = new TreeMap<Short, List<LinkedList<LatLng>>>();
		for (short key : floodSectors.keySet()) {
			List<LinkedList<LatLng>> sectors = new ArrayList<LinkedList<LatLng>>();
			floodLand.put(key, sectors);
			for (LatLng land : floodSectors.get(key)) {
				if (sectors.isEmpty()) {
					// Necesitamos empezar por alguna
					LinkedList<LatLng> aux = new LinkedList<LatLng>();
					aux.add(land);
					sectors.add(aux);
				} else {

					setAndOrderIntoList(sectors, land, tileSize);

				}

			}
		}
		return null;
	}

	private void setAndOrderIntoList(List<LinkedList<LatLng>> sectors,
			LatLng land, int tileSize) {
		boolean fin = false;
		for (LinkedList<LatLng> sector : sectors) {
			for (LatLng pos : sector) {
				// La distancia sera pos si esta a la derecha, neg si esta a la
				// izq
				double dist = pos.distance(land);
				if (dist <= tileSize * 1.1) {
					// le damos un pequeño margen de error
					if (dist > 0) {
						sector.add(sector.indexOf(pos) + 1, land);
					} else {
						sector.add(sector.indexOf(pos), land);
					}
					fin = true;
					break;
				}
			}
			if (fin) {
				break;
			}
		}

	}

	private void drawWater(LinkedList<LatLng> sector, int floodLevel,
			double[] incs) {
		Placemark placeMark = KmlBase.newPlaceMark(folder, sector.getFirst()
				.toString());
		KmlBase.setTimeSpan(placeMark, beginTime, endTime);
		placeMark.setStyleUrl(water + floodLevel);
		KmlBase.drawPolygon(placeMark, sector, incs);
	}

	/**
	 * Creates an style for level floodLevel but with deep z
	 * 
	 * @param floodLevel
	 *            Level
	 * @param z
	 *            deep
	 */
	protected void createWaterStyleAndColor(short floodLevel, short z) {
		int blue = Color.blue.getBlue();
		// Mientras mas profunda sea el agua, mas ocuro es el azul.
		blue += z;
		// Kml uses aabbggrr "5500" le da un color azul bonito
		String abgr;
		if (blue > 255) {
			abgr = "ff" + "ff" + "55" + "00";
		} else {
			abgr = Integer.toHexString(blue) + Integer.toHexString(blue) + "55"
					+ "00";
		}
		// le doy el mismo color de azul que transparencia
		// System.out.println("Setting color: "+abgr);
		folder.createAndAddStyle().withId(water + floodLevel)
				.createAndSetPolyStyle().withColor(abgr);
		// polyStyle.setColorMode(ColorMode.NORMAL);
	}

}