module com.example.projetfrogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    exports com.example.projetfrogger;

    exports graphicalElements;
    opens graphicalElements to javafx.graphics;

    exports TEST;
    opens TEST to org.junit.platform.commons;

    exports gameCommons;
    exports frog;
    exports util;
}