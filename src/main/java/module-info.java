module com.example.projetfrogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.projetfrogger to javafx.fxml;
    exports com.example.projetfrogger;
}