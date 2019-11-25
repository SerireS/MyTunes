package zpotify.be;


import java.io.Serializable;

/**
 *
 * @author Jonas
 */
public class Song implements Serializable
{
    
    
    private final int id;
    private String title;
    private int lenght;

    public Song(int id, String title, int year)
    {
        this.id = id;
        this.title = title;
        this.lenght = lenght;
    }

    /**
     * Get the value of year
     *
     * @return the value of year
     */
    public int getYear()
    {
        return lenght;
    }

    /**
     * Set the value of year
     *
     * @param year new value of year
     */
    public void setYear(int lenght)
    {
        this.lenght = lenght;
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
        return title + " (" + lenght + ")";
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
