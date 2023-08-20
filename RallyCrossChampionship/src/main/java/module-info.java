module com.example.rallycrosschampionship {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.rallycrosschampionship to javafx.fxml;
    exports com.example.rallycrosschampionship;
}