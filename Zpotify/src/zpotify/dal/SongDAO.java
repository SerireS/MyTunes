/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import java.io.File;
import zpotify.be.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonas
 */
public class SongDAO
{
    
    private static final String SONG_SOURCE = "musik/songs.txt";

    public List<Song> getAllSongs()
    {
        File file = new File (SONG_SOURCE);
        
        System.out.println("is it there:" + file.canRead());
        
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

