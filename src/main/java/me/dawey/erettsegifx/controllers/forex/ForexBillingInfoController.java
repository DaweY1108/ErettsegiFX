package me.dawey.erettsegifx.controllers.forex;

import com.oanda.v20.account.AccountSummary;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import me.dawey.erettsegifx.models.oanda.Oanda;

public class ForexBillingInfoController {
    @FXML
    private TextArea data;

    @FXML
    private void initialize() {
        Oanda oanda = new Oanda();
        AccountSummary accountSummary = oanda.getAccountSummary();

        data.setText(
                "Account ID: " + accountSummary.getId() + "\n" +
                "Account Balance: " + accountSummary.getBalance() + "\n" +
                "Account Currency: " + accountSummary.getCurrency() + "\n" +
                "Account Created: " + accountSummary.getCreatedTime() + "\n" +
                "Account Alias: " + accountSummary.getAlias() + "\n" +
                "Account Unrealized PL: " + accountSummary.getUnrealizedPL() + "\n" +
                "Account NAV: " + accountSummary.getNAV() + "\n" +
                "Account Margin Used: " + accountSummary.getMarginUsed() + "\n" +
                "Account Margin Available: " + accountSummary.getMarginAvailable() + "\n" +
                "Account Margin Closeout: " + accountSummary.getMarginCloseoutNAV() + "\n" +
                "Account Margin Closeout Percent: " + accountSummary.getMarginCloseoutPercent() + "\n" +
                "Account Margin Closeout Position Value: " + accountSummary.getMarginCloseoutPositionValue() + "\n" +
                "Account Withdrawal Limit: " + accountSummary.getWithdrawalLimit() + "\n" +
                "Account Margin Call Margin Used: " + accountSummary.getMarginCallMarginUsed() + "\n" +
                "Account Margin Call Percent: " + accountSummary.getMarginCallPercent() + "\n" +
                "Account Last Transaction ID: " + accountSummary.getLastTransactionID() + "\n"
        );
    }
}
