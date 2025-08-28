module com.yassin.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.yassin.javafx to javafx.fxml;
    exports com.yassin.javafx;
}