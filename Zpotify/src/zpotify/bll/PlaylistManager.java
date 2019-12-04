/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Playlist;
import zpotify.dal.PlaylistDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zpotify.dal.database.PlaylistDBDAO;

/**
 * @author jigzi
 */
public class PlaylistManager
{
    private PlaylistDAO playlistDao;
    private PlaylistDBDAO playlistDBDao;

    public PlaylistManager()
    {
        playlistDao = new PlaylistDAO();
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

    public void updatePlaylist(Playlist playlist)
    {
        playlistDao.updatePlaylist(playlist);
    }

    public void deletePlaylist(Playlist playlist) throws IOException
    {
        playlistDao.deletePlaylist(playlist);
    }
}
