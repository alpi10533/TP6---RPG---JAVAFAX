package com.isep.controller;

import com.isep.model.Hero;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * Class AwardEditDialogController
 */
public class AwardEditDialogController {

    //
    // @FXML Fields
    //
    @FXML
    private ComboBox awardField;

    //
    // Fields
    //
    private Stage dialogStage;
    private Hero hero;

    //
    // @FXML Methods
    //
    @FXML
    private void initialize() {
        awardField.getItems().addAll("Upgrade armor", "Upgrade weaponDamages", "Add potions", "Add foods");
    }

    @FXML
    private void ok() {
        if (isInputValid()) {
            if (Objects.equals(awardField.getSelectionModel().getSelectedItem().toString(), "Upgrade armor")){
                hero.upgradeArmor();
            } else if (Objects.equals(awardField.getSelectionModel().getSelectedItem().toString(), "Upgrade weaponDamages")) {
                hero.upgradeWeapon();
            } else if (Objects.equals(awardField.getSelectionModel().getSelectedItem().toString(), "Add potions")) {
                hero.addPotions();
            } else if (Objects.equals(awardField.getSelectionModel().getSelectedItem().toString(), "Add foods")) {
                hero.addFoods();
            }
            dialogStage.close();
        }
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (awardField.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Please select a type of award";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please correct invalid fields !");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
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

}


