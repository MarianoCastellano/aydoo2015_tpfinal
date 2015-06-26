package ar.edu.tp.domain;

import java.io.IOException;

public interface FileFormatExporter {

	void export() throws IOException;

	String getFormat();
}