/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zpotify.be.Playlist;

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
}
