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
import zpotify.dal.DalException;
import zpotify.gui.model.SongModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLNewEditSongController implements Initializable
{
    private SongModel songModel;
    private FXMLDocumentController controller;
            
    private String path;

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
    }
    // Denne metode sikre vi har fat i den samme SongModel og Controller hele tiden.
    void ApplyImportantData(SongModel songModel, FXMLDocumentController controller) {
        this.songModel = songModel;
        this.controller = controller;
    }

    @FXML
    private void handleButtonActionSave(ActionEvent event) throws DalException
    {
        System.out.println("Vi nåede det");
        System.out.println(txt_title.getText());
        System.out.println(txt_MP3_File.getText());
        
        String title = txt_title.getText().trim();
//        int length = Integer.parseInt(txt_time.getText().trim());
//        String artist = txt_artist.getText().trim();
        String place = "musik/" + txt_MP3_File.getText().trim();
        
        Stage stage = (Stage) btn_save.getScene().getWindow();
       // Tester om der er noget i title felt, sætter en rød border for at indikere her mangles noget
       // Det er først muligt at lukke dialogen ned når der er noget i feltet
        if(title.length() == 0){
            Border warning = new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
            
            txt_title.setBorder(warning);
        } else{
        this.songModel.createSong(title, place);
        stage.close();
        }
    }

    @FXML
    private void handleButtonActionChoose(ActionEvent event)

            // Chose button to new song function. Chooses a file limited to mp3 only and getting the path of the song.
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter onlyMP3 = new FileChooser.ExtensionFilter("Mp3 Files","*.mp3");
        fc.getExtensionFilters().add(onlyMP3);
        fc.setInitialDirectory(new File("musik"));
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
