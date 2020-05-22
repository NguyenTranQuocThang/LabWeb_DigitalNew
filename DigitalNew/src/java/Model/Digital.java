/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 *
 * @author thang
 */
public class Digital {

    int id;
    String title;
    String image;
    String author;
    String shortDes;
    String description;
    Timestamp timePost;

    public Digital() {
    }

    public Digital(int id, String title, String image, String author, String shortDes, String description, Timestamp timePost) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.author = author;
        this.shortDes = shortDes;
        this.description = description;
        this.timePost = timePost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimePost() {
        return timePost;
    }

    public void setTimePost(Timestamp timePost) {
        this.timePost = timePost;
    }

    public String getDateFormat() {
        return new SimpleDateFormat("MMM dd yyy '-' H:m ").format(this.timePost)
                + new SimpleDateFormat("aaa ").format(this.timePost).toLowerCase();
    }
}
