/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import java.awt.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import zpotify.gui.model.SongModel;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import zpotify.be.Song;

/**
 * @author Peder
 */
public class FXMLDocumentController implements Initializable {

    private static MediaPlayer mediaPlayer;
    private Media media;
    private boolean windowsState = true;
    private boolean playing = true;
    private SongModel songModel;
    @FXML
    private ImageView btn_close;
    @FXML
    private ImageView btn_playpause;
    @FXML
    private ImageView btn_previous;
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
    private ImageView btn_window_mode;
    @FXML
    private ImageView button_next;
    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField songPlaying;

    /*@FXML
    private void handleDeleteSong(ActionEvent event) throws IOException
    {
        Song selectedSong = txt_songs.getSelectionModel().getSelectedItem();
        txt_songs.getItems().remove(selectedSong);
        songModel.deleteSong(selectedSong);
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_songs.setOnMouseClicked(click
                -> {
            if (click.getClickCount() == 2) {
                try {
                    textPlaying();
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                } catch (Exception ignored) {
                }
                
                System.out.println(txt_songs.getSelectionModel().getSelectedItem().getPlace());

                media = new Media(new File(txt_songs.getSelectionModel().getSelectedItem().getPlace()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                btn_playpause.setImage(new Image("/Image/pause1.png"));
                textPlaying();
            }
        });

        // providing functionality to volume slider 
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isPressed()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100); // It would set the volume 
                    // as specified by user by pressing 
                }
            }
        });

//        try
//        {
//            File folder = new File("musik");
//            File[] listOfFiles = folder.listFiles();
//            ObservableList<String> songList = FXCollections.observableArrayList();
//            if (listOfFiles != null)
//            {
//                for (File file : listOfFiles)
//                {
//                    songList.add(file.getName());
//                }
//            }
//            txt_songs.setItems(songList);
//        } catch (Exception ignore)
//        {
//            System.out.println("No Files In Folder");
//        }
        try {
            songModel = new SongModel();

            ObservableList<Song> songs = songModel.getAllSongs();
            txt_songs.setItems(songs);
            System.out.println(songs);

            setSongSelection();
        } catch (Exception ex) {
            System.out.println("does not work properly");
            ex.printStackTrace();
        }
    }

    private void setSongSelection() {
        txt_songs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        /*txt_songs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>()
                {
            @Override
            public void changed(ObservableValue<? extends Song> arg0, Song oldValue, Song newValue)
            {
                if (newValue != null)
                {
                    txtSelectedMovieYear.setText(newValue.getId() + "");
                    txtSelectedMovieTitle.setText(newValue.getTitle());
                    txtSelectedMovieTitle.setText(newValue.getArtist());
                    txtSelectedMovieYear.setText(newValue.getLength() + "");
                }
            }
                });*/
    }

    @FXML
    //closes the app
    private void close_app(javafx.scene.input.MouseEvent event) {
        System.exit(0);
    }

    @FXML
    //minimizes the app
    private void minimize_app(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void windowMode(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (windowsState) {
            stage.setFullScreen(true);
            windowsState = false;
        } else {
            stage.setFullScreen(false);
            windowsState = true;
        }
    }

    @FXML
    //play and pause the music
    private void play_pause(javafx.scene.input.MouseEvent event) {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            btn_playpause.setImage(new Image("Image/play-button (2).png"));
        } else {
            mediaPlayer.play();
            btn_playpause.setImage(new Image("/Image/pause1.png"));
        }
    }

    @FXML
    //skip to next song
    private void nextSong(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    //play previous song
    private void previousSong(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    //Opens new window to add playlist
    private void handleButtonActionNewPlaylist(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNewEditPlaylist.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Opens new window to edit playlist
    private void handleButtonActionEditPlaylist(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNewEditPlaylist.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Deletes playlist
    private void handleButtonActionDeletePlaylist(ActionEvent event) {
    }

    @FXML
    //Deletes song on playlist
    private void handleButtonActionDeleteSongOnPlaylist(ActionEvent event) {
    }

    @FXML
    //Opens new window to add new song
    private void handleButtonActionNewSong(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNewEditSong.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Opens new window to edit song
    private void handleButtonActionEditSong(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNewEditSong.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Deletes song
    private void handleButtonActionDeleteSong(ActionEvent event) {
    }

    private void textPlaying() {
        songPlaying.setText(txt_songs.getSelectionModel().getSelectedItem().getTitle());
    }
}
