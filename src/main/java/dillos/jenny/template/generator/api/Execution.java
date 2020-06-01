package dillos.jenny.template.generator.api;

import java.io.File;
import java.util.Map;

public class Execution {

	private File archive;
	private File outputFolder;
	private TemplateConfig templateConfig;
	private Map<String, String> params;

	public File getArchive() {
		return archive;
	}

	public void setArchive(File archive) {
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

}
