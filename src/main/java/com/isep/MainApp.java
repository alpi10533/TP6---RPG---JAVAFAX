package com.isep;

import com.isep.controller.FightController;
import com.isep.controller.AwardEditDialogController;
import com.isep.controller.HeroEditDialogController;
import com.isep.controller.HeroController;
import com.isep.model.Enemy;
import com.isep.model.Hero;
import com.isep.model.History;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class MainApp
 */
public class MainApp extends Application {

    //
    // Fields
    //
    private Stage stage;
    private ObservableList<Hero> heroData = FXCollections.observableArrayList();
    private ObservableList<Enemy> enemyData = FXCollections.observableArrayList();
    private ObservableList<History> historyData = FXCollections.observableArrayList();

    //
    // Constructors
    //
    public MainApp() {
    }

    //
    // @Override Methods
    //
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/RootLayout.fxml")));
            stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
            stage.setTitle("Mini RPG lite 3000");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //
    // Methods
    //
    public void switchToHeroLayout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/HeroLayout.fxml"));
        Parent root = loader.load();
        HeroController controller = loader.getController();
        controller.setMainApp(this);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
        stage.setTitle("Mini RPG lite 3000");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public boolean showHeroEditDialog(Hero hero) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/HeroEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
            dialogStage.setTitle("Add a new hero");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            HeroEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setHero(hero);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showAwardEditDialog(Hero hero) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AwardEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
            dialogStage.setTitle("Choose your award");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AwardEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setHero(hero);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToFight() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FightLayout.fxml"));
        Parent root = loader.load();
        FightController controller = loader.getController();
        controller.setMainApp(this);
        stage = new Stage();
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
        stage.setTitle("Mini RPG lite 3000");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Hero> getHeroData() {
        return heroData;
    }

    public ObservableList<Enemy> getEnemyData() {
        return enemyData;
    }

    public ObservableList<History> getHistoryData() {
        return historyData;
    }

    public Stage getStage() {
        return stage;
    }

}