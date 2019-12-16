/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.bll;

import zpotify.be.Playlist;
import zpotify.be.Song;
import zpotify.dal.database.PlaylistSongDBDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jigzi
 */
public class PlaylistSongManager
{
    private PlaylistSongDBDAO playlistSongDBdao;

    public PlaylistSongManager() throws IOException
    {

        playlistSongDBdao = new PlaylistSongDBDAO();
    }

    //This is what the model calls when it tries to show the Songs in a single playlist. This calls a method in the PlaylistSongDBdao
    public List<Song> getPlaylistSongs(int id) throws SQLException
    {

        return playlistSongDBdao.getPlaylistSongs(id);

    }

    //This is what the model calls when deleting a song from a playlist. This calls a method in the PlaylistSongDBdao
    public void deleteFromPlaylistSong(Playlist playlist, Song song) throws SQLException
    {
        playlistSongDBdao.deleteFromPlaylistSong(playlist, song);
    }

    //This is what the model calls when adding a song to a playlist. This calls a method in the PlaylistSongDBdao
    public void addToPlaylist(Playlist playlist, Song song) throws SQLException

    {
        playlistSongDBdao.addToPlaylist(playlist, song);
    }

}
