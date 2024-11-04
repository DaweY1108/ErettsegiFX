package me.dawey.erettsegifx.models.mnbank.data;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "MNBCurrentExchangeRates")
public class MNBCurrentExchangeRates {

    private Day day;

    @XmlElement(name = "Day")
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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
