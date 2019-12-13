/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zpotify.be.Playlist;
import zpotify.gui.model.PlaylistModel;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLEditPlaylistController implements Initializable {
    
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
void ApplyImportantData(PlaylistModel playlistModel, FXMLDocumentController controller, Playlist selectedPlaylist) {
        this.playlistModel = playlistModel;
        this.controller = controller;
        this.selectedPlaylist = selectedPlaylist;
    }
    @FXML
    private void handleButtonActionCancel(ActionEvent event) 
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonActionEdit(ActionEvent event) throws SQLException {
        String title = txt_playlistName.getText().trim();
        int id = this.selectedPlaylist.getPlaylistId();
        Stage stage = (Stage) btn_edit.getScene().getWindow();
       // Tester om der er noget i title felt, sætter en rød border for at indikere her mangles noget
       // Det er først muligt at lukke dialogen ned når der er noget i feltet
        if(title.length() == 0)
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
