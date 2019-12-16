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
import zpotify.dal.DalException;
import zpotify.gui.FXMLDocumentController;

import java.sql.SQLException;

/**
 * @author Den Gode Gruppe
 */
public class SongModel
{
    private ObservableList<Song> allSongs;
    private SongManager songManager;
    private FXMLDocumentController mainController;

    // Maincontroller har den oprindelige refresh metode, derfor skal den sættes
    // i Constructor
    public SongModel(FXMLDocumentController mainController)
    {
        this.mainController = mainController;
        songManager = new SongManager();
    }

    public ObservableList<Song> getAllSongs()
    {
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        return allSongs;
    }

    public void search(String query) throws SQLException
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

    public void createSong(String title, String place) throws DalException
    {
        boolean songIsCreated = songManager.createSong(title, place);
        if (songIsCreated)
        {
            mainController.refreshSongs();
        }
    }

    public void deleteSong(Song selectedSong) throws DalException
    {
        songManager.deleteSong(selectedSong);
        if (allSongs.remove(selectedSong))
        {
            allSongs.remove(selectedSong);
        }
    }

    public void updateSong(String title, int id)
    {
        boolean songIsUpdated = songManager.updateSong(title, id);
        if (songIsUpdated)
        {
            mainController.refreshSongs();
        }
    }
}
