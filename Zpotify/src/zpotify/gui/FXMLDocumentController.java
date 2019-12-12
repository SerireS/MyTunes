/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import zpotify.be.Playlist;
import zpotify.be.Song;
import zpotify.dal.DalException;
import zpotify.gui.model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Peder
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
    private ImageView btn_close;
    @FXML
    private ImageView btn_playpause;
    @FXML
    private ImageView btn_previous;
    @FXML
    private ListView<Playlist> txt_playlist;
    @FXML
    private ListView<Song> txt_song_playlist;
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
    private ImageView button_next;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider seekSlider;
    @FXML
    private TextField songPlaying;
    @FXML
    private Button btn_add_song_to_playlist;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        buildModels();
        loadPlaylist();
        playSingleSong();
        playPlaylistSong();
        volumeControl();
        refreshSongs();
    }

    //Builds all Models necessary
    private void buildModels()
    {
        try
        {
            songModel = new SongModel(this);
        } catch (IOException ex)
        {
            System.out.println("Did not create new songmodel");
        }
        try
        {
            playlistModel = new PlaylistModel(this);
        } catch (IOException ex)
        {
            System.out.println("Did not create new playlistmodel");
        }

        try
        {
            playlistSongModel = new PlaylistSongModel(this);
        } catch (IOException ex)
        {
            System.out.println("Did not create new playlistSongModel");
        }
    }

    // providing functionality to volume slider
    private void volumeControl()
    {
        volumeSlider.valueProperty().addListener(new InvalidationListener()
        {
            public void invalidated(Observable ov)
            {
                try
                {
                    if (volumeSlider.isPressed())
                    {
                        mediaPlayer.setVolume(volumeSlider.getValue() / 100); // It would set the volume
                        // as specified by user by pressing
                    }
                } catch (Exception ex)
                {
                    System.out.println("Play Song To Change Volume");
                }
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
                System.out.println(currentSongPlaying);


                media = new Media(new File(txt_song_playlist.getSelectionModel().getSelectedItem().getPlace()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();


                btn_playpause.setImage(new Image("/Image/pause1.png"));
                textPlaying(txt_song_playlist.getItems().get(currentSongPlaying).toString());
            } else
            {
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
                playNextSong();
                songSlider();

            } else
            {
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

    private void windowMode(javafx.scene.input.MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (windowsState)
        {
            stage.setFullScreen(true);
            windowsState = false;
        } else
        {
            stage.setFullScreen(false);
            windowsState = true;
        }
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
        } catch (Exception ex)
        {
            System.out.println("No Song Selected");
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
            System.out.println("Ingen Næste Sange");
        }
        songSlider();
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
            System.out.println("Ingen Tidligere Sange");
        }
    }

    //Adds the currently selected song to the currently selected playlist
    @FXML
    private void handleAddSongToPlaylist() throws SQLException
    {
        Playlist playlist = txt_playlist.getSelectionModel().getSelectedItem();
        Song selectedSong = txt_songs.getSelectionModel().getSelectedItem();
        txt_song_playlist.getItems().add(selectedSong);
        playlistSongModel.addToPlaylist(playlist, selectedSong);
        System.out.println("Song succesfully added to playlist");
    }


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
                    } catch (InterruptedException e)
                    {
                    }
                    nextSong();
                }
            });
        } catch (Exception ex)
        {
            System.out.println("rip");
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
            // Her tildeles vigtige data objecter til edit controlleren, 
            // Det sikre at der er fat på de korrekte udgaver af dem.
            editplaylistcontroller.ApplyImportantData(playlistModel, this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.show();

        } catch (Exception e)
        {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Opens new window to edit playlist
    private void handleButtonActionEditPlaylist(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEditPlaylist.fxml"));
            Parent root1 = fxmlLoader.load();
            FXMLEditPlaylistController editplaylistcontroller = fxmlLoader.getController();
            // Her tildeles vigtige data objecter til edit controlleren, 
            // Det sikre at der er fat på de korrekte udgaver af dem.
            editplaylistcontroller.ApplyImportantData(playlistModel, this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.show();

        } catch (Exception e)
        {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Deletes playlist
    private void handleButtonActionDeletePlaylist(ActionEvent event) throws IOException, DalException
    {
        Playlist selectedPlaylist = txt_playlist.getSelectionModel().getSelectedItem();
        txt_playlist.getItems().remove(selectedPlaylist);
        playlistModel.deletePlaylist(selectedPlaylist);
        System.out.println("Playlist succesfully deleted");
    }

    @FXML
    //Deletes song on playlist
    private void handleButtonActionDeleteSongOnPlaylist(ActionEvent event) throws DalException, SQLException
    {
        Playlist playlist = txt_playlist.getSelectionModel().getSelectedItem();
        Song selectedSong = txt_song_playlist.getSelectionModel().getSelectedItem();
        txt_song_playlist.getItems().remove(selectedSong);
        playlistSongModel.deleteFromPlaylistSong(playlist, selectedSong);
        System.out.println("Song succesfully deleted from playlist" + playlist + selectedSong);
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
            // Her tildeles vigtige data objecter til edit controlleren, 
            // Det sikre at der er fat på de korrekte udgaver af dem.
            editsongcontroller.ApplyImportantData(songModel, this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e)
        {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Opens new window to edit song
    private void handleButtonActionEditSong(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEditSong.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Zpotify");
            stage.getIcons().add(new Image(Zpotify.class.getResourceAsStream("Image/wind (1).png")));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e)
        {
            System.out.println("Cant load new Window");
        }
    }

    @FXML
    //Deletes song
    private void handleButtonActionDeleteSong(ActionEvent event) throws IOException, DalException
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
            System.out.println("VI KLAREDE DEN IND I REFRESH - Song del");
            setSongSelection();
        } catch (Exception ex)
        {
            System.out.println("does not work properly !!!");
            ex.printStackTrace();
        }
        try
        {
            txt_playlist.setItems(this.playlistModel.getAllPlaylists());
            System.out.println("Vi klarede den ind i refresh, playlist del");
        } catch (Exception ex)
        {
            System.out.println("Vi klarede den IKKE i refresh playlist del");
            ex.printStackTrace();
        }
    }

    //This method loads the songs of the selected playlist, in the middle list.
    public void loadPlaylist()
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
    private void handleSearchSong(KeyEvent event) throws SQLException, DalException
    {
        try
        {
            String query = txt_search.getText().trim();
            songModel.search(query);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

//     mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->
//    {
//        if (newValue.toMillis() > mediaPlayer.getMedia().getDuration().toMillis() - 100)

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
        } catch (Exception ex)
        {
            System.out.println("rip");
        }
        seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                seekSlider.setOnMouseClicked(click ->
                {
                    if (click.getClickCount() == 1)
                        mediaPlayer.seek(Duration.millis(seekSlider.getValue()));
                        
            });

            }
        });
    }
}