/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zpotify.be.Song;
import zpotify.bll.SongManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import zpotify.dal.DalException;
import zpotify.dal.database.SongDBDAO;

/**
 * @author jigzi
 */
public class SongModel
{
    private ObservableList<Song> allSongs;
    private SongManager songManager;
    private SongDBDAO songDBDao;

    public SongModel() throws IOException
    {
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getAllSongs()
    {
        return allSongs;
    }
    
    public void search(String query) throws IOException, DalException, SQLException
    {
        if (query.isEmpty())
        {
            allSongs.clear();
            allSongs.addAll(songManager.getAllSongs());
        } else
        {
            allSongs.clear();
            allSongs.addAll(songManager.search(query));
        }
    }
    
     public Song createSong(String title, String place) throws DalException
    {
        songManager.createSong(title, place);
        return null;
    }

    public void deleteSong(Song selectedSong) throws IOException, DalException
    {
        songManager.deleteSong(selectedSong);
        if (allSongs.remove(selectedSong))
        {
            allSongs.remove(selectedSong);
            allSongs.sort(new Comparator<Song>()
            {
                // Meningen er at sortere dem efter længde? Ved ikke omdet er meninge, revurder metode
                @Override
                public int compare(Song arg0, Song arg1)
                {
                    return arg0.getLength() - arg1.getLength();
                }
            });
        }
    }
}
