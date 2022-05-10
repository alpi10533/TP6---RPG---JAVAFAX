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

    // on y stockera le nom du nouvel héros
    @FXML
    private TextField nameField;

    // on y stockera le type du nouveau héros
    @FXML
    private ComboBox typeField;

    //
    // Fields
    //

    // on y stockera la stage de notre fenêtre modale
    private Stage dialogStage;

    // on y stockera le héros temporaire
    private Hero hero;

    // on y stockera un booléen
    // ce booléen dépendra de l'état du bouton "OK" (cf. "HeroEditDialog")
    private boolean okClicked = false;

    //
    // @FXML Methods
    //

    // cette méthode est appelée automatiquement après le chargement de la vue (cf. "HeroEditDialog")
    // elle permet l'initialisation des champs de la combobox
    @FXML
    private void initialize() {
        typeField.getItems().addAll("Hunter", "Healer", "Mage", "Warrior");
    }

    // cette méthode est appelée quand le bouton OK est cliqué (cf. "HeroEditDialog")
    // elle permet de mettre à jour les données du héro temporaire et de fermer la fenêtre modale
    @FXML
    private void ok() {
        // on vérifie que les données saisies sont correctes
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

    // cette méthode est appelée quand le bouton CANCEL est cliqué (cf. "HeroEditDialog")
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

    // cette méthode est appelée dans le "MainApp" pour récupérer les données du héro temporaire
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    // cette méthode est appelée pour récupérer l'état du bouton OK (cf. HeroEditDialog)
    public boolean isOkClicked() {
        return okClicked;
    }

    // cette méthode est appelée pour vérifier la saisie des données du héro
    private boolean isInputValid() {
        String errorMessage = "";
        // si le nom saisi est incorrect
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name";
        }
        //si le type de héro n'a pas été saisi
        if (typeField.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Please select a type of hero";
        }
        // si aucune erreur est détectée // sinon erreur
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