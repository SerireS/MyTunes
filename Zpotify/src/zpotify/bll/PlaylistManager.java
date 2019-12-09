/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Playlist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zpotify.dal.DalException;
import zpotify.dal.database.PlaylistDBDAO;

/**
 * @author jigzi
 */
public class PlaylistManager
{
    private PlaylistDBDAO playlistDBDao;

    public PlaylistManager()
    {
        
        try {
            playlistDBDao = new PlaylistDBDAO();
        } catch (IOException ex) {
            Logger.getLogger(PlaylistManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Playlist> getAllPlaylists()
    {
        try {
            return playlistDBDao.getAllPlaylists();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean createPlaylist(String playlistName) throws DalException
    {
        return playlistDBDao.createPlaylist(playlistName);
    }
    

//    public void updatePlaylist(Playlist playlist)
//    {
//        playlistDBDao.updatePlaylist(playlist);
//    }

    public void deletePlaylist(Playlist playlist) throws DalException
    {
        playlistDBDao.deletePlaylist(playlist);
    }
}
