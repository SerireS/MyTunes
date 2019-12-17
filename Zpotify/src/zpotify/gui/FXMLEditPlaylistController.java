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
import javafx.stage.Stage;
import zpotify.be.Playlist;
import zpotify.gui.model.PlaylistModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Den Gode Gruppe
 */
public class FXMLEditPlaylistController implements Initializable
{

    private PlaylistModel playlistModel;
    private FXMLDocumentController controller;
    private Playlist selectedPlaylist;

    @FXML
    private TextField txt_playlistName;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    //This method makes sure that we get the correct data object when editing a playlist
    void ApplyImportantData(PlaylistModel playlistModel, FXMLDocumentController controller, Playlist selectedPlaylist)
    {
        this.playlistModel = playlistModel;
        this.controller = controller;
        this.selectedPlaylist = selectedPlaylist;
    }

    //This method cancels the editing of a playlist
    @FXML
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    //This method handles the editing part, calling the updatePlaylist method in the model
    @FXML
    private void handleButtonActionEdit(ActionEvent event)
    {
        String title = txt_playlistName.getText().trim();
        int id = this.selectedPlaylist.getPlaylistId();
        Stage stage = (Stage) btn_edit.getScene().getWindow();
        // Tests if field is empty, if it is. Sets border to red
        // It is only possible to close the window if there is anything in the field
        if (title.length() == 0)
        {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));

            txt_playlistName.setBorder(warning);
        } else
        {
            this.playlistModel.updatePlaylist(title, id);
            stage.close();
        }
    }

}
