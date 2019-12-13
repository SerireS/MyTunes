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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zpotify.be.Playlist;
import zpotify.dal.DalException;

/**
 *
 * @author jigzi
 */
public class PlaylistDBDAO {
    
    private DatabaseConnector dbCon;

    public PlaylistDBDAO() throws IOException   {
        dbCon = new DatabaseConnector();
    }
    
    public List<Playlist> getAllPlaylists() throws SQLServerException, SQLException 
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Playlists;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Playlist> allPlaylists = new ArrayList<>();
            System.out.println(rs);
            while (rs.next())
            {
                int playlistId = rs.getInt("playlistId");
                String playlistName = rs.getString("playlistName");
                Playlist playlist = new Playlist(playlistId, playlistName);
                allPlaylists.add(playlist);
            }
            return allPlaylists;
        
    }    }
    //Deletes the song from SQL database
    public void deletePlaylist(Playlist playlist) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = playlist.getPlaylistId();
            String sql = "DELETE FROM Playlists WHERE PlaylistId=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    public boolean createPlaylist(String playlistName) throws DalException {
        //System.out.println(playlistName);
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Playlists (playlistName) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, playlistName);
            int affectedRows = ps.executeUpdate();
        
            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            }
            System.out.println("Tried creating playlist");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException();
        }
        return false;
    }
    
    public boolean updatePlaylist(String title, int id) throws SQLServerException, SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "UPDATE Playlists SET playlistName = ? WHERE PlaylistId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("It worked or atleast I think it does");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
