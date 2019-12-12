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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import zpotify.dal.DalException;
import zpotify.gui.model.PlaylistModel;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLNewEditPlaylistController implements Initializable
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
    
    // Denne metode sikre vi har fat i den samme PlaylistModel og Controller hele tiden.
    void ApplyImportantData(PlaylistModel playlistModel, FXMLDocumentController controller) {
        this.playlistModel = playlistModel;
        this.controller = controller;
    }

    @FXML
    private void handleButtonActionSave(ActionEvent event) throws DalException
    {
        String playlistName = txt_playlistName.getText().trim();
        Stage stage = (Stage) btn_save.getScene().getWindow();
       // Tester om der er noget i playlistName felt, sætter en rød border for at indikere her mangles noget
       // Det er først muligt at lukke dialogen ned når der er noget i feltet
        if(playlistName.length() == 0)
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
