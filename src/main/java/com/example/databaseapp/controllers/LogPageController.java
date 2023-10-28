package com.example.databaseapp.controllers;

import com.example.databaseapp.appUtils.LogInUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LogPageController {
    @FXML
    private Text idUserText;

    @FXML
    private Button logButton;

    @FXML
    private Text logStatus;

    @FXML
    private Text passUserText;

    @FXML
    private ImageView regPageImag;

    @FXML
    private Button registButton;

    @FXML
    private TextField userIdTextField;

    @FXML
    private PasswordField userPassTextField;

    @FXML
    private void LogIn() {
        if (userIdTextField.getText().isEmpty() | userPassTextField.getText().isEmpty()) {
            logStatus.setText("Заполните все поля!");
        } else {
            LogInUtils utils = new LogInUtils(Integer.parseInt(userIdTextField.getText().toString()), userPassTextField.getText().toString());
                if (utils.checkInputInfo()) {

            } else {
                logStatus.setText("Неправильный ID или пароль");
            }
        }
    }
}
