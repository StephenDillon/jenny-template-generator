package dillos.jenny.template.generator.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import dillos.jenny.template.generator.api.Execution;
import dillos.jenny.template.generator.api.Generator;
import net.lingala.zip4j.ZipFile;

public class GeneratorImpl implements Generator {

	private static final String EXTRACTED = "extracted";

	@Override
	public File generate(Execution execution) {
		File workingLocation = new File(execution.getOutputFolder(), EXTRACTED);
		workingLocation.mkdirs();

		ExtractTemplateUtil.extractTemplate(execution.getArchive(), workingLocation);
		// TODO Update the project files

		File archive = generateZipFile(execution, workingLocation);
		FileUtils.deleteQuietly(workingLocation);
		return archive;
	}

	private File generateZipFile(Execution execution, File workingLocation) {
		String archiveName = execution.getGeneratedZipName();
		if (!archiveName.endsWith(".zip")) {
			archiveName += ".zip";
		}

		File zip = new File(execution.getOutputFolder(), archiveName);
		try {
			ZipFile zipFile = new ZipFile(zip);
			for (File file : workingLocation.listFiles()) {
				if (file.isDirectory()) {
					zipFile.addFolder(file);
				} else {
					zipFile.addFile(file);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return zip;
	}

}
