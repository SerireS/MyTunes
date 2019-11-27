/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import java.io.IOException;
import java.util.List;
import zpotify.be.Playlist;
import zpotify.dal.PlaylistDAO;

/**
 *
 * @author jigzi
 */
public class PlaylistManager {
    private PlaylistDAO playlistDao;
    
    public PlaylistManager()
    {
        playlistDao = new PlaylistDAO();
    }
    
    public List<Playlist> getAllPlaylists()
    {
        return playlistDao.getAllPlaylists();
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
