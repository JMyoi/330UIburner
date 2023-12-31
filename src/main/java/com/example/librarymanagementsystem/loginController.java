package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;
    @FXML
    Parent root;
    Library library = new Library();

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String UserName = userNameText.getText();
        String pass = passwordText.getText();
        if(library.logIn(UserName, pass)){
            if(library.getCurrentUserType().equals("M")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberMenu.fxml"));
                root = loader.load();
                memberMenuController membermenuController = loader.getController();
                membermenuController.displayMemberMenu( library);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if (library.getCurrentUserType().equals("L")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LibrarianMenu.fxml"));
                root = loader.load();
                LibraryMenuController librarymenucontroller = loader.getController();
                librarymenucontroller.displayLibrarianMenu(library);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            System.out.println("current user is "+library.getCurrentUserName());
        }
        else{
            errorLabel.setText("Incorrect credentials.");
            System.out.println("Incorrect credentials");
        }
    }
    public void receiveLibrary(Library lib){
        library = lib;
        System.out.println("library received in login controller");
    }

    public void switchSceneToCreateAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccountPage.fxml"));
        root = loader.load();
        CreateAccountPageController createaccountpagecontroller = loader.getController();
        createaccountpagecontroller.receieveLibrary(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
