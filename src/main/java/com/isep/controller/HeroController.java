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

/**
 * Class HeroController
 */
public class HeroController {

    //
    // @FXML Fields
    //
    @FXML
    private TableView<Hero> heroTable;
    @FXML
    private TableColumn<Hero, String> nameColumn;
    @FXML
    private TableColumn<Hero, String> typeColumn;
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
    private MainApp mainApp;

    //
    // Constructors
    //
    public HeroController() {
    }

    //
    // @FXML Methods
    //
    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        showHeroDetails(null);
        heroTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showHeroDetails(newValue));
    }

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

    @FXML
    private void addHero() {
        Hero tempHero = new Hero();
        boolean okClicked = mainApp.showHeroEditDialog(tempHero);
        if (okClicked) {
            mainApp.getHeroData().add(tempHero);
        }
    }

    @FXML
    private void startFight(ActionEvent event) throws IOException {
        if (mainApp.getHeroData().size() != 0) {
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
    //
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        heroTable.setItems(mainApp.getHeroData());
    }

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
