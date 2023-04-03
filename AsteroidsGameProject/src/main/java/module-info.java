module application.asteroidsgameproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens application.asteroidsgameproject to javafx.fxml;
    exports application.asteroidsgameproject;
    exports highscore;
    opens highscore to javafx.fxml;
}