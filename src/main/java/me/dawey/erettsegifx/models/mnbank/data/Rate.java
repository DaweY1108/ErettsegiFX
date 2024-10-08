package me.dawey.erettsegifx.models.mnbank.data;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class Rate {
    private String unit;
    private String curr;
    private String value;

    @XmlAttribute(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlAttribute(name = "curr")
    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
