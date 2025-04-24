module com.javafx.nutrimaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.base;

    requires java.sql;

    opens com.javafx.nutrimaker to javafx.fxml;
    exports com.javafx.nutrimaker;
}