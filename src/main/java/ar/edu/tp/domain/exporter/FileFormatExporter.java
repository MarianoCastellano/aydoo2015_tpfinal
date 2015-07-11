package ar.edu.tp.domain.exporter;

import java.io.IOException;

public interface FileFormatExporter {

	void export(long timeEjecution) throws IOException;

	String getFormat();
}