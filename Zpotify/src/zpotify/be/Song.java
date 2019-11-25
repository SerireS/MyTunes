package zpotify.be;


import java.io.Serializable;

/**
 * @author Jonas
 */
public class Song implements Serializable
{


    private final int id;
    private String title;
    private int length;
    private String artist;

    public Song(int id, String title, int year, String artist)
    {
        this.id = id;
        this.title = title;
        this.length = length;
        this.artist = artist;
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

    @Override
    public String toString()
    {
        return title + " (" + length + ")";
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
