/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLNewEditSongController implements Initializable
{

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_choose;
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField txt_title;
    @FXML
    private TextField txt_artist;
    @FXML
    private TextField txt_category;
    @FXML
    private TextField txt_time;
    @FXML
    private TextField txt_MP3_File;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleButtonActionSave(ActionEvent event)
    {
        String title = txt_title.getText().trim();
        String artist = txt_artist.getText().trim();
        int length = Integer.parseInt(txt_time.getText().trim());
        String place = txt_MP3_File.getText().trim();
            
    }

    @FXML
    private void handleButtonActionChoose(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null)
        {
            txt_MP3_File.setText(selectedFile.getName());
            selectedFile.getAbsoluteFile();
        }
    }

    @FXML
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

}
