package ar.edu.tp.domain.processor;

import java.io.File;

public interface StatisticalProcessorStrategy {

	void processStatistics(String folderInput,File folderOutput) throws Exception;
}