module GUI
{
    requires PacmanReinvented;
    requires javafx.controls;
    requires javafx.fxml;
    opens UI;
    exports UI;
}