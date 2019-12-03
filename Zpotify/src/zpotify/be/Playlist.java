/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.be;

import java.util.List;

/**
 * @author jigzi
 */
public class Playlist
{
    private int playlistId;
    private List<Song> songs;
    private String playlistName;


    public Playlist(int playlistId, List<Song> songs, String playlistName)
    {
        this.playlistId = playlistId;
        this.songs = songs;
        this.playlistName = playlistName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
    
    public List<Song> getSongs()
    {
        return songs;
    }

    public void setSongs(List<Song> songs)
    {
        this.songs = songs;
    }

    public String getPlaylistName()
    {
        return playlistName;
    }

    public void setPlaylistName(String playlistName)
    {
        this.playlistName = playlistName;
    }


}
