/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import zpotify.be.Song;
import zpotify.dal.SongDAO;

/**
 *
 * @author nbruu
 */
public class SongDBDAO extends SongDAO {

    private DatabaseConnector dbCon;

    public SongDBDAO() throws IOException   {
        dbCon = new DatabaseConnector();
    }

    public List<Song> getAllSongs() throws SQLServerException, SQLException 
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Songs;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Song> allSongs = new ArrayList<>();
            System.out.println(rs);
            while (rs.next())
            {
                int id = rs.getInt("songId");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                int length = rs.getInt("length");
                String place = rs.getString("place");
                Song song = new Song(id, title, artist, length, place);
                allSongs.add(song);
            }
            return allSongs;
        
    }    }
            
   

}
