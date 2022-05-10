package com.isep.controller;

import com.isep.MainApp;
import com.isep.model.History;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Class FightController
 */
public class FightController {

    //
    // @FXML Fields
    //

    // on y stockera un tableau d'historiques
    @FXML
    private TableView<History> historyTable;

    // on y stockera une colonne de contenus d'historiques
    @FXML
    private TableColumn<History, String> historyColumn;

    // variables nécessaires pour afficher les détails du combat
    @FXML
    private Label fightLabel;
    @FXML
    private Label nameHeroLabel;
    @FXML
    private Label typeHeroLabel;
    @FXML
    private Label lifePointsHeroLabel;
    @FXML
    private Label weaponDamagesHeroLabel;
    @FXML
    private Label foodsHeroLabel;
    @FXML
    private Label potionsHeroLabel;
    @FXML
    private Label nameEnemyLabel;
    @FXML
    private Label typeEnemyLabel;
    @FXML
    private Label lifePointsEnemyLabel;
    @FXML
    private Label weaponDamagesEnemyLabel;

    // variables nécessaires pour afficher les actions possibles du héro
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonAttack;
    @FXML
    private Button buttonHeal;
    @FXML
    private Button buttonEat;
    @FXML
    private Button buttonEnd;

    //
    // Fields
    //

    // on y stockera les données de l'application
    private MainApp mainApp;

    // on y stockera l'index du héros combattant
    private int fightingHeroIndex;

    // on y stockera l'index de l'ennemi combattant
    private int fightingEnemyIndex;

    // on y stockera le joueur dont s'est le tour
    private int playerTurn; // 0 = le héro | 1 = l'ennemi

    // on y stockera si la partie semble terminée
    private int userShouldEnd; // 0 = non | 1 = oui, il a perdu | 2 = oui, il a gagné

    // on y stockera le numéro du tour
    private int round;

    //
    // @FXML Methods
    //

    // cette méthode est appelée automatiquement après le chargement de la vue (cf. "FightLayout")
    // elle permet l'initialisation des champs du tableau
    @FXML
    private void initialize() {
        historyColumn.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
    }

    // cette méthode est également appelée automatiquement
    // elle permet d'écouter les 5 boutons (PLAY, ATTACK, EAT, HEAL, END) (cf. "FightLayout")
    @FXML
    void actionButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(buttonPlay)) { // si le bouton PLAY est cliqué
            if (round != 0) { // si la partie a commencé
                if (userShouldEnd == 1) { // s'il a perdu
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getStage());
                    alert.setTitle("ERROR");
                    alert.setHeaderText("The fight is finished : you loose");
                    alert.setContentText("Please click on END !");
                    alert.showAndWait();
                } else if (userShouldEnd == 2) { // s'il a gagné
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getStage());
                    alert.setTitle("ERROR");
                    alert.setHeaderText("The fight is finished : you won");
                    alert.setContentText("Please click on END !");
                    alert.showAndWait();
                } else { // sinon
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getStage());
                    alert.setTitle("ERROR");
                    alert.setHeaderText("The fight has already started !");
                    alert.showAndWait();
                }
            } else { // si la partie n'a pas commencé
                launch(); // on lance le premier tour
            }
        } else if (event.getSource().equals(buttonAttack)) { // si le bouton ATTACK est cliqué
            if (round == 0) { // si la partie n'a pas commencé
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight has not already started");
                alert.setContentText("Please click on PLAY !");
                alert.showAndWait();
            } else if (userShouldEnd == 1) { // s'il a perdu
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight is finished : you loose");
                alert.setContentText("Please click on END !");
                alert.showAndWait();
            } else if (userShouldEnd == 2) { // s'il a gagné
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight is finished : you won");
                alert.setContentText("Please click on END !");
                alert.showAndWait();
            } else if (playerTurn != 0) { // si ce n'est pas encore à son tour de jouer
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("It is not your turn yet !");
                alert.showAndWait();
            } else if (mainApp.getHeroData().size() != 0) { // s'il reste encore des héros
                if (mainApp.getHeroData().get(fightingHeroIndex).getLifePoints() <= 0) { // si le héros combattant n'a plus de points de vie
                    mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " eliminated ..."));
                    mainApp.getHeroData().remove(fightingHeroIndex); // on supprime ce héro
                    if (mainApp.getHeroData().size() != 0) { // s'il reste encore d'autres héros
                        launch(); // on lance un autre tour
                    } else {
                        update();
                    }
                } else {
                    mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " attacks ..."));
                    mainApp.getEnemyData().get(fightingEnemyIndex).deleteLifePoints(mainApp.getHeroData().get(fightingHeroIndex).attack());
                    update();
                    playerTurn = 1;
                    if (mainApp.getEnemyData().size() != 0) {
                        if (mainApp.getEnemyData().get(fightingEnemyIndex).getLifePoints() <= 0) {
                            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " eliminated ..."));
                            mainApp.getEnemyData().remove(fightingEnemyIndex);
                            getAward();
                            if (mainApp.getEnemyData().size() != 0) {
                                launch();
                            } else {
                                update();;
                            }
                        } else {
                            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " attacks ..."));
                            mainApp.getHeroData().get(fightingHeroIndex).deleteLifePoints(mainApp.getEnemyData().get(fightingEnemyIndex).attack());
                            update();
                            playerTurn = 0;
                        }
                    } else {
                        update();
                    }
                }
            } else {
                update();
            }
        } else if (event.getSource().equals(buttonHeal)) {
            if (round == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight has not already started");
                alert.setContentText("Please click on PLAY !");
                alert.showAndWait();
            } else if (userShouldEnd == 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight is finished : you loose");
                alert.setContentText("Please click on END !");
                alert.showAndWait();
            } else if (userShouldEnd == 2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight is finished : you won");
                alert.setContentText("Please click on END !");
                alert.showAndWait();
            } else if (playerTurn != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("It is not your turn yet !");
                alert.showAndWait();
            } else if (mainApp.getHeroData().get(fightingHeroIndex).getSizeOfPotions() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("You don't have any potions");
                alert.setContentText("Please chose an other option !");
                alert.showAndWait();
            } else if (mainApp.getHeroData().size() != 0) {
                if (mainApp.getHeroData().get(fightingHeroIndex).getLifePoints() <= 0) {
                    mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " eliminated ..."));
                    mainApp.getHeroData().remove(fightingHeroIndex);
                    if (mainApp.getHeroData().size() != 0) {
                        launch();
                    } else {
                        update();
                    }
                } else {
                    mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " heals ..."));
                    mainApp.getHeroData().get(fightingHeroIndex).heal();
                    update();
                    playerTurn = 1;
                    if (mainApp.getEnemyData().size() != 0) {
                        if (mainApp.getEnemyData().get(fightingEnemyIndex).getLifePoints() <= 0) {
                            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " eliminated ..."));
                            mainApp.getEnemyData().remove(fightingEnemyIndex);
                            getAward();
                            System.out.println(mainApp.getHeroData().get(fightingHeroIndex).getSizeOfFoods());
                            if (mainApp.getEnemyData().size() != 0) {
                                launch();
                            } else {
                                update();;
                            }
                        } else {
                            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " attacks ..."));
                            mainApp.getHeroData().get(fightingHeroIndex).deleteLifePoints(mainApp.getEnemyData().get(fightingEnemyIndex).attack());
                            update();
                            playerTurn = 0;
                        }
                    } else {
                        update();
                    }
                }
            } else {
                update();
            }
        } else if (event.getSource().equals(buttonEat)) {
            if (round == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight has not already started");
                alert.setContentText("Please click on PLAY !");
                alert.showAndWait();
            } else if (userShouldEnd == 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight is finished : you loose");
                alert.setContentText("Please click on END !");
                alert.showAndWait();
            } else if (userShouldEnd == 2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("The fight is finished : you won");
                alert.setContentText("Please click on END !");
                alert.showAndWait();
            } else if (playerTurn != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("It is not your turn yet !");
                alert.showAndWait();
            } else if (mainApp.getHeroData().get(fightingHeroIndex).getSizeOfFoods() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getStage());
                alert.setTitle("ERROR");
                alert.setHeaderText("You don't have any foods");
                alert.setContentText("Please chose an other option !");
                alert.showAndWait();
            } else if (mainApp.getHeroData().size() != 0) {
                if (mainApp.getHeroData().get(fightingHeroIndex).getLifePoints() <= 0) {
                    mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " eliminated ..."));
                    mainApp.getHeroData().remove(fightingHeroIndex);
                    if (mainApp.getHeroData().size() != 0) {
                        launch();
                    } else {
                        update();
                    }
                } else {
                    mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " heals ..."));
                    mainApp.getHeroData().get(fightingHeroIndex).eat();
                    update();
                    playerTurn = 1;
                    if (mainApp.getEnemyData().size() != 0) {
                        if (mainApp.getEnemyData().get(fightingEnemyIndex).getLifePoints() <= 0) {
                            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " eliminated ..."));
                            mainApp.getEnemyData().remove(fightingEnemyIndex);
                            getAward();
                            System.out.println(mainApp.getHeroData().get(fightingHeroIndex).getSizeOfFoods());
                            if (mainApp.getEnemyData().size() != 0) {
                                launch();
                            } else {
                                update();;
                            }
                        } else {
                            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " attacks ..."));
                            mainApp.getHeroData().get(fightingHeroIndex).deleteLifePoints(mainApp.getEnemyData().get(fightingEnemyIndex).attack());
                            update();
                            playerTurn = 0;
                        }
                    } else {
                        update();
                    }
                }
            } else {
                update();
            }
        } else if (event.getSource().equals(buttonEnd)){
            System.exit(0);
        }
    }

    //
    // Methods
    //

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        round = 0;
        userShouldEnd = 0;
        historyTable.setItems(mainApp.getHistoryData());
    }

    public void launch() {
        round++;
        generateCombat();
        update();
        mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " against " + mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " ..."));
        if (playerTurn == 0) { // le héros commence
            mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " starts ..."));
        } else if (playerTurn == 1) { // l'énemi commence
            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " starts ..."));
            mainApp.getHistoryData().add(new History(mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " attacks ..."));
            mainApp.getHeroData().get(fightingHeroIndex).deleteLifePoints(mainApp.getEnemyData().get(fightingEnemyIndex).attack());
            playerTurn = 0;
            update();
        }
    }

    public void generateCombat() {
        playerTurn = (int) (Math.random() * 2);
        fightingHeroIndex = (int)(Math.random() * mainApp.getHeroData().size());
        fightingEnemyIndex = (int)(Math.random() * mainApp.getEnemyData().size());
    }

    public void update() {
        if (mainApp.getHeroData().size() == 0 || mainApp.getEnemyData().size() == 0) {
            if (mainApp.getHeroData().size() == 0) {
                nameHeroLabel.setText("");
                typeHeroLabel.setText("");
                lifePointsHeroLabel.setText("");
                weaponDamagesHeroLabel.setText("");
                foodsHeroLabel.setText("");
                potionsHeroLabel.setText("");
                nameEnemyLabel.setText(mainApp.getEnemyData().get(fightingEnemyIndex).getName());
                typeEnemyLabel.setText(mainApp.getEnemyData().get(fightingEnemyIndex).getType());
                lifePointsEnemyLabel.setText(Integer.toString(mainApp.getEnemyData().get(fightingEnemyIndex).getLifePoints()));
                weaponDamagesEnemyLabel.setText(Integer.toString(mainApp.getEnemyData().get(fightingEnemyIndex).getWeaponDamages()));
                fightLabel.setText("YOU LOSE");
                userShouldEnd = 1;
            } else if (mainApp.getEnemyData().size() == 0) {
                nameHeroLabel.setText(mainApp.getHeroData().get(fightingHeroIndex).getName());
                typeHeroLabel.setText(mainApp.getHeroData().get(fightingHeroIndex).getType());
                lifePointsHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getLifePoints()));
                weaponDamagesHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getWeaponDamages()));
                foodsHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getSizeOfFoods()));
                potionsHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getSizeOfPotions()));
                nameEnemyLabel.setText("");
                typeEnemyLabel.setText("");
                lifePointsEnemyLabel.setText("");
                weaponDamagesEnemyLabel.setText("");
                fightLabel.setText("YOU WIN");
                userShouldEnd = 2;
            }
        } else {
            nameHeroLabel.setText(mainApp.getHeroData().get(fightingHeroIndex).getName());
            typeHeroLabel.setText(mainApp.getHeroData().get(fightingHeroIndex).getType());
            lifePointsHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getLifePoints()));
            weaponDamagesHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getWeaponDamages()));
            foodsHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getSizeOfFoods()));
            potionsHeroLabel.setText(Integer.toString(mainApp.getHeroData().get(fightingHeroIndex).getSizeOfPotions()));
            nameEnemyLabel.setText(mainApp.getEnemyData().get(fightingEnemyIndex).getName());
            typeEnemyLabel.setText(mainApp.getEnemyData().get(fightingEnemyIndex).getType());
            lifePointsEnemyLabel.setText(Integer.toString(mainApp.getEnemyData().get(fightingEnemyIndex).getLifePoints()));
            weaponDamagesEnemyLabel.setText(Integer.toString(mainApp.getEnemyData().get(fightingEnemyIndex).getWeaponDamages()));
            fightLabel.setText("FIGHT N°" + round);
        }
    }

    private void getAward() {
        mainApp.showAwardEditDialog(mainApp.getHeroData().get(fightingHeroIndex));
        update();
    }

}