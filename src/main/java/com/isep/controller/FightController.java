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
    @FXML
    private TableView<History> historyTable;
    @FXML
    private TableColumn<History, String> historyColumn;
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
    private MainApp mainApp;
    private int fightingHeroIndex;
    private int fightingEnemyIndex;
    private int playerTurn;
    private int userShouldEnd;
    private int round;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        round = 0;
        userShouldEnd = 0;
        historyTable.setItems(mainApp.getHistoryData());
    }

    //
    // @FXML Methods
    //
    @FXML
    private void initialize() {
        historyColumn.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
    }

    @FXML
    void actionButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(buttonPlay)) {
            if (round != 0) {
                if (userShouldEnd == 1) {
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
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getStage());
                    alert.setTitle("ERROR");
                    alert.setHeaderText("The fight has already started !");
                    alert.showAndWait();
                }
            } else {
                launch();
            }
        } else if (event.getSource().equals(buttonAttack)) {
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
    public void launch() {
        round++;
        generateCombat();
        update();
        mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " against " + mainApp.getEnemyData().get(fightingEnemyIndex).getName() + " ..."));
        if (playerTurn == 0) { //le héros commence
            mainApp.getHistoryData().add(new History(mainApp.getHeroData().get(fightingHeroIndex).getName() + " starts ..."));
        } else if (playerTurn == 1) { //l'énemi commence
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