package ar.edu.tp.domain.parser;

import java.util.List;

import ar.edu.tp.domain.Trip;

public interface ParserZip {

	List<Trip> parse() throws Exception;
}