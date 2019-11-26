/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import zpotify.be.Playlist;

/**
 *
 * @author jigzi
 */
public class PlaylistDAO {
    public List<Playlist> getAllPlaylists()
    {
        return new ArrayList<>();
    }
    
    public void updatePlaylist(Playlist playlist)
    {
        List<Playlist> allPlaylists = getAllPlaylists();
    }
    
    public void deletePlaylist(Playlist playlist) throws IOException
    {
        //To be continued
    }
}
