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

package util.flood;

import java.util.LinkedList;
import java.util.ListIterator;

import util.Scenario;
import util.jcoord.LatLng;

public class FloodScenario extends Scenario {

	private static final long serialVersionUID = 1L;

	/**
	 * Entradas de agua
	 */
	private LinkedList<WaterSource> waterSources; // TODO Array o Linked??
	/**
	 * Determina si se representa el agua como agentes o no
	 */
	private boolean waterAgents = true;
	/**
	 * Representa los ms entre cada actualización de la posición del agua
	 */
	private long floodUpdateTime = 1000; // TODO mejorar esta idea
	/**
	 * Representa la cantidad de agua que tiene cada agente, o que se mueve
	 * entre casillas en caso de que no se agentifique el agua
	 */
	private double water = 1;

	public FloodScenario() {
		super();
		waterSources = new LinkedList<WaterSource>();
		Scenario.current = this;
	}

	public boolean addWaterSource(WaterSource ws) {
		boolean result = false;
		// Comprobamos que esté dentro del área de simulación
		LatLng coord = ws.getCoord();
		if (NW.getLat() >= coord.getLat() && NW.getLng() >= coord.getLng()
				&& SE.getLat() <= coord.getLat()
				&& SE.getLng() <= coord.getLng()) {
			result = waterSources.add(ws);
		}
		return result;
	}

	public ListIterator<WaterSource> waterSourcesIterator() {
		return waterSources.listIterator();
	}

	public int waterSourcesSize() {
		return waterSources.size();
	}

	public void setWaterAgents(boolean waterAgents) {
		this.waterAgents = waterAgents;
	}

	/**
	 * Devuelve un booleano indicando si se representa el agua como agentes o no
	 * 
	 * @return boolean waterAgents
	 */
	public boolean useWaterAgents() {
		return waterAgents;
	}

	public void setFloodUpdateTime(long floodUpdateTime) {
		this.floodUpdateTime = floodUpdateTime;
	}

	public long getFloodUpdateTime() {
		if (!waterAgents)
			return floodUpdateTime;
		else
			return -1;
	}

	public void setWater(double water) {
		this.water = water;
	}

	public double getWater() {
		return water;
	}

}