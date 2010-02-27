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

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import util.DateAndTime;
import util.HexagonalGrid;
import util.Snapshot;

@SuppressWarnings("serial")
public class SendUpdateBehav extends TickerBehaviour {

	private Set<AID> to;
	private HexagonalGrid grid;
	private DateAndTime dateTime;

	public SendUpdateBehav(Agent a, long period, Set<AID> to,
			HexagonalGrid grid, DateAndTime dateTime) {
		super(a, period);
		this.to = to;
		this.grid = grid;
		this.dateTime = dateTime;
	}

	@Override
	protected void onTick() {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		Iterator<AID> it = to.iterator();
		while (it.hasNext())
			msg.addReceiver(it.next());
		msg.setConversationId("update-visor");
		try {
			msg.setContentObject(new Snapshot(myAgent.getAID(), grid, dateTime));
			myAgent.send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}