module gui.thedrake1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens gui.thedrake1 to javafx.fxml;
    exports gui.thedrake1;
    exports gui.thedrake1.mechanics;
    opens gui.thedrake1.mechanics to javafx.fxml;
}