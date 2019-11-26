/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import zpotify.gui.model.SongModel;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import zpotify.be.Song;

/**
 * @author Peder
 */
public class FXMLDocumentController implements Initializable
{

    private SongModel songModel;
    @FXML
    private ImageView btn_close;
    @FXML
    private ImageView btn_playpause;
    @FXML
    private ImageView btn_previous;
    @FXML
    private ImageView btn_shuffle;
    @FXML
    private ImageView btn_stop;
    @FXML
    private ImageView btn_replay;
    @FXML
    private ListView<?> txt_playlist;
    @FXML
    private ListView<?> txt_song_playlist;
    @FXML
    private ListView<Song> txt_songs;
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
    @FXML
    private Slider slider_volume;
    @FXML
    private ImageView btn_max;
    @FXML
    private ImageView btn_next;
    

    private void handleButtonAction(MouseEvent event)
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try 
        {
            songModel = new SongModel();
            txt_songs.setItems(songModel.getAllSongs());
            
            setSongSelection();
        } catch (Exception ex) {
            System.out.println("does not work properly");
            ex.printStackTrace();
        }
    }
    
    private void setSongSelection()
    {
        txt_songs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        /*txt_songs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>()
                {
            @Override
            public void changed(ObservableValue<? extends Song> arg0, Song oldValue, Song newValue)
            {
                if (newValue != null)
                {
                    txtSelectedMovieTitle.setText(newValue.getTitle());
                    txtSelectedMovieYear.setText(newValue.getYear() + "");
                }
            }
                });*/
    }
    
    /*@FXML
    private void handleDeleteMovie(ActionEvent event) throws IOException
    {
        Song selectedSong = txt_songs.getSelectionModel().getSelectedItem();
        txt_songs.getItems().remove(selectedSong);
        songModel.deleteSong(selectedSong);
    }*/

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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private boolean window_mode(javafx.scene.input.MouseEvent event)
    {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    if (true)
    {
        stage.setFullScreen(true);
        return false;
    }
    else
    {
        stage.setFullScreen(false);
        return true;
    }
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
