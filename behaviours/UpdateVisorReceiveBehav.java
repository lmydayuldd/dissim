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

import gui.VisorFrame;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import util.HexagonalGrid;

@SuppressWarnings("serial")
public class UpdateVisorReceiveBehav extends CyclicBehaviour {

	private VisorFrame visor;

	public UpdateVisorReceiveBehav(VisorFrame visor) {
		this.visor = visor;
	}

	@Override
	public void action() {
//		MessageTemplate mt = MessageTemplate.and(MessageTemplate
//				.MatchConversationId("update-visor"), MessageTemplate
//				.MatchPerformative(ACLMessage.INFORM));
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			// Mensaje recibido, hay que procesarlo
			try {
				HexagonalGrid grid = (HexagonalGrid) msg.getContentObject();
				visor.update(grid);
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		} else {
			block();
		}
	}

}
