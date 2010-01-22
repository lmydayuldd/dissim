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

package behaviours.flood;

import util.HexagonalGrid;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RegisterFloodTileBehav extends CyclicBehaviour {

	private static final long serialVersionUID = 5939510314795911200L;

	private HexagonalGrid grid;

	public RegisterFloodTileBehav(HexagonalGrid grid) {
		this.grid = grid;
	}

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.and(MessageTemplate
				.MatchConversationId("register-flood"), MessageTemplate
				.MatchPerformative(ACLMessage.CFP));
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			// Mensaje CFP recibido, hay que procesarlo
			String pos = msg.getContent();
			String[] data = pos.split(" ");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			double water = Double.parseDouble(data[2]);
			double value = Double.parseDouble(data[3]);
			double gridValue = grid.getValue(x, y);

			ACLMessage reply = msg.createReply();
			if (value == gridValue) {
				grid.increaseValue(x, y, water);
				reply.setPerformative(ACLMessage.CONFIRM);
			} else {
				reply.setPerformative(ACLMessage.DISCONFIRM);
				reply.setContent(Double.toString(gridValue));
			}
			myAgent.send(reply);
		} else {
			block();
		}
	}
}
