module com.example.projetfrogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.projetfrogger to javafx.fxml;
    exports com.example.projetfrogger;

    exports graphicalElements;
    opens graphicalElements to javafx.graphics;
}