package ar.edu.tp.domain.processor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.edu.tp.exception.DirectoryNotFoundException;

public class FileManager {

	private static final String ERROR_DIRECTORY_NOT_FOUND = "Error: no se encuentra el directorio";
	private static final String REGEX = "/";
	private static final String SUFFIX_ZIP = ".zip";
	private String folder;

	public FileManager(String folder) {
		this.folder = folder;
	}

	public void validateFolder() throws DirectoryNotFoundException {
		File file = new File(folder);
		if (!file.exists()) {
			throw new DirectoryNotFoundException(ERROR_DIRECTORY_NOT_FOUND);
		}
	}

	public List<String> findPaths() {
		List<String> paths = new ArrayList<String>();
		File file = new File(folder);
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			File eachFile = listFiles[i];
			if (eachFile.getName().endsWith(SUFFIX_ZIP)) {
				paths.add(eachFile.getPath());
			}
		}
		return paths;
	}

	public String extractNameFromZipFile(String file) {
		int length = file.length();
		return file.substring(0, length - 4);
	}

	public String extractNameFromFolder(String folder) {
		String[] file = folder.split(REGEX);
		return file[file.length - 1];
	}
}