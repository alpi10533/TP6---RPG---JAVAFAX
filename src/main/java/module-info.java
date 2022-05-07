module com.isep.rpg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.security.jgss;

    opens com.isep to javafx.fxml;
    exports com.isep;
    exports com.isep.controller;
    opens com.isep.controller to javafx.fxml;
}