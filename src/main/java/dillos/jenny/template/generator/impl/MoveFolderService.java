package dillos.jenny.template.generator.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import dillos.jenny.template.generator.api.Execution;
import dillos.jenny.template.generator.api.MoveFolder;

public class MoveFolderService {

	public static void moveFolder(File workingFolder, Execution execution, MoveFolder moveFolder) throws IOException {
		File move = new File(workingFolder, moveFolder.getSourceFolder());

		String replacementDir = execution.getParams().get(moveFolder.getReplacementKey()).replaceAll("-", "/");
		String path = workingFolder.getAbsolutePath() + "/" + moveFolder.getRootTarget() + "/" + replacementDir;
		new File(path).mkdirs();

		Files.move(move.toPath(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	}

}
