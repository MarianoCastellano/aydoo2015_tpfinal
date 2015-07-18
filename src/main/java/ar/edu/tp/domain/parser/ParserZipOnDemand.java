package ar.edu.tp.domain.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.tp.domain.Trip;

public class ParserZipOnDemand implements ParserZip {

	private List<String> paths;

	public ParserZipOnDemand(List<String> paths) {
		this.paths = paths;
	}

	public List<Trip> parse() throws IOException {
		List<Trip> trips = new ArrayList<Trip>();

		for (String path : paths) {
			ParserZipDeamon deamon = new ParserZipDeamon(path);
			List<Trip> parse = deamon.parse();
			trips.addAll(parse);
		}
		return trips;
	}

}