package dillos.jenny.template.generator.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import dillos.jenny.template.generator.impl.GeneratorImpl;

class GeneratorTest {

	private Generator generator = new GeneratorImpl();
	private File outputLocation = new File("build/generator-test");
	private File archive = new File("src/test/resources/test-template.zip");
	private File templateConfig = new File("src/test/resources/test-template-config.json");
	private Gson gson = new Gson();

	@BeforeEach
	void init() throws IOException {
		if (outputLocation.exists()) {
			FileUtils.deleteDirectory(outputLocation);
		}
		outputLocation.mkdirs();
	}

	@Test
	void testGenerate() throws IOException {
		generator.generate(setupExecution());

		assertTrue(outputLocation.listFiles().length > 0);
	}

	private Execution setupExecution() throws IOException {
		Execution execution = new Execution();
		execution.setArchive(archive);
		execution.setOutputFolder(outputLocation);
		execution.setParams(new HashMap<>());
		execution.setTemplateConfig(readTemplateConfig());
		return execution;
	}

	private TemplateConfig readTemplateConfig() throws IOException {
		return gson.fromJson(new FileReader(templateConfig), TemplateConfig.class);
	}

}
