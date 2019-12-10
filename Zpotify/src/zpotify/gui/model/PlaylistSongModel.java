/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zpotify.be.Playlist;
import zpotify.be.Song;
import zpotify.bll.PlaylistSongManager;
import zpotify.dal.DalException;
import zpotify.gui.FXMLDocumentController;

/**
 *
 * @author jigzi
 */
public class PlaylistSongModel {
    private ObservableList<Song> songsInPlaylist;
    private PlaylistSongManager playlistSongManager;
    private FXMLDocumentController mainController;

    public PlaylistSongModel(FXMLDocumentController mainController) throws IOException
    {
        this.mainController = mainController;
        playlistSongManager = new PlaylistSongManager();   
    }
    public ObservableList<Song> getPlaylistSongs(int id) throws SQLException 
    {
        
        this.songsInPlaylist = FXCollections.observableArrayList(playlistSongManager.getPlaylistSongs(id));
            return this.songsInPlaylist;    
    }

    public void deleteFromPlaylistSongEverything(Playlist playlist, Song selectedSong) throws DalException, SQLException 
    {
        playlistSongManager.deleteFromPlaylistSongEverything(playlist, selectedSong);
    }
    
    public void addToPlaylist(Playlist playlist, Song song) throws SQLException
    {
        playlistSongManager.addToPlaylist(playlist, song);
    }
}
