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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Peter
 */
public class FXMLNewEditSongController implements Initializable {

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_choose;
    @FXML
    private Button btn_cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonActionSave(ActionEvent event) 
    {
    }

    @FXML
    private void handleButtonActionChoose(ActionEvent event) 
    {
    }

    @FXML
    private void handleButtonActionCancel(ActionEvent event) 
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
    
}
