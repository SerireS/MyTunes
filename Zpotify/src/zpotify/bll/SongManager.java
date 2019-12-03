/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Song;
import zpotify.dal.SongDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zpotify.dal.DalException;
import zpotify.dal.database.SongDBDAO;

/**
 * @author jigzi
 */
public class SongManager
{
    
    private SongDAO songDao;
    private SongDBDAO songDBDao;

    public SongManager() 
    {
        
        songDao = new SongDAO();
        try {
            songDBDao = new SongDBDAO();
        } catch (IOException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Song> getAllSongs() 
    {
        try {
            return songDBDao.getAllSongs();
        } catch (SQLException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateSong(Song song) 
    {
        try {
            songDao.updateSong(song);
        } catch (IOException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSong(Song song) 
    {
        try {
            songDao.deleteSong(song);
        } catch (IOException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*

    public List<Song> search(String query)
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
