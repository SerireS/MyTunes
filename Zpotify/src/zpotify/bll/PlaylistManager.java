/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Playlist;
import zpotify.dal.DalException;
import zpotify.dal.database.PlaylistDBDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Den Gode Gruppe
 */
public class PlaylistManager
{
    private PlaylistDBDAO playlistDBDao;

    //Constructor
    public PlaylistManager()
    {
        try
        {
            playlistDBDao = new PlaylistDBDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(PlaylistManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This is what the model calls when it is trying to show the list of playlists.
    public List<Playlist> getAllPlaylists()
    {
        try
        {
            return playlistDBDao.getAllPlaylists();
        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //This is what the model calls when creating a playlist. This calls a method in the PlaylistDBDao
    public boolean createPlaylist(String playlistName) throws DalException
    {
        return playlistDBDao.createPlaylist(playlistName);
    }

    //This is what the model calls when deleting a playlist. This calls a method in the PlaylistDBDao
    public void deletePlaylist(Playlist playlist) throws DalException
    {
        playlistDBDao.deletePlaylist(playlist);
    }

    //This is what the model calls when updating a playlist. This calls a method in the PlaylistDBDao
    public boolean updatePlaylist(String title, int id)
    {
        return playlistDBDao.updatePlaylist(title, id);
    }
}
