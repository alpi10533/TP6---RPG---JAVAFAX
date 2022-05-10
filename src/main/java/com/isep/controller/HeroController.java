package com.isep.controller;

import com.isep.MainApp;
import com.isep.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class HeroController
 */
public class HeroController {

    //
    // @FXML Fields
    //

    // on y stockera un tableau de héros
    @FXML
    private TableView<Hero> heroTable;

    // on y stockera une colonne de noms de héros
    @FXML
    private TableColumn<Hero, String> nameColumn;

    // on y stockera une colonne de types de héros
    @FXML
    private TableColumn<Hero, String> typeColumn;

    // variables nécessaires pour afficher les détails d'un héros
    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label lifePointsLabel;
    @FXML
    private Label weaponDamagesLabel;
    @FXML
    private Label foodsLabel;
    @FXML
    private Label potionsLabel;

    //
    // Fields
    //

    // on y stockera les données de l'application
    private MainApp mainApp;

    //
    // Constructors
    //
    public HeroController() {
    }

    //
    // @FXML Methods
    //

    // cette méthode est appelée automatiquement après le chargement de la vue (cf. "HeroLayout")
    // elle permet l'initialisation des champs du tableau
    // elle permet également l'appel de la méthode "showHeroDetails" au clic d'un élement du tableau
    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        showHeroDetails(null);
        heroTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showHeroDetails(newValue));
    }

    // cette méthode est appelée quand le bouton DELETE est cliqué (cf. "HeroLayout")
    // elle permet de supprimer un héros après l'avoir sélectionner dans le tableau
    // si aucun héro est sélectionné un message d'erreur apparait quand le bouton DELETE est cliqué
    // sinon on met à jour les données
    @FXML
    private void deleteHero() {
        int selectedIndex = heroTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            heroTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getStage());
            alert.setTitle("WARNING");
            alert.setHeaderText("No hero selected");
            alert.setContentText("Please select a hero in the table !");
            alert.showAndWait();
        }
    }

    // cette méthode est appelée quand le bouton ADD est cliqué (cf. "HeroLayout")
    // elle permet d'ajouter un héros via une fenêtre modale de dialogue
    // si les informations saisies sont incorrectes un message d'erreur apparait quand le bouton OK est cliqué (cf. "HeroEditDialog")
    // sinon on met à jour les données
    @FXML
    private void addHero() {
        Hero tempHero = new Hero();
        // on passe à la saisie des informations du nouvel héros via une fenêtre modale
        boolean okClicked = mainApp.showHeroEditDialog(tempHero);
        if (okClicked) {
            if (Objects.equals(tempHero.getType(), "Hunter")) {
                mainApp.getHeroData().add(new Hunter(tempHero.getName()));
            } else if (Objects.equals(tempHero.getType(), "Healer")) {
                mainApp.getHeroData().add(new Healer(tempHero.getName()));
            } else if (Objects.equals(tempHero.getType(), "Mage")) {
                mainApp.getHeroData().add(new Mage(tempHero.getName()));
            } else if (Objects.equals(tempHero.getType(), "Warrior")) {
                mainApp.getHeroData().add(new Warrior(tempHero.getName()));
            }
        }
    }

    // cette méthode est appelée quand le bouton NEXT est cliqué (cf. "HeroLayout")
    // elle permet de créer les ennemis et de passer aux combats via une nouvelle fenêtre
    // si aucun héros n'a été ajouté un message d'erreur apparait quand le bouton NEXT est cliqué (cf. "HeroLayout")
    // sinon on met à jour les données et on passe aux combats
    @FXML
    private void startFight(ActionEvent event) throws IOException {
        // on crée le même nombre d'ennemis que de héros
        if (mainApp.getHeroData().size() != 0) {
            // aléatoirement et avec une probabilité plus importante d'un ennemi de type Basic que de type Boss
            int max = 10;
            int min = 1;
            int range = max - min + 1;
            for (int i = 0; i < mainApp.getHeroData().size(); i++) {
                int rand = (int)(Math.random() * range) + min;
                if (rand == 8 || rand == 9 || rand == 10){
                    String nameBoss = "Boss N°" + i;
                    mainApp.getEnemyData().add(new Boss(nameBoss));
                } else {
                    String nameBasic = "Basic N°" + i;
                    mainApp.getEnemyData().add(new Basic(nameBasic));
                }
            }
            // on passe aux combats via une nouvelle fenêtre
            mainApp.switchToFight();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getStage());
            alert.setTitle("ERROR");
            alert.setHeaderText("No hero added");
            alert.setContentText("Please add a hero in the table !");
            alert.showAndWait();
        }

    }

    //
    // Methods

    // cette méthode est appelée pour récupérer les données importantes de l'application (notamment "heroData", "enemyData" et historyData")
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        heroTable.setItems(mainApp.getHeroData());
    }

    // cette méthode est appelée lorsqu'un héro du tableau est cliqué
    // elle permet d'afficher le détail des informations du héro
    private void showHeroDetails(Hero hero) {
        if (hero != null) {
            nameLabel.setText(hero.getName());
            typeLabel.setText(hero.getType());
            lifePointsLabel.setText(Integer.toString(hero.getLifePoints()));
            weaponDamagesLabel.setText(Integer.toString(hero.getWeaponDamages()));
            foodsLabel.setText(Integer.toString(hero.getSizeOfFoods()));
            potionsLabel.setText(Integer.toString(hero.getSizeOfPotions()));
        } else {
            nameLabel.setText("");
            typeLabel.setText("");
            lifePointsLabel.setText("");
            weaponDamagesLabel.setText("");
            foodsLabel.setText("");
            potionsLabel.setText("");
        }
    }

}
