package me.dawey.erettsegifx.models.mnbank;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import me.dawey.erettsegifx.models.mnbank.data.MNBCurrentExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRatesQueryValues;
import soap.MNBArfolyamServiceSoap;
import soap.MNBArfolyamServiceSoapImpl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private final MNBArfolyamServiceSoap service;

    public BankManager() {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        service = impl.getCustomBindingMNBArfolyamServiceSoap();
    }

    public void printXML() {
        try {
            System.out.println(service.getExchangeRates("2021-01-01", "2021-02-02", "USD"));
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    private MNBExchangeRatesQueryValues getExchangeRatesQueryValues() {
        try {
            JAXBContext context = JAXBContext.newInstance(MNBExchangeRatesQueryValues.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (MNBExchangeRatesQueryValues) unmarshaller.unmarshal(new StringReader(service.getInfo()));
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }

    public List<String> getCurrencies() {
        return getExchangeRatesQueryValues().getCurrencies().getCurrencies();
    }

    private MNBCurrentExchangeRates getCurrentExchangeRates() {
        try {
            JAXBContext context = JAXBContext.newInstance(MNBCurrentExchangeRates.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (MNBCurrentExchangeRates) unmarshaller.unmarshal(new StringReader(service.getCurrentExchangeRates()));
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }

    public List<MNBCurrentExchangeRates.Rate> getCurrentExchangeRatesList() {
        return getCurrentExchangeRates().getDay().getRates();
    }

    private MNBExchangeRates getExchangeRates(String startDate, String endDate, String currency) {
        try {
            JAXBContext context = JAXBContext.newInstance(MNBExchangeRates.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (MNBExchangeRates) unmarshaller.unmarshal(new StringReader(service.getExchangeRates(startDate, endDate, currency)));
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }

    public List<MNBExchangeRates.Day> getExchangeRatesList(String startDate, String endDate, String currency) {
        return getExchangeRates(startDate, endDate, currency).getDays();
    }
}
