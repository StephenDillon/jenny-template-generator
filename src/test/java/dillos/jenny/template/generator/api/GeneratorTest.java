package dillos.jenny.template.generator.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import dillos.jenny.template.generator.impl.ExtractTemplateUtil;
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
		File archive = generator.generate(setupExecution());

		assertTrue(archive.exists());

		File extracted = new File(outputLocation, "extracted-archive");
		ExtractTemplateUtil.extractTemplate(new FileInputStream(archive), extracted);

		System.out.println(extracted.getAbsolutePath());
		File settingsFile = new File(extracted, "settings.gradle");
		assertTrue(settingsFile.exists());
		assertEquals("rootProject.name = 'generator-test'", FileUtils.readFileToString(settingsFile, Charset.defaultCharset()).trim());
	}

	private Execution setupExecution() throws IOException {
		Execution execution = new Execution();
		execution.setGeneratedZipName("archive");
		execution.setArchive(new FileInputStream(archive));
		execution.setOutputFolder(outputLocation);
		execution.setParams(new HashMap<>());
		execution.getParams().put("PROJECT_NAME", "generator-test");
		execution.getParams().put("PROJECT_NAME_DOTTED", "generator.test");
		execution.setTemplateConfig(readTemplateConfig());
		return execution;
	}

	private TemplateConfig readTemplateConfig() throws IOException {
		return gson.fromJson(new FileReader(templateConfig), TemplateConfig.class);
	}

}
