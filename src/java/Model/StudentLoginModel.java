/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;

/**
 *
 * @author toni
 */
public class StudentLoginModel {
    
    private Blob image;
    private int id;

    public StudentLoginModel(Blob image, int id) {
        this.image = image;
        this.id = id;
    }

    
    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
