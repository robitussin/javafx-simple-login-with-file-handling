import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Scene1Controller {

    @FXML
    TextField nametextfield;

    @FXML
    TextField passwordtextfield;

    @FXML
    Button loginButton;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginButtonHandler(ActionEvent event) throws IOException {

        // Get text from text field
        String username = nametextfield.getText();
        String password = passwordtextfield.getText();  

        User user = new User(username, password);

        File accountsfile = new File("accounts.txt");

        if (accountsfile.createNewFile()) {
            System.out.println("File created: " + accountsfile.getName());
        } else {
            System.out.println("File already exists.");
        } 

        Scanner filescanner = new Scanner(accountsfile);
        while (filescanner.hasNextLine()) {

            String data = filescanner.nextLine();
   
            String username_from_file = data.split(",")[0];
            String password_from_file = data.split(",")[1];

            if (username_from_file.equals(user.getUsername()) && password_from_file.equals(user.getPassword())) {
                
                System.out.println("Login successful");

                // Load Scene2.fxml when login button is clicked
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
                // Load Scene2Controller
                root = loader.load();
                Scene2Controller scene2Controller = loader.getController();
                // Pass username from textfield to displayName() method
                scene2Controller.displayName(username);

                // Load stage and scene
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            } 
        }

        filescanner.close();
    }
}