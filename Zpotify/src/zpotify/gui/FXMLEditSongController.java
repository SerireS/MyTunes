/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zpotify.be.Song;
import zpotify.gui.model.SongModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Den Gode Gruppe
 */
public class FXMLEditSongController extends FXMLDocumentController
{

    private SongModel songModel;
    private FXMLDocumentController controller;
    private Song SelectedSong;

    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_save;
    @FXML
    private TextField txt_title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    //This method makes sure that we get the correct data object when editing a song
    void ApplyImportantData(SongModel songModel, FXMLDocumentController controller, Song selectedSong)
    {

        this.songModel = songModel;
        this.controller = controller;
        this.SelectedSong = selectedSong;
    }

    //This method cancels the editing of a playlist
    @FXML
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    //This method handles the editing part, calling the updateSong method in the model
    @FXML
    private void handleButtonActionSave(ActionEvent event)
    {
        String title = txt_title.getText().trim();
        int id = this.SelectedSong.getId();
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
            this.songModel.updateSong(title, id);
            stage.close();
        }
    }

}
