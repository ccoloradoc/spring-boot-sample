package mx.wedevelop.model;

import javax.persistence.*;

/**
 * Created by colorado on 23/02/17.
 */
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Version
    private int version;

    private String name;
    private String picture;
    private int age;

    public Guest() {

    }

    public Guest(int id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public Guest(String name, int age, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPicture() {
        return picture;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
