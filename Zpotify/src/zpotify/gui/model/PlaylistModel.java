/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zpotify.be.Playlist;
import zpotify.bll.PlaylistManager;
import zpotify.dal.DalException;
import zpotify.gui.FXMLDocumentController;

import java.util.Comparator;

/**
 * @author Den Gode Gruppe
 */
public class PlaylistModel
{
    private ObservableList<Playlist> allPlaylists;
    private PlaylistManager playlistManager;
    private FXMLDocumentController mainController;

    public PlaylistModel(FXMLDocumentController mainController)
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
        boolean playlistIsCreated = playlistManager.createPlaylist(playlistName);
        if (!playlistIsCreated)
        {
            return;
        }
        mainController.refreshSongs();
    }

    public void deletePlaylist(Playlist selectedPlaylist) throws DalException
    {
        playlistManager.deletePlaylist(selectedPlaylist);
        if (allPlaylists.remove(selectedPlaylist))
        {
            allPlaylists.remove(selectedPlaylist);
            // Meningen er at sortere dem efter længde? Ved ikke omdet er meninge, revurder metode
            allPlaylists.sort(Comparator.comparingInt(Playlist::getPlaylistId));
        }
    }

    public void updatePlaylist(String title, int id)
    {
        boolean playlistIsUpdated = playlistManager.updatePlaylist(title, id);
        if (playlistIsUpdated)
        {
            mainController.refreshSongs();
        }
    }
}
