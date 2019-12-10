/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import zpotify.be.Playlist;
import zpotify.be.Song;
import zpotify.dal.DalException;
import zpotify.dal.database.PlaylistSongDBDAO;

/**
 *
 * @author jigzi
 */
public class PlaylistSongManager {
    private PlaylistSongDBDAO playlistSongDBdao;

    public PlaylistSongManager() throws IOException {
        
      playlistSongDBdao = new PlaylistSongDBDAO();
    }
    
    public List<Song> getPlaylistSongs(int id) throws SQLException 
    {
        
        return playlistSongDBdao.getPlaylistSongs(id);
        
    }

    public void deleteFromPlaylistSongEverything(Playlist playlist, Song selectedSong) throws DalException, SQLException 
    {
        playlistSongDBdao.deleteFromPlaylistSongEverything(playlist, selectedSong);
    }
    
    public void addToPlaylist(Playlist playlist, Song song) throws SQLException
            
    {
        playlistSongDBdao.addToPlaylist(playlist, song);
    }
   
}
