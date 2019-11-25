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

/**
 *
 * @author jigzi
 */
public class SongModel {
    private ObservableList<Song> allSongs;
    private SongManager songManager;
    
    public SongModel()
    {
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
    }
    
    public ObservableList<Song> getAllSongs()
    {
        return allSongs;
    }
}
