/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zpotify.be.Playlist;
import zpotify.bll.PlaylistManager;
import zpotify.dal.DalException;
import zpotify.dal.database.PlaylistDBDAO;
import zpotify.gui.FXMLDocumentController;

/**
 *
 * @author jigzi
 */
public class PlaylistModel {
    private ObservableList<Playlist> allPlaylists;
    private PlaylistManager playlistManager;
    private FXMLDocumentController mainController;

    public PlaylistModel(FXMLDocumentController mainController) throws IOException
    {
        this.mainController = mainController;
        playlistManager = new PlaylistManager();
    }

    public ObservableList<Playlist> getAllPlaylists()
    {
        allPlaylists = FXCollections.observableArrayList();
        allPlaylists.addAll(playlistManager.getAllPlaylists());
        return allPlaylists;
    }
    
    public void createPlaylist(String playlistName) throws DalException
    {
        System.out.println(playlistName);
        boolean playlistIsCreated = playlistManager.createPlaylist(playlistName);
        if (playlistIsCreated == true){
            mainController.refreshSongs();
        }
    }

    public void deletePlaylist(Playlist selectedPlaylist) throws IOException, DalException
    {
        playlistManager.deletePlaylist(selectedPlaylist);
        if (allPlaylists.remove(selectedPlaylist))
        {
            allPlaylists.remove(selectedPlaylist);
            allPlaylists.sort(new Comparator<Playlist>()
            {
                // Meningen er at sortere dem efter længde? Ved ikke omdet er meninge, revurder metode
                @Override
                public int compare(Playlist arg0, Playlist arg1)
                {
                    return arg0.getPlaylistId()- arg1.getPlaylistId();
                }
            });
        }
    }
    public void updatePlaylist(String title, int id) throws SQLException{
    boolean playlistIsUpdated = playlistManager.updatePlaylist(title, id);
        if (playlistIsUpdated == true)
        {
            mainController.refreshSongs();
        }
    }
}
