/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal.database;

import zpotify.be.Playlist;
import zpotify.dal.DalException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Den Gode Gruppe
 */
public class PlaylistDBDAO
{

    private DatabaseConnector dbCon;

    public PlaylistDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /* 
    * If called this method will create a connection between the database and the program
    * The SQL statement will be run.
    * The method will return an ArrayList of playlists, which will be the list of all playlists in the database.
    */
    public List<Playlist> getAllPlaylists() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Playlists;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Playlist> allPlaylists = new ArrayList<>();
            while (rs.next())
            {
                int playlistId = rs.getInt("playlistId");
                String playlistName = rs.getString("playlistName");
                Playlist playlist = new Playlist(playlistId, playlistName);
                allPlaylists.add(playlist);
            }
            return allPlaylists;

        }
    }

    //Deletes the song from SQL database
    public void deletePlaylist(Playlist playlist) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = playlist.getPlaylistId();
            String sql = "DELETE FROM Playlists WHERE PlaylistId=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
                throw new DalException();
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    
    /* 
    * If called this method will create a connection between the database and the program
    * The SQL statement will be run.
    * A new playlist will be given with the name chosen.
    */
    public boolean createPlaylist(String playlistName) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Playlists (playlistName) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, playlistName);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    return true;
                }
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
        return false;
    }

    /* 
    * If called this method will create a connection between the database and the program
    * The SQL statement will be run.
    * the playlist with the chosen playlistId will have its name changed, to the chosen name.
    */
    public boolean updatePlaylist(String title, int id)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Playlists SET playlistName = ? WHERE PlaylistId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
