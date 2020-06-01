package dillos.jenny.template.generator.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExtractTemplateUtilTest {

	private File archive = new File("src/test/resources/test-template.zip");
	private File outputLocation = new File("build/extraction-test");

	@BeforeEach
	void init() throws IOException {
		outputLocation.mkdirs();
		FileUtils.deleteQuietly(outputLocation);
	}

	@Test
	void testTemplateExtraction() throws IOException {

		ExtractTemplateUtil.extractTemplate(new FileInputStream(archive), outputLocation);

		assertTrue(new File(outputLocation, "settings.gradle").exists());
	}

}
