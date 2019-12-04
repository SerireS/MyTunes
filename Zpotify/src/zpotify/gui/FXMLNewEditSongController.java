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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import zpotify.dal.DalException;
import zpotify.gui.model.SongModel;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLNewEditSongController implements Initializable
{
    private SongModel songModel;

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
        try {
            songModel = new SongModel();
        } catch (IOException ex) {
            Logger.getLogger(FXMLNewEditSongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void handleButtonActionSave(ActionEvent event) throws DalException
    {
        System.out.println("Vi nåede det");
        System.out.println(txt_title.getText());
        
        String title = txt_title.getText().trim();
        int length = Integer.parseInt(txt_time.getText().trim());
        String artist = txt_artist.getText().trim();
        String place = "musik/" + txt_MP3_File.getText().trim();
        
        songModel.createSong(title, length, artist, place);
        
        Stage stage = (Stage) btn_save.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonActionChoose(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter onlyMP3 = new FileChooser.ExtensionFilter("Mp3 Files","*.mp3");
        fc.getExtensionFilters().add(onlyMP3);
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null)
        {
            txt_MP3_File.setText(selectedFile.getName());
            
        }
    }

    @FXML
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

}
