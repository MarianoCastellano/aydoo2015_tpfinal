package ar.edu.tp.domain.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.tp.domain.Travel;

public class ParserZipOnDemand implements ParserZip {

	private List<String> paths;

	public ParserZipOnDemand(List<String> paths) {
		this.paths = paths;
	}

	public List<Travel> parse() throws IOException {
		List<Travel> travels = new ArrayList<Travel>();

		for (String path : paths) {
			ParserZipDeamon deamon = new ParserZipDeamon(path);
			List<Travel> parse = deamon.parse();
			travels.addAll(parse);
		}
		return travels;
	}

}