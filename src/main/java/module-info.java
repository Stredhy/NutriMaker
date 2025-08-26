module com.nutrimaker {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.nutrimaker to javafx.fxml;
    exports com.nutrimaker;
}
