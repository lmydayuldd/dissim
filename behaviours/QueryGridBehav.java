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

package behaviours;

import agents.EnvironmentAgent;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import util.HexagonalGrid;
import util.Point;
import util.jcoord.LatLng;

/**
 * {@link Behaviour} that receives querys about the {@link HexagonalGrid} of an
 * {@link EnvironmentAgent}. It can return the elevation of some grid
 * coordinates, or convert from geographical coordinates to grid coordinates and
 * viceversa.
 * 
 * @author Alejandro Blanco, Manuel Gomar
 * 
 */
@SuppressWarnings("serial")
public class QueryGridBehav extends CyclicBehaviour {

	/**
	 * Means the other agent is asking for the elevation of a tile
	 */
	public static final String ELEVATION = "elev";
	/**
	 * Means the other agent is asking for converting a geographical coordinate
	 * into a grid coordinate
	 */
	public static final String COORD_TO_TILE = "ctt";
	/**
	 * Means the other agent is asking for converting a grid coordinate into a
	 * geographical coordinate
	 */
	public static final String TILE_TO_COORD = "ttc";

	private HexagonalGrid grid;

	/**
	 * {@link QueryGridBehav} constructor
	 * 
	 * @param agt
	 *            An {@link EnvironmentAgent}
	 * @param grid
	 *            {@link HexagonalGrid}
	 */
	public QueryGridBehav(Agent agt, HexagonalGrid grid) {
		super(agt);
		this.grid = grid;
	}

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchConversationId("query-grid");
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			// Mensaje recibido, hay que procesarlo
			String pos = msg.getContent();
			String[] data = pos.split(" ");
			String content = "";

			if (data[0].equals(ELEVATION)) {
				content = Short.toString(grid.getValue(Integer
						.parseInt(data[1]), Integer.parseInt(data[2])));
			} else if (data[0].equals(COORD_TO_TILE)) {
				Point p = grid.coordToTile(new LatLng(Double
						.parseDouble(data[1]), Double.parseDouble(data[2])));
				content = Integer.toString(p.getCol()) + " "
						+ Integer.toString(p.getRow());
			} else if (data[0].equals(TILE_TO_COORD)) {
				LatLng coord = grid.tileToCoord(new Point(Integer
						.parseInt(data[1]), Integer.parseInt(data[2])));
				content = Double.toString(coord.getLat()) + " "
						+ Double.toString(coord.getLng());
			}

			ACLMessage reply = msg.createReply();
			reply.setPerformative(ACLMessage.INFORM);
			reply.setContent(content);
			myAgent.send(reply);
		} else {
			block();
		}
	}
}