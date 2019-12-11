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
    private String playlistName;

    public Playlist(int playlistId, String playlistName) 
    {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
    }
    
    public Playlist(String playlistName) 
    {
        this.playlistName = playlistName;
    }
    //Returns the id of the playlist
    public int getPlaylistId() 
    {
        return playlistId;
    }
    //Sets the id of the playlist
    public void setPlaylistId(int playlistId) 
    {
        this.playlistId = playlistId;
    }
    //Returns the playlistname
    public String getPlaylistName() 
    {
        return playlistName;
    }
    //Sets the id of the playlist
    public void setPlaylistName(String playlistName) 
    {
        this.playlistName = playlistName;
    }

    @Override
    public String toString() 
    {
        return playlistName;
    }

}
