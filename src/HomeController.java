import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeController implements Initializable{

    @FXML
    Label nameLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
     
        String user = LoginController.user.getUsername();

        nameLabel.setText("Hello " + user);
    }

}