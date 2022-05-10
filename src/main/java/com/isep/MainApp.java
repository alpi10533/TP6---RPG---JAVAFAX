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

    // on y stockera la stage de notre application
    private Stage stage;

    // on utilise des listes observables pour que la vue soit toujours synchronisée avec les données de l'application

    // on y stockera les données des héros de notre application
    private ObservableList<Hero> heroData = FXCollections.observableArrayList();
    // on y stockera les données des ennemis de notre application
    private ObservableList<Enemy> enemyData = FXCollections.observableArrayList();
    // on y stockera l'historique des actions des héros et ennemis de notre application
    private ObservableList<History> historyData = FXCollections.observableArrayList();

    //
    // Constructors
    //
    public MainApp() {
    }

    //
    // @Override Methods
    //

    // initialisation de la première scène
    @Override
    public void start(Stage stage) {
        try {
            // on charge la vue "RootLayout"
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

    // la méthode "switchToHeroLayout" permet de passer à la seconde scène (même stage)
    // cette méthode est appelée quand le bouton START est cliqué (cf. "RootLayout")
    public void switchToHeroLayout(ActionEvent event) throws IOException {
        // on charge la vue "HeroLayout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/HeroLayout.fxml"));
        Parent root = loader.load();
        // on associe à la vue le contrôleur "HeroController"
        HeroController controller = loader.getController();
        // on appelle la méthode "setMainApp" du contrôleur "HeroController" en passant comme paramètre notre application "MainApp"
        // cette méthode est appelée pour récupérer les données importantes de l'application (notamment "heroData", "enemyData" et historyData")
        controller.setMainApp(this);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
        stage.setTitle("Mini RPG lite 3000");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    // la méthode "showHeroEditDialog" permet d'ouvrir une fenêtre modale (nouvelle stage en +) pour l'ajout d'un nouvel héro
    // cette méthode prend comme paramètre un héro temporaire
    // et retourne un booléen
    // elle sera appelé par la fonction "addHero" du contrôleur "HeroController"
    // cette fonction sera fonction sera elle-même appelée quand le bouton "NEW" est cliqué (cf. "HeroLayout" )
    public boolean showHeroEditDialog(Hero tempHero) {
        try {
            // on charge la vue "HeroEditDialog"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/HeroEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
            dialogStage.setTitle("Add a new hero");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // on associe à la vue le contrôleur "HeroEditDialog"
            HeroEditDialogController controller = loader.getController();
            // on appelle la méthode "setDialogStage" du contrôleur "HeroEditDialog" en passant comme paramètre notre nouvelle stage "dialogStage"
            // cette méthode est appelée pour récupérer les données de la stage
            controller.setDialogStage(dialogStage);
            // on appelle la méthode "setHero" du contrôleur "HeroEditDialog" en passant comme paramètre notre héro temporaire "tempHero"
            // cette méthode est appelée pour récupérer les données du héro
            controller.setHero(tempHero);
            dialogStage.showAndWait();
            return controller.isOkClicked(); // TRUE si l'ajout d'un nouvel héro est confirmé
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    // la méthode "showHeroEditDialog" permet de changer de fenêtre (nouvelle stage) pour les combats
    public void switchToFight() throws IOException {
        // on charge la vue "FightLayout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FightLayout.fxml"));
        Parent root = loader.load();
        // on associe à la vue le contrôleur "FightController"
        FightController controller = loader.getController();
        // on appelle la méthode "setMainApp" du contrôleur "FightController" en passant comme paramètre notre application "MainApp"
        // cette méthode est appelée pour récupérer les données importantes de l'application (notamment "heroData", "enemyData" et historyData")
        controller.setMainApp(this);
        stage = new Stage();
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
        stage.setTitle("Mini RPG lite 3000");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    // la méthode "showAwardEditDialog" permet d'ouvrir une fenêtre modale (nouvelle stage en +) pour le choix d'une récompense
    // cette méthode prend comme paramètre un héro
    // elle sera appelé par la fonction "getAward" du contrôleur "FightController"
    // cette fonction sera fonction sera elle-même appelée automatiquement quand un héro gagne un combat
    public void showAwardEditDialog(Hero hero) {
        try {
            // on charge la vue "AwardEditDialog"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AwardEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2619/2619285.png"));
            dialogStage.setTitle("Choose your award");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // on associe à la vue le contrôleur "AwardEditDialogController"
            AwardEditDialogController controller = loader.getController();
            // on appelle la méthode "setDialogStage" du contrôleur "AwardEditDialogController" en passant comme paramètre notre nouvelle stage "dialogStage"
            // cette méthode est appelée pour récupérer les données de la stage
            controller.setDialogStage(dialogStage);
            // on appelle la méthode "setHero" du contrôleur "AwardEditDialogController" en passant comme paramètre notre héro vainqueur "hero"
            // cette méthode est appelée pour récupérer les données du héro
            controller.setHero(hero);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // point d'entrée de l'application
    public static void main(String[] args) {
        launch(args);
    }

    //
    // Accessor Methods
    //
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