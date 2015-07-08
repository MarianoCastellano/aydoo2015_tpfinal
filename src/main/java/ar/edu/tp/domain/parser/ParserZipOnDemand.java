package ar.edu.tp.domain.parser;

import java.io.IOException;
import java.util.List;

public class ParserZipOnDemand implements ParserZip {

	private List<String> paths;
	private ParserZipDeamon deamon;

	public ParserZipOnDemand(List<String> paths) {
		this.paths = paths;
	}

	public void parse() throws IOException {
		deamon = new ParserZipDeamon(" ");
	
			deamon.parse(paths);
			

	}

	public ParserZipDeamon getDeamon() {
		return deamon;
	}
}