/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
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

    public List<Song> getPlaylistSongs(int id) throws SQLException {
        List<Song> newSongList = new ArrayList();
        try ( Connection con = dbCon.getConnection()) {
            String query = "SELECT * FROM SongPlaylistRelation INNER JOIN Song ON SongPlaylistRelation.SongId = song.id WHERE SongPlaylistRelation.PlaylistId = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Song song = new Song(rs.getString("title"), rs.getString("place"));
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

    public Song addToPlaylist(Playlist playlist, Song song) throws SQLException {
        String sql = "INSERT INTO SongPlaylistRelation(PlaylistId,SongId) VALUES (?,?);";
        int Id = -1;
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            Id = getNewestSongInPlaylist(playlist.getPlaylistId()) + 1;
            ps.setInt(1, playlist.getPlaylistId());
            ps.setInt(2, song.getId());
            ps.addBatch();
            ps.executeBatch();
            return song;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void deleteFromPlaylistSongEverything(Song selectedSong) throws SQLServerException, SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String query = "DELETE from SongPlaylistRelationship WHERE songId =?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, selectedSong.getId());
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private int getNewestSongInPlaylist(int id) throws SQLServerException, SQLException {
        int newestID = -1;
        try ( Connection con = dbCon.getConnection()) {
            String query = "SELECT TOP(1) * FROM SongPlaylistRelation WHERE PlaylistId = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                newestID = rs.getInt("locationInListID");
            }
            System.out.println(newestID);
            return newestID;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return newestID;
        } catch (SQLException ex) {
            System.out.println(ex);
            return newestID;
        }
    }
}
