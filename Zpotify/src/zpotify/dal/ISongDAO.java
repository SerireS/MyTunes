/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import java.util.List;
import zpotify.be.Song;


/**
 *
 * @author nbruu
 */
public interface ISongDAO 
{
    Song createSong(String title, String artist, int length) throws DalException;
    
    void deleteSong(Song song) throws DalException;

    List<Song> getAllSongs() throws DalException;

    void updateSong(Song song) throws DalException;

    void writeAllSongs(List<Song> allSongs, String fileName) throws DalException;
    
}
