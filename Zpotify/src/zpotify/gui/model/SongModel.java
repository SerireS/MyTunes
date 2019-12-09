/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import zpotify.be.Song;
import zpotify.bll.SongManager;
import zpotify.dal.DalException;
import zpotify.gui.FXMLDocumentController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author jigzi
 */
public class SongModel
{
    private ObservableList<Song> allSongs;
    private SongManager songManager;
    private FXMLDocumentController mainController;
    private Iterator<String> songIterator;

    // Maincontroller har den oprindelige refresh metode, derfor skal den sættes
    // i Constructor
    public SongModel(FXMLDocumentController mainController) throws IOException
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

    public void createSong(String title, String place) throws DalException
    {
        boolean songIsCreated = songManager.createSong(title, place);
        if (songIsCreated == true)
        {
            mainController.refreshSongs();
        }
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

    public void nextSong(Stage primaryStage)
    {
        getAllSongs();
        songIterator = allSongs.iterator();
        Label songLabel = new Label(songIterator.next());
        ListView<String> lv = new ListView(songs);

        MultipleSelectionModel<String> selectionModel = lv.getSelectionModel();

        lv.setCellFactory(t -> new ListCell<String>()
        {

            {
                setOnMouseClicked(evt ->
                {
                    if (evt.getButton() == MouseButton.PRIMARY && evt.getClickCount() == 2)
                    {
                        evt.consume();
                        // update label
                        songLabel.setText(getItem());
                        // iterator should return next item next
                        songIterator = songs.listIterator(selectionModel.getSelectedIndex() + 1);
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }

        });
    }
}
