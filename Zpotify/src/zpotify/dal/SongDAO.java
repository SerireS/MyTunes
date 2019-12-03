/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal;

import zpotify.be.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Jonas
 */
public class SongDAO
{

    private static final String SONG_SOURCE = "musik/songs.txt";

    /*public List<Song> getAllSongs() throws IOException
    {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(SONG_SOURCE))))
        {
            List<Song> allSongs = new ArrayList<>();

            while (true)
            {
                String aLineOfText = br.readLine();
                if (aLineOfText == null)
                {
                    break;
                } else if (!aLineOfText.isEmpty())
                {
                    try
                    {
                        String[] arrSong = aLineOfText.split(",");

                        int id = Integer.parseInt(arrSong[0].trim());
                        String title = arrSong[1].trim();
                        String artist = arrSong[2].trim();
                        int length = Integer.parseInt(arrSong[3].trim());

                        for (int i = 3; i < arrSong.length; i++)
                        {
                            title += "," + arrSong[i];
                            artist += "," + arrSong[i];
                        }
                        Song song = new Song(id, title, artist, place);
                        allSongs.add(song);
                    } catch (Exception e)
                    {
                        //Catch
                    }
                }
            }
            return allSongs;
        }

    }
*/
    public void updateSong(Song song) throws IOException
    {
//        List<Song> allSongs = getAllSongs();
    }

    public void deleteSong(Song song) throws IOException
    {
        //to be continued
    }
}

