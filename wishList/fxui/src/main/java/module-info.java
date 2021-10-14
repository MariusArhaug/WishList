module wishList.ui {
    requires com.fasterxml.jackson.databind;

    requires java.net.http;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires wishList.core;

    opens wishList.ui to javafx.graphics, javafx.fxml;
}
