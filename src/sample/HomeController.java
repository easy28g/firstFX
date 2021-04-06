package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView ImageButtonHome;

    @FXML
    void initialize() {
        assert ImageButtonHome != null : "fx:id=\"ImageButtonHome\" was not injected: check your FXML file 'app.fxml'.";

    }
}
