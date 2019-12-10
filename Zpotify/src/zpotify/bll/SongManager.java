/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Song;


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
    
    private SongDBDAO songDBDao;

    public SongManager() 
    {
        
        try {
            songDBDao = new SongDBDAO();
        } catch (IOException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean createSong(String title, String place) throws DalException
    {
        return songDBDao.createSong(title, place);
    }
    
    public List<Song> search(String query) throws DalException, SQLException
    {
        List<Song> searchBase = songDBDao.getAllSongs();
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

    public List<Song> getAllSongs() 
    {
        try {
            return songDBDao.getAllSongs();
        } catch (SQLException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteSong(Song song) throws DalException 
    {
        songDBDao.deleteSong(song);
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
