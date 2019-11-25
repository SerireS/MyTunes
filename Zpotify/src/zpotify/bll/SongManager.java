/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Song;
import zpotify.dal.SongDAO;

import java.util.List;

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

    public List<Song> getAllSongs()
    {
        return songDao.getAllSongs();
    }

    public void updateSong(Song song)
    {
        songDao.updateSong(song);
    }
}
