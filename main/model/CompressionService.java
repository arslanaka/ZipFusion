package main.model;

import java.io.*;
import java.util.Objects;
import java.util.zip.*;

public class CompressionService {

    public void compressToZip(String sourceFilePath, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            File sourceFile = new File(sourceFilePath);
            compressFileToZipfile(sourceFile.getParent(), sourceFile.getName(), zipOut);
        }
    }

    private void compressFileToZipfile(String rootDir, String sourceFile, ZipOutputStream out) throws IOException {
        File fileToCompress = new File(rootDir, sourceFile);
        try (FileInputStream fis = new FileInputStream(fileToCompress)) {
            ZipEntry zipEntry = new ZipEntry(fileToCompress.getName());
            out.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                out.write(bytes, 0, length);
            }
        }
    }
}
