
package com.zt.mala3b.MangerPackage.Models.StadiumAddResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {

    @Expose
    private String message;
    @SerializedName("stadium_id")
    private Long stadiumId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

}
