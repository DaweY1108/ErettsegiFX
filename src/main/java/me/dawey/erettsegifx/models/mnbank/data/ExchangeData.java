package me.dawey.erettsegifx.models.mnbank.data;

public class ExchangeData {
    private String date;
    private String currency;
    private String unit;
    private String value;

    public ExchangeData(String date, String currency, String unit, String value) {
        this.date = date;
        this.currency = currency;
        this.unit = unit;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }
}
