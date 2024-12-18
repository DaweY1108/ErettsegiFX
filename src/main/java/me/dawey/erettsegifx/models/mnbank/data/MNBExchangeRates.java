package me.dawey.erettsegifx.models.mnbank.data;

import jakarta.xml.bind.annotation.*;

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

    @XmlType(propOrder = {"date", "rates"})
    public static class Day {

        private String date;
        private List<Rate> rates;

        @XmlAttribute(name = "date")
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @XmlElement(name = "Rate")
        public List<Rate> getRates() {
            return rates;
        }

        public void setRates(List<Rate> rates) {
            this.rates = rates;
        }
    }

    @XmlType(propOrder = {"unit", "currency", "value"})
    public static class Rate {

        private int unit;
        private String currency;
        private String value;

        @XmlAttribute(name = "unit")
        public int getUnit() {
            return unit;
        }

        public void setUnit(int unit) {
            this.unit = unit;
        }

        @XmlAttribute(name = "curr")
        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @XmlValue
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}