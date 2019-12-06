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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import zpotify.be.Song;
import zpotify.dal.DalException;

/**
 *
 * @author nbruu
 */
public class SongDBDAO {

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
    //Deletes the song from SQL database
    public void deleteSong(Song song) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = song.getId();
            String sql = "DELETE FROM songs WHERE SongId=?;";
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
        
    public void createSong(String title, String place) throws DalException {
        System.out.println(title + place);
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Songs (title, place) VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
//            ps.setInt(2, length);
//            ps.setString(3, artist);
            ps.setString(2, place);
            int affectedRows = ps.executeUpdate();
        System.out.println("" +affectedRows);
            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    
                    Song song = new Song(title, place);
                    
                }
            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException();
        }
    }

   

}
