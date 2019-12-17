/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import zpotify.be.Playlist;
import zpotify.be.Song;
import zpotify.dal.DalException;
import zpotify.gui.model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Den Gode Gruppe
 */
public class FXMLDocumentController implements Initializable
{

    private static MediaPlayer mediaPlayer;
    private Media media;
    private boolean windowsState = true;
    private boolean playing = true;
    private PlaylistModel playlistModel;
    private PlaylistSongModel playlistSongModel;
    private SongModel songModel;
    private int currentSongPlaying;
    @FXML
    private ImageView btn_playpause;
    @FXML
    private ListView<Playlist> txt_playlist;
    @FXML
    private ListView<Song> txt_song_playlist;
    @FXML
    private ListView<Song> txt_songs;
    @FXML
    private TextField txt_search;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider seekSlider;
    @FXML
    private TextField songPlaying;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        buildModels();
        loadPlaylist();
        playSingleSong();
        playPlaylistSong();
        volumeControl();
        refreshSongs();
        songSlider();
    }

    //Builds all Models necessary
    private void buildModels()
    {
        songModel = new SongModel(this);
        playlistModel = new PlaylistModel(this);

        try
        {
            playlistSongModel = new PlaylistSongModel(this);
        } catch (IOException ignored)
        {
        }
    }

    // providing functionality to volume slider
    private void volumeControl()
    {
        volumeSlider.valueProperty().addListener(ov ->
        {
            try
            {
                if (volumeSlider.isPressed())
                {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100); // It would set the volume
                    // as specified by user by pressing
                }
            } catch (Exception ignored)
            {
            }
        });
    }

    //Selects a single song entity in the list of songs
    private void setSongSelection()
    {
        txt_songs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    //plays a song on a playlist
    private void playPlaylistSong()
    {
        txt_song_playlist.setOnMouseClicked(click ->
        {
            if (click.getClickCount() == 2)
            {
                try
                {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                } catch (Exception ignored)
                {

                }

                currentSongPlaying = txt_song_playlist.getSelectionModel().getSelectedIndex();


                media = new Media(new File(txt_song_playlist.getSelectionModel().getSelectedItem().getPlace()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();


                btn_playpause.setImage(new Image("/Image/pause1.png"));
                textPlaying(txt_song_playlist.getItems().get(currentSongPlaying).toString());
            }
        });
    }

    // Plays a song from the list of songs.
    private void playSingleSong()
    {
        txt_songs.setOnMouseClicked(click ->
        {
            if (click.getClickCount() == 2)
            {

                try
                {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                } catch (Exception ignored)
                {

                }

                currentSongPlaying = txt_songs.getSelectionModel().getSelectedIndex();


                media = new Media(new File(txt_songs.getSelectionModel().getSelectedItem().getPlace()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();

                btn_playpause.setImage(new Image("/Image/pause1.png"));
                textPlaying(txt_songs.getItems().get(currentSongPlaying).toString());
                songSlider();

            }
        });
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    //play and pause the music
    private void play_pause(javafx.scene.input.MouseEvent event)
    {
        try
        {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            {
                mediaPlayer.pause();
                btn_playpause.setImage(new Image("Image/play-button (3).png"));
            } else
            {
                mediaPlayer.play();
                btn_playpause.setImage(new Image("/Image/pause1.png"));
            }
        } catch (Exception ignored)
        {
        }
    }

    @FXML
    //skip to next song
    private void nextSong()
    {
        try
        {
            currentSongPlaying = currentSongPlaying + 1;
            media = new Media(new File(txt_songs.getItems().get(currentSongPlaying).getPlace()).toURI().toString());

            try
            {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            } catch (Exception ignored)
            {
            }

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            btn_playpause.setImage(new Image("/Image/pause1.png"));
            textPlaying(txt_songs.getItems().get(currentSongPlaying).toString());

            txt_songs.getSelectionModel().select(currentSongPlaying);
        } catch (Exception ex)
        {
            currentSongPlaying = currentSongPlaying - 1;
        }
    }

    @FXML
    //play previous song
    private void previousSong(javafx.scene.input.MouseEvent event)
    {
        try
        {
            currentSongPlaying = currentSongPlaying - 1;
            media = new Media(new File(txt_songs.getItems().get(currentSongPlaying).getPlace()).toURI().toString());

            try
            {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            } catch (Exception ignored)
            {
            }

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            btn_playpause.setImage(new Image("/Image/pause1.png"));
            textPlaying(txt_songs.getItems().get(currentSongPlaying).toString());

            txt_songs.getSelectionModel().select(currentSongPlaying);
        } catch (Exception ex)
        {
            currentSongPlaying = currentSongPlaying + 1;
        }
    }

    //Adds the currently selected song to the currently selected playlist
    @FXML
    private void handleAddSongToPlaylist()
    {
        Playlist playlist = txt_playlist.getSelectionModel().getSelectedItem();
        Song selectedSong = txt_songs.getSelectionModel().getSelectedItem();
        txt_song_playlist.getItems().add(selectedSong);
        playlistSongModel.addToPlaylist(playlist, selectedSong);
    }

    // Playing the next song
    private void playNextSong()
    {
        try
        {
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->
            {
                if (newValue.toMillis() > mediaPlayer.getMedia().getDuration().toMillis() - 100)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored)
                    {
                    }
                    nextSong();
                }
            });
        } catch (Exception ignored)
        {
        }
    }


    @FXML
    //Opens new window to add playlist
    private void handleButtonActionNewPlaylist(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNewPlaylist.fxml"));
            Parent root1 = fxmlLoader.load();
            FXMLNewPlaylistController editplaylistcontroller = fxmlLoader.getController();
            // Here the edit controller is given important data objects,
            // This secures that it is the correct ones we are working with.
            editplaylistcontroller.ApplyImportantData(playlistModel, this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.show();

        } catch (Exception ignored)
        {
        }
    }

    @FXML
    //Opens new window to edit playlist
    private void handleButtonActionEditPlaylist(ActionEvent event)
    {
        try
        {
            Playlist selectedPlaylist = txt_playlist.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEditPlaylist.fxml"));
            Parent root1 = fxmlLoader.load();
            FXMLEditPlaylistController editplaylistcontroller = fxmlLoader.getController();
            // Here the edit controller is given important data objects,
            // This secures that it is the correct ones we are working with.
            editplaylistcontroller.ApplyImportantData(playlistModel, this, selectedPlaylist);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.show();

        } catch (Exception ignored)
        {
        }
    }

    @FXML
    //Deletes playlist
    private void handleButtonActionDeletePlaylist(ActionEvent event) throws DalException
    {
        Playlist selectedPlaylist = txt_playlist.getSelectionModel().getSelectedItem();
        txt_playlist.getItems().remove(selectedPlaylist);
        playlistModel.deletePlaylist(selectedPlaylist);
    }

    @FXML
    //Deletes song on playlist
    private void handleButtonActionDeleteSongOnPlaylist(ActionEvent event)
    {
        Playlist playlist = txt_playlist.getSelectionModel().getSelectedItem();
        Song selectedSong = txt_song_playlist.getSelectionModel().getSelectedItem();
        txt_song_playlist.getItems().remove(selectedSong);
        playlistSongModel.deleteFromPlaylistSong(playlist, selectedSong);
    }

    @FXML
    //Opens new window to add new song
    private void handleButtonActionNewSong(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNewSong.fxml"));
            Parent root1 = fxmlLoader.load();
            FXMLNewSongController editsongcontroller = fxmlLoader.getController();
            // Here the edit controller is given important data objects,
            // This secures that it is the correct ones we are working with.
            editsongcontroller.ApplyImportantData(songModel, this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ignored)
        {
        }
    }

    @FXML
    //Opens new window to edit song
    private void handleButtonActionEditSong(ActionEvent event)
    {
        try
        {
            Song selectedSong = txt_songs.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEditSong.fxml"));
            Parent root1 = fxmlLoader.load();
            FXMLEditSongController editsongcontroller = fxmlLoader.getController();
            // Here the edit controller is given important data objects,
            // This secures that it is the correct ones we are working with.
            editsongcontroller.ApplyImportantData(songModel, this, selectedSong);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ignored)
        {
        }
    }

    @FXML
    //Deletes song
    private void handleButtonActionDeleteSong(ActionEvent event) throws DalException
    {
        Song selectedSong = txt_songs.getSelectionModel().getSelectedItem();
        txt_songs.getItems().remove(selectedSong);
        songModel.deleteSong(selectedSong);
    }

    //Puts the name of the song in a textfield. Effectively showing what song is being played
    private void textPlaying(String songName)
    {
        songPlaying.setText(songName);
        songPlaying.setAlignment(Pos.CENTER);
    }

    //The refreshSongs method refresh the list of songs and playlists
    public void refreshSongs()
    {
        try
        {
            txt_songs.setItems(this.songModel.getAllSongs());
            setSongSelection();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            txt_playlist.setItems(this.playlistModel.getAllPlaylists());
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //This method loads the songs of the selected playlist, in the middle list.
    private void loadPlaylist()
    {
        txt_playlist.setOnMouseClicked(click ->
        {
            if (click.getClickCount() == 1)
            {
                int currentPlaylistSelected = txt_playlist.getSelectionModel().getSelectedItem().getPlaylistId();

                try
                {
                    txt_song_playlist.setItems(this.playlistSongModel.getPlaylistSongs(currentPlaylistSelected));
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    //This is a search function
    @FXML
    private void handleSearchSong(KeyEvent event) throws SQLException
    {
        String query = txt_search.getText().trim();
        songModel.search(query);
    }

    // Shows how long we are into the song
    private void songSlider()
    {
        try
        {
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->
            {
                {
                    {
                        seekSlider.setMax(mediaPlayer.getMedia().getDuration().toMillis());
                        seekSlider.setValue(newValue.toMillis());
                    }
                }
            });
        } catch (Exception ignored)
        {
        }
        // Jumps to time in the media when pressed.
        seekSlider.setOnMousePressed(event1 -> mediaPlayer.seek(Duration.millis(seekSlider.getValue())));
    }

    @FXML
    //Hmmmmmmmm
    private void Rick(javafx.scene.input.MouseEvent event)
    {
        try
        {
            Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ").toURI());
        } catch (IOException | URISyntaxException ignored)
        {
        }
    }
}