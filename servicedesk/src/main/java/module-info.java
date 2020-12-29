module mas.emma.servicedesk {
    requires javafx.controls;
    requires javafx.fxml;

    opens mas.emma.servicedesk to javafx.fxml;
    exports mas.emma.servicedesk;
}
