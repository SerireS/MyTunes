/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import zpotify.be.Song;

import java.util.List;

/**
 * @author nbruu
 */
public interface iSongDAO
{

    Song createSong(String title, int length, String artist) throws DalException;

    void deleteSong(Song song) throws DalException;

    List<Song> getallSongs() throws DalException;

    void updateSong(Song song) throws DalException;

    void writeAllSongs(List<Song> allsongs, String fileName) throws DalException;
}
