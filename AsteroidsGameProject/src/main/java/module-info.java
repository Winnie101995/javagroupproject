module application.asteroidsgameproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.media;

    opens application.asteroidsgameproject to javafx.fxml;
    exports application.asteroidsgameproject;
}