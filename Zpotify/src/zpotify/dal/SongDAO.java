/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import zpotify.be.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonas
 */
public class SongDAO
{
    
    private static final String SONG_SOURCE = "musik/songs.txt";

    public List<Song> getAllSongs() throws IOException
    {
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File(SONG_SOURCE))))
        {
            List<Song> allSongs = new ArrayList<>();
            
            while(true)
            {
                String aLineOfText = br.readLine();
                if (aLineOfText == null)
                {
                    break;
                }else if (!aLineOfText.isEmpty())
                {
                    try
                    {
                        String[] arrSong = aLineOfText.split(",");
                        
                        int id = Integer.parseInt(arrSong[0].trim());
                        String title = arrSong[1].trim();
                        String artist = arrSong[2].trim();
                        int length = Integer.parseInt(arrSong[3].trim());
                        for(int i = 3; i < arrSong.length; i++)
                                {
                                    title += "," + arrSong[i];
                                    artist += "," + arrSong[i];
                                }
                        Song song = new Song(id, title, length, artist);
                                allSongs.add(song);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return allSongs;
        }
        
    }

    public void updateSong(Song song) throws IOException
    {
        List<Song> allSongs = getAllSongs();
    }
    
//    public void deleteSong(Song song) throws IOException
////    {
////        List<Song> allSongs = getAllSongs();
////        if (allSongs.remove(song))
////        {
////            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(SONG_SOURCE))))
////            {
////                for (Song song : allSongs)
////                {
////                    bw.write(song.getArtist()+ "," + song.getTitle() + "," + song.getLength());
////                    bw.newLine();
////                }
////            }
////
////        }
////    }
}

