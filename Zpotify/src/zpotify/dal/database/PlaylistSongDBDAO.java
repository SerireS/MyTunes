/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import zpotify.be.Playlist;
import zpotify.be.Song;

/**
 *
 * @author jigzi
 */
public class PlaylistSongDBDAO {

    private DatabaseConnector dbCon;
    //Constructor, creating a new object. The DatabaseConnector
    public PlaylistSongDBDAO() throws IOException   {
        dbCon = new DatabaseConnector();
    }
    /*
    * If called this method tries to create a connection between the database and the program. 
    * It creates a new ArrayList which is a list of songs
    * If it creates the connection it will run the String query you see below.
    * The query shows a list of songs on a playlist. The sent down Id will be the PlaylistId selected.
    * Effectively returning the list of songs, in the selected playlist.
    */
    public List<Song> getPlaylistSongs(int id) throws SQLException {
        System.out.println("Vi nåede ind i model Getall");
        List<Song> newSongList = new ArrayList();
        try ( Connection con = dbCon.getConnection()) {
            String query = "SELECT * FROM SongPlaylistRelation INNER JOIN Songs ON SongPlaylistRelation.songId = songs.songId WHERE SongPlaylistRelation.PlaylistId = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            System.out.println("almindeligt tekst" + rs);
            while (rs.next()) {
                int songId = rs.getInt("songId");
                String title = rs.getString("title");
                String place = rs.getString("place");
                Song song = new Song(songId, title, place);
                newSongList.add(song);
            }
            return newSongList;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        
    }
    /*
    * If called this method tries to create a connection between the database and the program. 
    * If it creates the connection it will run the String query you see below.
    * This query if given playlistId and songId will add the songId's connection to the playlistId. 
    * Effectively adding the song to the playlist.
    */
    public Song addToPlaylist(Playlist playlist, Song song) throws SQLException {
        
        String sql = "INSERT INTO SongPlaylistRelation(PlaylistId,SongId) VALUES (?,?);";
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlist.getPlaylistId());
            ps.setInt(2, song.getId());
            ps.addBatch();
            ps.executeBatch();
            System.out.println(song.getId());
            return song;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    /*
    * If called this method tries to create a connection between the database and the program. 
    * If it creates the connection it will run the String query you see below.
    * This query if given playlistId and songId will remove the songId's connection to the playlistId. 
    * Effectively removing the song from the playlist.
    */
    public void deleteFromPlaylistSong(Playlist playlist, Song song) throws SQLServerException, SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String query = "DELETE FROM SongPlaylistRelation WHERE songId =? AND playlistId =?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, song.getId());
            preparedStmt.setInt(2, playlist.getPlaylistId());
            preparedStmt.execute();
            System.out.println("hm" + song.getId());
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
