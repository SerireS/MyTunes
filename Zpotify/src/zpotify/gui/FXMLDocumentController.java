/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Peder
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private ImageView btn_close;
    @FXML
    private ImageView btn_playpause;
    @FXML
    private ImageView button_next;
    @FXML
    private ImageView btn_previous;
    @FXML
    private ImageView btn_shuffle;
    @FXML
    private ImageView btn_stop;
    @FXML
    private ImageView btn_replay;
    @FXML
    private ImageView btn_window_mode;
    @FXML
    private TextArea txt_playlist;
    @FXML
    private TextArea txt_song_playlist;
    @FXML
    private TextArea txt_songs;
    @FXML
    private ImageView btn_move_song;
    @FXML
    private Button btn_new_playlist;
    @FXML
    private Button btn_edit_playlist;
    @FXML
    private Button btn_delete_playlist;
    @FXML
    private Button btn_delete_song_playlist;
    @FXML
    private ImageView btn_move_up;
    @FXML
    private ImageView btn_move_down;
    @FXML
    private Button btn_new_song;
    @FXML
    private Button btn_edit_song;
    @FXML
    private Button btn_delete_song;
    @FXML
    private TextField txt_search;
    
    
    private void handleButtonAction(MouseEvent event)
    {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    //closes the app
    private void close_app(javafx.scene.input.MouseEvent event)
    {
        System.exit(0);
    }

    @FXML
    //minimizes the app
    private void minimize_app(javafx.scene.input.MouseEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    private void window_mode(javafx.scene.input.MouseEvent event)
    {
    }

    @FXML
    //pause the music
    private void play_pause(javafx.scene.input.MouseEvent event)
    {
    }

    @FXML
    //skip to next song
    private void nextSong(javafx.scene.input.MouseEvent event)
    {
    }

    @FXML
    //play previous song
    private void previousSong(javafx.scene.input.MouseEvent event)
    {
    }


    @FXML
    //shuffle the songs
    private void shuffle_music(javafx.scene.input.MouseEvent event)
    {
    }

    @FXML
    //stop music
    private void stop_music(javafx.scene.input.MouseEvent event)
    {
    }

    @FXML
    //replay the song
    private void replay_music(javafx.scene.input.MouseEvent event)
    {
    }
    
}
