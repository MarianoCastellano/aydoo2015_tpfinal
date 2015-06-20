package ar.edu.tp.domain.parser;

import java.util.List;

import ar.edu.tp.domain.Travel;

public interface ParserZip {

	List<Travel> parse() throws Exception;
}