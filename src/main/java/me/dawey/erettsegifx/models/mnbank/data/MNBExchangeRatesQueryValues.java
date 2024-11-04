package me.dawey.erettsegifx.models.mnbank.data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlRootElement(name = "MNBExchangeRatesQueryValues")
@XmlType(propOrder = {"firstDate", "lastDate", "currencies"})
public class MNBExchangeRatesQueryValues {

    private String firstDate;
    private String lastDate;
    private Currencies currencies;

    @XmlElement(name = "FirstDate")
    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    @XmlElement(name = "LastDate")
    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @XmlElement(name = "Currencies")
    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }

    @XmlType(propOrder = {"currencies"})
    public static class Currencies {

        private List<String> currencies;

        @XmlElement(name = "Curr")
        public List<String> getCurrencies() {
            return currencies;
        }

        public void setCurrencies(List<String> currencies) {
            this.currencies = currencies;
        }
    }
}
