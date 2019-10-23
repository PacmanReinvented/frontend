module GUI
{
    requires PacmanReinvented;
    requires javafx.controls;
    requires javafx.fxml;
    requires Logic;
    opens UI;
    exports UI;
}