package mx.wedevelop.model;

/**
 * Created by colorado on 23/02/17.
 */
public class Guest {
    private int id;
    private String name;
    private String picture;

    public Guest(int id, String name) {
        this.id = id;
        this.name = name;
        this.picture = "http://loremflickr.com/240/240/dog?random=" + id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
