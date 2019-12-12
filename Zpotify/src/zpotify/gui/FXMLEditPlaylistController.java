/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import zpotify.gui.model.PlaylistModel;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLEditPlaylistController implements Initializable {
    
    private PlaylistModel playlistModel;
    private FXMLDocumentController controller;

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
void ApplyImportantData(PlaylistModel playlistModel, FXMLDocumentController controller) {
        this.playlistModel = playlistModel;
        this.controller = controller;
    }
    @FXML
    private void handleButtonActionCancel(ActionEvent event) 
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonActionEdit(ActionEvent event) {
    }
    
}
