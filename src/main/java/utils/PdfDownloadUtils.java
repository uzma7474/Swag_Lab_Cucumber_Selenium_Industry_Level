
package utils;

import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * src/main/java/
└── utils/
    └── PdfDownloadUtils.java

Use this utility to:

create the download directory
delete old PDFs
wait for the download
identify the downloaded PDF
verify that the PDF exists
verify that the file is not empty
PdfDownloadUtils.java

 */


public final class PdfDownloadUtils {

	private static final Logger log = LoggerFactory.getLogger(PdfDownloadUtils.class);

	private PdfDownloadUtils() {
		// Utility class
	}

	/**
	 * Creates the directory if it does not exist.
	 */
	public static Path createDownloadDirectory(String directory) {

		try {

			Path downloadPath = Paths.get(directory);

			Files.createDirectories(downloadPath);

			log.info("PDF download directory: {}", downloadPath.toAbsolutePath());

			return downloadPath;

		} catch (IOException e) {

			log.error("Unable to create PDF download directory: {}", directory, e);

			throw new RuntimeException("Unable to create PDF download directory", e);
		}
	}

	/**
	 * Deletes existing PDF files from the download directory.
	 */
	public static void deleteExistingPdfFiles(String directory) {

		try {

			Path downloadPath = Paths.get(directory);

			if (!Files.exists(downloadPath)) {
				Files.createDirectories(downloadPath);
				return;
			}

			try (var files = Files.list(downloadPath)) {

				files.filter(path -> path.getFileName().toString().toLowerCase().endsWith(".pdf")).forEach(path -> {

					try {

						Files.deleteIfExists(path);

						log.info("Deleted existing PDF: {}", path.getFileName());

					} catch (IOException e) {

						log.warn("Unable to delete PDF: {}", path, e);
					}
				});
			}

		} catch (IOException e) {

			log.error("Unable to clean PDF download directory: {}", directory, e);

			throw new RuntimeException("Unable to clean PDF download directory", e);
		}
	}

	/**
	 * Waits until a PDF file is downloaded.
	 *
	 * @param directory download directory
	 * @param timeout   maximum wait time
	 * @return downloaded PDF path
	 */
	public static Path waitForPdfDownload(String directory, Duration timeout) {

		Path downloadPath = Paths.get(directory);

		long endTime = System.currentTimeMillis() + timeout.toMillis();

		while (System.currentTimeMillis() < endTime) {

			try {

				List<Path> pdfFiles;

				try (var files = Files.list(downloadPath)) {

					pdfFiles = files
					        .filter(Files::isRegularFile)
					        .filter(path ->
					                path.getFileName()
					                        .toString()
					                        .toLowerCase()
					                        .endsWith(".pdf"))
					        .sorted(
					                Comparator.comparingLong(
					                        (Path path) -> {
					                            try {
					                                return Files.getLastModifiedTime(path).toMillis();
					                            } catch (IOException e) {
					                                return 0L;
					                            }
					                        }
					                ).reversed()
					        )
					        .collect(Collectors.toList());
				}

				if (!pdfFiles.isEmpty()) {

					Path pdf = pdfFiles.get(0);

					if (Files.size(pdf) > 0) {

						log.info("PDF downloaded successfully: {}", pdf.toAbsolutePath());

						return pdf;
					}
				}

				Thread.sleep(500);

			} catch (IOException e) {

				log.warn("Error while checking PDF download directory", e);

			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();

				throw new RuntimeException("PDF download wait interrupted", e);
			}
		}

		throw new RuntimeException("PDF was not downloaded within " + timeout.getSeconds() + " seconds. Directory: "
				+ downloadPath.toAbsolutePath());
	}

	/**
	 * Verifies that a PDF exists and is not empty.
	 */
	public static boolean isValidPdf(Path pdfPath) {

		try {

			if (pdfPath == null || !Files.exists(pdfPath)) {

				log.error("PDF does not exist: {}", pdfPath);

				return false;
			}

			long size = Files.size(pdfPath);

			boolean valid = size > 0;

			log.info("PDF validation - File: {}, Size: {} bytes, Valid: {}", pdfPath.getFileName(), size, valid);

			return valid;

		} catch (IOException e) {

			log.error("Unable to validate PDF: {}", pdfPath, e);

			return false;
		}
	}
}
