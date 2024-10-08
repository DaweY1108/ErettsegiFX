package me.dawey.erettsegifx.models.mnbank;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import me.dawey.erettsegifx.models.mnbank.data.Day;
import me.dawey.erettsegifx.models.mnbank.data.ExchangeData;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.Rate;
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
            System.out.println(service.getExchangeRates("2022-08-14", "2022-09-14", "EUR"));
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    private MNBExchangeRates getExchangeRates(String startDate, String endDate, String currency) {
        try {
            JAXBContext context = JAXBContext.newInstance(MNBExchangeRates.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MNBExchangeRates rates = (MNBExchangeRates) unmarshaller.unmarshal(new StringReader(service.getExchangeRates(startDate, endDate, currency)));
            return rates;
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }

    public List<ExchangeData> getExchangeDatas(String startDate, String endDate, String currency) {
        MNBExchangeRates rates = getExchangeRates(startDate, endDate, currency);
        List<ExchangeData> exchangeDatas = new ArrayList<>();
        for (Day day : rates.getDays()) {
            Rate rate = day.getRate();
            exchangeDatas.add(
                    new ExchangeData(day.getDate(), rate.getCurr(), rate.getUnit(), rate.getValue())
            );
        }
        return exchangeDatas;
    }
}
