package osm;

import java.util.SortedSet;
import java.util.TreeSet;

import util.Logger;

public class OsmMap {
	public static final short COUNTRY = 0;
	
	private Logger streetsLogger;
	protected String continent;
	protected String name;
	protected String place;
	protected long id;
	protected SortedSet<OsmWay> ways;

	public OsmMap(long id, String continent, String name, String place) {
		this.continent = continent;
		this.name = name;
		this.place = place;
		this.id = id;
		ways = new TreeSet<OsmWay>();
	}
	
	public void addWay(OsmWay way){
		ways.add(way);
	}
}
