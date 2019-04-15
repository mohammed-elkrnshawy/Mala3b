
package com.zt.mala3b.MangerPackage.Models.FeatureResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Attribute {

    @Expose
    private int id;
    @Expose
    private String image;
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
