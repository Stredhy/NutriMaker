module com.javafx.nutrimaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.javafx.nutrimaker to javafx.fxml;
    exports com.javafx.nutrimaker;
}