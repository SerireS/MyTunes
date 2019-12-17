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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import zpotify.dal.DalException;
import zpotify.gui.model.SongModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Den Gode Gruppe
 */
public class FXMLNewSongController implements Initializable
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
    private TextField txt_MP3_File;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    //This method makes sure that we get the correct data object when Creating a song
    void ApplyImportantData(SongModel songModel, FXMLDocumentController controller)
    {
        this.songModel = songModel;
        this.controller = controller;
    }

    //This method handles the creating of a new song. It calls the method createSong in the model class.
    @FXML
    private void handleButtonActionSave(ActionEvent event) throws DalException
    {
        String title = txt_title.getText().trim();
        String place = "musik/" + txt_MP3_File.getText().trim();
        Stage stage = (Stage) btn_save.getScene().getWindow();
        // Tests if field is empty, if it is. Sets border to red
        // It is only possible to close the window if there is anything in the field
        if (title.length() == 0)
        {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));

            txt_title.setBorder(warning);
        } else
        {
            this.songModel.createSong(title, place);
            stage.close();
        }
    }

    @FXML
    private void handleButtonActionChoose(ActionEvent event)

    // Chose button to new song function. Chooses a file limited to mp3 only and getting the path of the song.
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter onlyMP3 = new FileChooser.ExtensionFilter("Mp3 Files", "*.mp3");
        fc.getExtensionFilters().add(onlyMP3);
        fc.setInitialDirectory(new File("musik"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null)
        {
            txt_MP3_File.setText(selectedFile.getName());

        }
    }

    @FXML
    //Closes the stage
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}
