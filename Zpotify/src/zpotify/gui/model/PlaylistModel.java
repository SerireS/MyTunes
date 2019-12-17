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

    //This is what the controller calls when it is trying to show a list of all playlist. This calls a method in the PlaylistManager
    public ObservableList<Playlist> getAllPlaylists()
    {
        allPlaylists = FXCollections.observableArrayList();
        allPlaylists.addAll(playlistManager.getAllPlaylists());
        return allPlaylists;
    }

    //This is what the controller calls when creating a playlist. This calls a method in the PlaylistManager
    public void createPlaylist(String playlistName) throws DalException
    {
        boolean playlistIsCreated = playlistManager.createPlaylist(playlistName);
        if (!playlistIsCreated)
        {
            return;
        }
        mainController.refreshSongs();
    }

    //This is what the controller calls when deleting a playlist. This calls a method in the PlaylistManager
    public void deletePlaylist(Playlist selectedPlaylist) throws DalException
    {
        playlistManager.deletePlaylist(selectedPlaylist);
        if (allPlaylists.remove(selectedPlaylist))
        {
            allPlaylists.remove(selectedPlaylist);
            allPlaylists.sort(Comparator.comparingInt(Playlist::getPlaylistId));
        }
    }

    //This is what the controller calls when updating a playlist. This calls a method in the PlaylistManager
    public void updatePlaylist(String title, int id)
    {
        boolean playlistIsUpdated = playlistManager.updatePlaylist(title, id);
        if (playlistIsUpdated)
        {
            mainController.refreshSongs();
        }
    }
}
