/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Song;
import zpotify.dal.DalException;
import zpotify.dal.database.SongDBDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Den Gode Gruppe
 */
public class SongManager
{

    private SongDBDAO songDBDao;

    public SongManager()
    {

        try
        {
            songDBDao = new SongDBDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // This method calls the createSong method in the songDBDao
    public boolean createSong(String title, String place) throws DalException
    {
        return songDBDao.createSong(title, place);
    }

    //This method compares the song titles to the written search query. Thus creating a functional search.
    public List<Song> search(String query) throws SQLException
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

    // This song tries to call getAllSongs method from songDBDao
    public List<Song> getAllSongs()
    {
        try
        {
            return songDBDao.getAllSongs();
        } catch (SQLException ex)
        {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // This method calls the method deleteSong in the songDBDao
    public void deleteSong(Song song) throws DalException
    {
        songDBDao.deleteSong(song);
    }

    //This method calls the method updateSong method from the songDBDao
    public boolean updateSong(String title, int id)
    {
        return songDBDao.updateSong(title, id);
    }

}
