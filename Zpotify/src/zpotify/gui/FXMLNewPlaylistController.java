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
import zpotify.dal.DalException;
import zpotify.gui.model.PlaylistModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Den Gode Gruppe
 */
public class FXMLNewPlaylistController implements Initializable
{

    private PlaylistModel playlistModel;
    private FXMLDocumentController controller;

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField txt_playlistName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    //This method makes sure that we get the correct data object when Creating a playlist
    void ApplyImportantData(PlaylistModel playlistModel, FXMLDocumentController controller)
    {
        this.playlistModel = playlistModel;
        this.controller = controller;
    }

    //This method handles the creating of a new playlist. It calls the method createPlaylist in the model class.
    @FXML
    private void handleButtonActionSave(ActionEvent event) throws DalException
    {
        String playlistName = txt_playlistName.getText().trim();
        Stage stage = (Stage) btn_save.getScene().getWindow();
        // Tests if field is empty, if it is. Sets border to red
        // It is only possible to close the window if there is anything in the field
        if (playlistName.length() == 0)
        {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
            txt_playlistName.setBorder(warning);
        } else
        {
            this.playlistModel.createPlaylist(playlistName);
            stage.close();
        }
    }

    @FXML
    //closes stage
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

}
