package converter.view;

import converter.application.ConverterApp;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ConverterGUI extends Application {
    private ConverterApp controller;
    private final BorderPane layout = new BorderPane();
    private final HBox top = new HBox();
    private final Label databaseStatus = new Label();
    private final ComboBox<String> currencyFrom = new ComboBox<>();
    private final ComboBox<String> currencyTo = new ComboBox<>();
    private final VBox middle = new VBox();
    private final HBox conversion = new HBox();
    private final HBox result = new HBox();
    private final TextField currencyAmount = new TextField();
    private final Label resultLabel = new Label();
    private final TextField currencyResult = new TextField();
    private final Label amountLabel = new Label();

    public void start(Stage stage) {
        top.setAlignment(Pos.CENTER);
        top.setSpacing(25);

        layout.setTop(top);
        BorderPane.setMargin(top, new Insets(50, 5, 5, 5));

        databaseStatus.setText("Database status: OK");
        databaseStatus.setStyle("-fx-text-fill: green;");
        layout.setBottom(databaseStatus);
        BorderPane.setMargin(databaseStatus, new Insets(10, 10, 10, 10));

        middle.setAlignment(Pos.CENTER);
        middle.setSpacing(25);

        amountLabel.setText("Amount to Convert:");
        currencyAmount.setPromptText("Input amount");
        currencyAmount.setMaxWidth(500);
        currencyResult.setMaxWidth(500);
        currencyResult.setEditable(false);
        currencyResult.setPromptText("...");

        Button convertButton = new Button("Convert");
        convertButton.setMinWidth(100);

        conversion.setAlignment(Pos.CENTER);
        conversion.setSpacing(5);
        conversion.getChildren().addAll(amountLabel, currencyAmount);

        resultLabel.setText("Amount Converted:");
        result.setAlignment(Pos.CENTER);
        result.setSpacing(5);
        result.getChildren().addAll(resultLabel, currencyResult);

        middle.getChildren().addAll(conversion, result, convertButton);

        layout.setCenter(middle);

        Scene scene = new Scene(layout, 750, 380);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        convertButton.setOnAction(actionEvent -> {
            try {
                if (currencyAmount.getText().isEmpty()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                currencyResult.setText("Amount field is empty!");
            }

            try {
                double amount = Double.parseDouble(currencyAmount.getText());
                String from = currencyFrom.getValue();
                String to = currencyTo.getValue();
                if (from.equals(to)) {
                    setResult("No conversion needed!");
                } else {
                    try {
                        controller.convert(amount, from, to);
                    } catch (Exception e) {
                        databaseStatus.setText("ERROR! Database connection failed!");
                        databaseStatus.setStyle("-fx-text-fill: red;");
                    }
                }
            } catch (NumberFormatException e) {
                currencyResult.setText("Invalid input!");
            }
        });
        try {
            controller.passCurrencyNamesToGui();
        } catch (Exception e) {
            e.printStackTrace();
            databaseStatus.setText("ERROR! Database connection failed!");
            databaseStatus.setStyle("-fx-text-fill: red;");
        }
        stage.show();
    }

    public void setCurrencyNames(List<String> currencyNames) {
        Label fromLabel = new Label("Select currency from :");
        Label toLabel = new Label("Select currency to :");
        currencyFrom.setItems(FXCollections.observableArrayList(currencyNames));
        currencyTo.setItems(FXCollections.observableArrayList(currencyNames));
        currencyFrom.setValue(currencyNames.get(0));
        currencyTo.setValue(currencyNames.get(1));
        top.getChildren().addAll(fromLabel, currencyFrom, toLabel, currencyTo);

    }

    public void init() {
        controller = new ConverterApp(this);
    }

    public void setResult(String result) {
        currencyResult.setText(result);
    }
}
