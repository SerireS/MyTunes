/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zpotify.be.Playlist;
import zpotify.be.Song;
import zpotify.bll.PlaylistSongManager;
import zpotify.gui.FXMLDocumentController;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Den Gode Gruppe
 */
public class PlaylistSongModel
{
    private ObservableList songsInPlaylist;
    private PlaylistSongManager playlistSongManager;
    private FXMLDocumentController mainController;

    //Constructor. Giving model the controller and manager connection.
    public PlaylistSongModel(FXMLDocumentController mainController) throws IOException
    {
        this.mainController = mainController;
        playlistSongManager = new PlaylistSongManager();
    }

    //This is what the controller calls when it tries to show the Songs in a single playlist. This calls a method in the playlistsongmanager
    public ObservableList getPlaylistSongs(int id) throws SQLException
    {

        this.songsInPlaylist = FXCollections.observableArrayList(playlistSongManager.getPlaylistSongs(id));
        return this.songsInPlaylist;
    }

    //This is what the controller calls when deleting a song from a playlist. This calls a method in the playlistsongmanager
    public void deleteFromPlaylistSong(Playlist playlist, Song song)
    {
        playlistSongManager.deleteFromPlaylistSong(playlist, song);
    }

    //This is what the controller calls when adding a song to a playlist. This calls a method in the playlistsongmanager
    public void addToPlaylist(Playlist playlist, Song song)
    {
        playlistSongManager.addToPlaylist(playlist, song);
    }
}
