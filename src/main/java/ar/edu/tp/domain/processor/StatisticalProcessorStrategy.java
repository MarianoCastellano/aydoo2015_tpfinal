package ar.edu.tp.domain.processor;

import java.io.File;

public interface StatisticalProcessorStrategy {

	void processStatistics(String folder,File folderOutput) throws Exception;
}