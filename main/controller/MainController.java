package main.controller;


import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.model.CompressionService;

import java.io.File;
import java.io.IOException;

public class MainController {

    private File selectedFile;
    private final CompressionService compressionService = new CompressionService();

    @FXML
    private Text statusText;

    @FXML
    private void onChooseFile() {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            statusText.setText("Selected file: " + selectedFile.getName());
        }
    }

    @FXML
    private void onCompress() {
        if (selectedFile != null) {
            String zipFilePath = selectedFile.getParent() + "/" + selectedFile.getName().replace(".", "_compressed.");
            try {
                compressionService.compressToZip(selectedFile.getPath(), zipFilePath);
                statusText.setText("Compression to ZIP completed successfully!");
            } catch (IOException e) {
                statusText.setText("Compression failed: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            statusText.setText("Please select a file first.");
        }
    }
}
