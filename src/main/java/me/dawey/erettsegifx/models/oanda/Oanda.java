package me.dawey.erettsegifx.models.oanda;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;

import java.util.Date;
import java.util.List;

public class Oanda {
    private static final String url = "https://api-fxpractice.oanda.com";
    private static final AccountID accountID = new AccountID("101-004-30070413-001");
    private static final String token = "84c6afbec99a536c7ee145a1621ebc1c-0fa7b8303562c3be9c0bb9dee0f56b5f";
    private Context ctx;
    private List<String> instruments = List.of("EUR_USD", "USD_HUF", "EUR_HUF", "USD_JPY", "GBP_USD", "USD_CAD", "AUD_USD", "NZD_USD", "USD_CHF");

    public Oanda() {
        ctx = new Context(url, token);
    }

    public List<String> getInstruments() {
        return instruments;
    }

    /**
     *Szamlainformaciok lekerese
     */
    public AccountSummary getAccountSummary() {
        try {
            return ctx.account.summary(accountID).getAccount();
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
            return null;
        }
    }

    /**
     *Aktualis arak lekerese
     */
    public PricingGetResponse getPricing() {
        PricingGetRequest request = new PricingGetRequest(accountID, instruments);
        try {
            return ctx.pricing.get(request);
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
            return null;
        }
    }

    /**
     *Historikus adatok lekerese
     */
    public InstrumentCandlesResponse getHistory(String instrument) {
        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument));
        try {
            return ctx.instrument.candles(request);
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
            return null;
        }
    }
}
