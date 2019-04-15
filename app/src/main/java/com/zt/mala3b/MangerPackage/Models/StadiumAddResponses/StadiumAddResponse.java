
package com.zt.mala3b.MangerPackage.Models.StadiumAddResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class StadiumAddResponse {

    @Expose
    private Data data;
    @Expose
    private Boolean status;
    @Expose
    private String msg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }
}
