package dillos.jenny.template.generator.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import dillos.jenny.template.generator.api.Execution;
import dillos.jenny.template.generator.api.TextReplace;

public class TextReplacementService {

	public static void replaceText(File workingFolder, Execution execution, TextReplace textReplace)
			throws IOException {
		Collection<File> files = FileUtils.listFiles(workingFolder, new RegexFileFilter("^(.*?)"), TrueFileFilter.TRUE);
		for (File file : files) {
			String content = FileUtils.readFileToString(file, Charset.defaultCharset());
			String updatedContext = content.replaceAll(textReplace.getOriginalText(),
					execution.getParams().get(textReplace.getReplacementKey()));

			FileUtils.write(file, updatedContext, Charset.defaultCharset());
		}
	}

}
