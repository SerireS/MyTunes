/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Song;
import zpotify.dal.SongDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import zpotify.dal.DalException;

/**
 * @author jigzi
 */
public class SongManager
{
    
    private SongDAO songDao;

    public SongManager()
    {
        songDao = new SongDAO();
    }

    public List<Song> getAllSongs() throws IOException
    {
        return songDao.getAllSongs();
    }

    public void updateSong(Song song) throws IOException
    {
        songDao.updateSong(song);
    }

    public void deleteSong(Song song) throws IOException
    {
        songDao.deleteSong(song);
    }
    
    /*

    public List<Song> search(String query) throws DalException, IOException
    {
        List<Song> searchBase = songDao.getAllSongs();
        List<Song> result = new ArrayList<>();

        for (Song song : searchBase)
        {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(song);
            }
        }
        return result;
    }
    */
}
