package dillos.jenny.template.generator.api;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class Execution {

	private String generatedZipName;
	private InputStream archive;
	private File outputFolder;
	private TemplateConfig templateConfig;
	private Map<String, String> params;

	public InputStream getArchive() {
		return archive;
	}

	public void setArchive(InputStream archive) {
		this.archive = archive;
	}

	public File getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;
	}

	public TemplateConfig getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(TemplateConfig templateConfig) {
		this.templateConfig = templateConfig;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getGeneratedZipName() {
		return generatedZipName;
	}

	public void setGeneratedZipName(String generatedZipName) {
		this.generatedZipName = generatedZipName;
	}

}
