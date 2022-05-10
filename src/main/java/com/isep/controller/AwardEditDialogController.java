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

    // on y stockera le type de récompense
    @FXML
    private ComboBox awardField;

    //
    // Fields
    //

    // on y stockera la stage de notre fenêtre modale
    private Stage dialogStage;

    // on y stockera le héros recevant sa récompense après avoir gagné son combat
    private Hero hero;

    //
    // @FXML Methods
    //

    // cette méthode est appelée automatiquement après le chargement de la vue (cf. "AwardEditDialog")
    // elle permet l'initialisation des champs de la combobox
    @FXML
    private void initialize() {
        awardField.getItems().addAll("Upgrade armor", "Upgrade weaponDamages", "Add potions", "Add foods");
    }

    // cette méthode est appelée quand le bouton OK est cliqué (cf. "AwardEditDialog")
    // elle permet de mettre à jour les données du héro et de fermer la fenêtre modale
    @FXML
    private void ok() {
        // on vérifie que la donnée saisie est correcte
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

    // cette méthode est appelée quand le bouton CANCEL est cliqué (cf. "AwardEditDialog")
    // elle permet de fermer la fenêtre modale
    @FXML
    private void cancel() {
        dialogStage.close();
    }

    //
    // Methods
    //

    // cette méthode est appelée dans le "MainApp" pour récupérer les données de la stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // cette méthode est appelée dans le "MainApp" pour récupérer les données du héro
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    // cette méthode est appelée pour vérifier la saisie du type de récompense
    private boolean isInputValid() {
        String errorMessage = "";
        // si le type de récompense n'a pas été sélectionné
        if (awardField.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Please select a type of award";
        }
        // si aucune erreur est détectée // sinon erreur
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

}


