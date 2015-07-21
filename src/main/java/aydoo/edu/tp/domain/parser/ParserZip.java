package aydoo.edu.tp.domain.parser;

import java.util.List;

import aydoo.edu.tp.domain.Trip;

public interface ParserZip {

	List<Trip> parse(String path) throws Exception;
}