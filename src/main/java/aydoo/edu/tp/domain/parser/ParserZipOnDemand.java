package aydoo.edu.tp.domain.parser;

import java.io.IOException;
import java.util.List;

import aydoo.edu.tp.domain.Trip;

public class ParserZipOnDemand implements ParserZip {

	public List<Trip> parse(String path) throws IOException {
		ParserZipDaemon daemon = new ParserZipDaemon();
		return daemon.parse(path);
	}

}