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
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Peter
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

    // Denne metode sikre vi har fat i den samme SongModel og Controller hele tiden.
    void ApplyImportantData(SongModel songModel, FXMLDocumentController controller, Song selectedSong)
    {

        this.songModel = songModel;
        this.controller = controller;
        this.SelectedSong = selectedSong;
    }

    @FXML
    private void handleButtonActionCancel(ActionEvent event)
    {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonActionSave(ActionEvent event) throws SQLException
    {
        String title = txt_title.getText().trim();
        int id = this.SelectedSong.getId();
        Stage stage = (Stage) btn_save.getScene().getWindow();
        // Tester om der er noget i title felt, sætter en rød border for at indikere her mangles noget
        // Det er først muligt at lukke dialogen ned når der er noget i feltet
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
