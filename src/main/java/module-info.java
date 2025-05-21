module com.javafx.nutrimaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.base;

    requires okhttp3;
    requires com.google.gson;

    requires java.sql;
    requires annotations;
    opens com.javafx.nutrimaker to javafx.fxml;
    exports com.javafx.nutrimaker;
}