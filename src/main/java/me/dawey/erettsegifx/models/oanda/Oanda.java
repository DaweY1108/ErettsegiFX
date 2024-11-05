package me.dawey.erettsegifx.models.oanda;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.trade.Trade;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeSpecifier;
import com.oanda.v20.transaction.Transaction;
import com.oanda.v20.transaction.TransactionID;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Oanda {
    private static final String url = "https://api-fxpractice.oanda.com";
    private static final AccountID accountID = new AccountID("101-004-30070413-001");
    private static final String token = "84c6afbec99a536c7ee145a1621ebc1c-0fa7b8303562c3be9c0bb9dee0f56b5f";
    private Context ctx;
    private final List<String> instruments = List.of("EUR_USD", "USD_HUF", "EUR_HUF", "USD_JPY", "GBP_USD", "USD_CAD", "AUD_USD", "NZD_USD", "USD_CHF");

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
    public PricingGetResponse getPricing(String instrument) {
        PricingGetRequest request = new PricingGetRequest(accountID, List.of(instrument));
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
    public InstrumentCandlesResponse getHistory(String instrument, LocalDate startDate, LocalDate endDate) {
        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument));
        request.setFrom(new DateTime(startDate.toString()));
        request.setTo(new DateTime(endDate.toString()));
        request.setGranularity(CandlestickGranularity.D);

        try {
            return ctx.instrument.candles(request);
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
            return null;
        }
    }
    /*
     * Nyitott pozíciók lekérése
     */

    public List<Trade> getTrades() {
        try {
            return ctx.trade.list(accountID).getTrades();
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
            return null;
        }
    }

    public TransactionID openTrade(String instrument, int units) {
        InstrumentName instrumentName = new InstrumentName(instrument);
        try {
            OrderCreateRequest request = new OrderCreateRequest(accountID);
            MarketOrderRequest marketOrderRequest = new MarketOrderRequest();
            marketOrderRequest.setInstrument(instrumentName);
            marketOrderRequest.setUnits(units);
            request.setOrder(marketOrderRequest);
            OrderCreateResponse response = ctx.order.create(request);
            return response.getOrderFillTransaction().getId();
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
        }
        return null;
    }

    public boolean closeTrade(String id) {
        try {
            TradeCloseRequest request = new TradeCloseRequest(accountID, new TradeSpecifier(id));
            ctx.trade.close(request);
            return true;
        } catch (Exception e) {
            System.out.println("Hiba az Oanda API hivasakor! \nÜzenet: " + e.getMessage());
        }
        return false;
    }
}
