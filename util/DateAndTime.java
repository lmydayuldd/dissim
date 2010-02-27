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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateAndTime implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private GregorianCalendar g;

	public DateAndTime(int year, int month, int dayOfMonth, int hourOfDay,
			int minute) {
		g = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, 0);
	}

	public void updateTime(int minutes) {
		g.add(Calendar.MINUTE, minutes);
	}

	@Override
	public String toString() {
		// dateTime (AAAA-MM-DDThh:mm:ssZ)
		String month = "";
		String day = "";
		String hour = "";
		String minute = "";
		int m = g.get(Calendar.MONTH);
		int da = g.get(Calendar.DAY_OF_MONTH);
		int h = g.get(Calendar.HOUR_OF_DAY);
		int mm = g.get(Calendar.MINUTE);
		if (m < 10) {
			month = "0" + m;
		} else {
			month = "" + m;
		}
		if (da < 10) {
			day = "0" + da;
		} else {
			day = "" + da;
		}
		if (h < 10) {
			hour = "0" + h;
		} else {
			hour = "" + h;
		}
		if (mm < 10) {
			minute = "0" + mm;
		} else {
			minute = "" + mm;
		}
		return g.get(Calendar.YEAR) + "-" + month + "-" + day + "T" + hour
				+ ":" + minute + ":00";
	}

}