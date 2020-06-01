package dillos.jenny.template.generator.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractTemplateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtractTemplateUtil.class);

	public static void extractTemplate(InputStream archive, File extractionFolder) {

		extractionFolder.mkdirs();

		try (InputStream inputStream = archive) {
			File tempFile = new File(extractionFolder.getAbsolutePath(), "extract");
			Files.copy(inputStream, tempFile.toPath());

			extractFolder(tempFile, extractionFolder);

			tempFile.delete();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public static void extractFolder(File file, File extractFolder) {
		try {
			int BUFFER = 2048;

			ZipFile zip = new ZipFile(file);

			@SuppressWarnings("unchecked")
			Enumeration<ZipEntry> zipFileEntries = (Enumeration<ZipEntry>) zip.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = zipFileEntries.nextElement();
				String currentEntry = entry.getName();

				File destFile = new File(extractFolder, currentEntry);
				// destFile = new File(newPath, destFile.getName());
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();

				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}

			}
			zip.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

}
