module com.texteditor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.texteditor to javafx.fxml;
    exports com.texteditor;
}
