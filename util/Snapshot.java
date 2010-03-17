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

import jade.core.AID;

import java.io.Serializable;
import java.util.Map;

import kml.KmlBase;

public class Snapshot implements Serializable {

	private static final long serialVersionUID = 1L;

	private HexagonalGrid grid;
	private DateAndTime dateTime;
	private AID envAid;
	private Map<String, Point> people;
	private String name;
	private String description;
	private KmlBase kBase;

	public Snapshot(String name, String description, AID envAid,
			HexagonalGrid grid, DateAndTime dateTime, Map<String, Point> people) {
		this.envAid = envAid;
		this.grid = grid;
		this.dateTime = dateTime;
		this.people = people;
		this.name = name;
		this.description = description;
		kBase = new KmlBase(name, grid.toString());
	}

	/**
	 * Kml to write all information
	 * @return
	 */
	public KmlBase getKml() {
		return kBase;
	}
	
	public AID getEnvAid() {
		return envAid;
	}

	public HexagonalGrid getGrid() {
		return grid;
	}

	public DateAndTime getDateTime() {
		return dateTime;
	}

	public Map<String, Point> getPeople() {
		return people;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public DateAndTime updateTime(int minutes){		
		return dateTime.updateTime(minutes);
	}

	public void writeKml() {
		String fileName = kBase.getFolder().getName();
		if(fileName!= null && fileName.length()!=0){
			KmlBase.createKmzFile(kBase.getKml(), fileName);
		}else{
			KmlBase.createKmzFile(kBase.getKml(), "UnamedKml");
		}		
		
	}
}
