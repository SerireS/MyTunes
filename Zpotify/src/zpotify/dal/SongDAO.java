/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import zpotify.be.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonas
 */
public class SongDAO
{

    public List<Song> getAllSongs()
    {
        return new ArrayList<>();
    }

    public void updateSong(Song song)
    {
        List<Song> allSongs = getAllSongs();
    }
    
    public void deleteSong(Song song) throws IOException
    {
        //to be continued
    }
}

