package dillos.jenny.template.generator.api;

public class TextReplace {

	private String fileRegex, originalText, replacementKey;

	public String getFileRegex() {
		return fileRegex;
	}

	public void setFileRegex(String fileRegex) {
		this.fileRegex = fileRegex;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String getReplacementKey() {
		return replacementKey;
	}

	public void setReplacementKey(String replacementKey) {
		this.replacementKey = replacementKey;
	}
}
