/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import java.sql.SQLException;
import java.util.List;
import zpotify.be.Song;
import zpotify.dal.DalException;
import zpotify.dal.database.PlaylistSongDBDAO;

/**
 *
 * @author jigzi
 */
public class PlaylistSongManager {
    private PlaylistSongDBDAO playlistSongDBdao;

    public PlaylistSongManager() {
      playlistSongDBdao = new PlaylistSongDBDAO();
    }
    
    public List<Song> getPlaylistSongs() 
    {
        return playlistSongDBdao.getPlaylistSongs(P);
    }

    public void deleteFromPlaylistSongEverything(Song selectedSong) throws DalException, SQLException 
    {
        playlistSongDBdao.deleteFromPlaylistSongEverything(selectedSong);
    }
    
}
