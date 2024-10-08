package me.dawey.erettsegifx.models.mnbank.data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "MNBExchangeRates")
public class MNBExchangeRates {
    private List<Day> days;

    @XmlElement(name = "Day")
    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}