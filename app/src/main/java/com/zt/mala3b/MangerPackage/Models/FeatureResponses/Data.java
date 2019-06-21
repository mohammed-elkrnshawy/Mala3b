
package com.zt.mala3b.MangerPackage.Models.FeatureResponses;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Data {

    @Expose
    private List<Attribute> attributes;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}
