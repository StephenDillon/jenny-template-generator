package dillos.jenny.template.generator.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;

import dillos.jenny.template.generator.api.Execution;
import dillos.jenny.template.generator.api.Generator;
import dillos.jenny.template.generator.api.TemplateConfig;
import dillos.jenny.template.generator.api.TextReplace;
import net.lingala.zip4j.ZipFile;

public class GeneratorImpl implements Generator {

	private static final String EXTRACTED = "extracted";

	@Override
	public File generate(Execution execution) {
		File workingLocation = new File(execution.getOutputFolder(), EXTRACTED);
		workingLocation.mkdirs();
		try {

			ExtractTemplateUtil.extractTemplate(execution.getArchive(), workingLocation);
			updateProjectFiles(execution, workingLocation);

			File archive = generateZipFile(execution, workingLocation);
			FileUtils.deleteQuietly(workingLocation);
			return archive;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void updateProjectFiles(Execution execution, File workingLocation) throws Exception {
		TemplateConfig config = execution.getTemplateConfig();
		for (TextReplace textReplace : config.getTextReplacements()) {
			TextReplacementService.replaceText(workingLocation, execution, textReplace);
		}
	}

	private File generateZipFile(Execution execution, File workingLocation) throws Exception {
		String archiveName = execution.getGeneratedZipName();
		if (!archiveName.endsWith(".zip")) {
			archiveName += ".zip";
		}

		File zip = new File(execution.getOutputFolder(), archiveName);

		ZipFile zipFile = new ZipFile(zip);
		for (File file : workingLocation.listFiles()) {
			if (file.isDirectory()) {
				zipFile.addFolder(file);
			} else {
				zipFile.addFile(file);
			}
		}

		return zip;
	}

}
