module GUI
{
    requires PacmanReinvented;
    requires Interfaces;
    requires javafx.fxml;
    requires Logic;
    requires javafx.graphics;
    opens UI;
    exports UI;
}