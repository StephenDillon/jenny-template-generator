package dillos.jenny.template.generator.api;

import java.util.List;

public class TemplateConfig {

	private List<TextReplace> textReplacements;

	private List<MoveFolder> moveFolders;

	public List<TextReplace> getTextReplacements() {
		return textReplacements;
	}

	public void setTextReplacements(List<TextReplace> textReplacements) {
		this.textReplacements = textReplacements;
	}

	public List<MoveFolder> getMoveFolders() {
		return moveFolders;
	}

	public void setMoveFolders(List<MoveFolder> moveFolders) {
		this.moveFolders = moveFolders;
	}

}
