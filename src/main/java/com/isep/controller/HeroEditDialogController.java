package com.isep.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.isep.model.Hero;
import java.util.Objects;

/**
 * Class HeroEditDialogController
 */
public class HeroEditDialogController {

    //
    // @FXML Fields
    //
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox typeField;

    //
    // Fields
    //
    private Stage dialogStage;
    private Hero hero;
    private boolean okClicked = false;

    //
    // @FXML Methods
    //
    @FXML
    private void initialize() {
        typeField.getItems().addAll("Hunter", "Healer", "Mage", "Warrior");
    }

    @FXML
    private void ok() {
        if (isInputValid()) {
            if (Objects.equals(typeField.getSelectionModel().getSelectedItem().toString(), "Hunter")){
                hero.setName(nameField.getText());
                hero.setType(1);
            } else if (Objects.equals(typeField.getSelectionModel().getSelectedItem().toString(), "Healer")) {
                hero.setName(nameField.getText());
                hero.setType(2);
            } else if (Objects.equals(typeField.getSelectionModel().getSelectedItem().toString(), "Mage")) {
                hero.setName(nameField.getText());
                hero.setType(3);
            } else if (Objects.equals(typeField.getSelectionModel().getSelectedItem().toString(), "Warrior")) {
                hero.setName(nameField.getText());
                hero.setType(4);
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }

    //
    // Methods
    //
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name";
        }
        if (typeField.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Please select a type of hero";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please correct invalid fields !");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}