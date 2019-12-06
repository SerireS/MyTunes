package zpotify.be;

import java.io.Serializable;

/**
 * @author Jonas
 */
public class Song implements Serializable
{

    private int id;
    private String title;
    private int length;
    private String artist;
    private String place;

    public Song(int id, String title, String artist, int length, String place)
    {
        this.place = place;
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.length = length;
    }

    public Song(String title, String place) {
        this.title = title;
        this.place = place;
    }
    
    

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Denne funktion returner "title - artist (længde)".
     **/
    @Override
    public String toString()
    {
        return title;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Song)
        {
            //Obj is a Song
            Song other = (Song) obj;
            return (this.id == other.getId());
        } else
        {
            //Obj is not a Song
            return super.equals(obj);
        }
    }

}
