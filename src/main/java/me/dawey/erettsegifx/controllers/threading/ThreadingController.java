package me.dawey.erettsegifx.controllers.threading;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ThreadingController {

    @FXML
    private Label oneSecondLabel;

    @FXML
    private Label twoSecondLabel;

    private boolean running;
    private Thread oneSecondThread;
    private Thread twoSecondThread;

    @FXML
    private void initialize() {
        oneSecondLabel.setText("0");
        twoSecondLabel.setText("0");
    }

    public void threadingStartButton(ActionEvent actionEvent) {
        running = true;

        // 1 másodperces szál
        oneSecondThread = new Thread(() -> {
            int counter = 0;
            while (running) {
                counter++;
                int finalCounter = counter;
                Platform.runLater(() -> oneSecondLabel.setText(String.valueOf(finalCounter)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 2 másodperces szál
        twoSecondThread = new Thread(() -> {
            int counter = 0;
            while (running) {
                counter++;
                int finalCounter = counter;
                Platform.runLater(() -> twoSecondLabel.setText(String.valueOf(finalCounter)));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        oneSecondThread.start();
        twoSecondThread.start();
    }

    public void threadingStopButton(ActionEvent actionEvent) {
        running = false;

        if (oneSecondThread != null) {
            oneSecondThread.interrupt();
        }
        if (twoSecondThread != null) {
            twoSecondThread.interrupt();
        }
    }
}
