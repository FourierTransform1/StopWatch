package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;


public class StopWatch implements Initializable {

    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button resetButton;
    @FXML private Label hourLabel;
    @FXML private Label minuteLabel;
    @FXML private Label secondLabel;

    static int hours = 0;
    static int minutes = 0;
    static int seconds = 0;
    static boolean state;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleStartButton(ActionEvent event) {
        state = true;
        startButton.setDisable(true);
        Thread t = new Thread(){


            @Override
            public void run() {


                for(;;){

                    if(state == true) {

                        try {
                            sleep(1000);
                            if(seconds > 59) {
                                seconds = 00;
                                minutes++;
                            }
                            if(minutes > 60) {
                                seconds = 00;
                                minutes = 00;
                                hours++;
                            }

                            seconds++;
                            //System.out.println(seconds);

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    secondLabel.setText("" + seconds);
                                    //System.out.println("after seconds");
                                    minuteLabel.setText("" + minutes);
                                    hourLabel.setText("" + hours);

                                }
                            });

                        } catch (Exception e) {

                        }

                    } else {
                        break;
                    }
                }
            }

        };

        t.start();

    }

    public void handleStopButton(ActionEvent event) {

        state = false;
        startButton.setDisable(false);
    }

    public void handleResetButton(ActionEvent event) {

        startButton.setDisable(false);
        state = false;
        seconds = 0;
        minutes = 0;
        hours = 0;

        secondLabel.setText("00");
        minuteLabel.setText("00");
        hourLabel.setText("00");
    }
}
