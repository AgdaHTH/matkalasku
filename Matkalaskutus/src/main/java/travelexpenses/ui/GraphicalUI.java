/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import travelexpenses.dao.CreateDatabase;
import travelexpenses.dao.DatabaseBillDao;
import travelexpenses.dao.DatabaseUserDao;
import travelexpenses.domain.Bill;

import travelexpenses.domain.TravelExpensesApp;
import travelexpenses.domain.User;

/**
 *
 * @author Hilla
 */
public class GraphicalUI extends Application {

    private TravelExpensesApp application;
    private Scene loginScene;
    private Scene newUserScene;
    private Scene expensesScene;
    private Scene logoutScene;

    @Override
    public void init() throws Exception {
        DatabaseUserDao userdao = new DatabaseUserDao();
        DatabaseBillDao billdao = new DatabaseBillDao();
        this.application = new TravelExpensesApp(userdao, billdao);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        Label welcome = new Label("Welcome! Please login or create a new user:");
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        TextField usernameInput = new TextField();

        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Username:");

        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("Login");
        Button createUserButton = new Button("Create a new user");

        loginPane.getChildren().addAll(welcome, loginMessage, inputPane, loginButton, createUserButton);

        loginButton.setOnAction(e -> {

            String username = usernameInput.getText();
            //menuLabel.setText(username + " logged in...");

            if (application.login(username)) {
                loginMessage.setText("");
                primaryStage.setScene(expensesScene);
                usernameInput.setText("");
            } else {
                usernameInput.setText("");
                loginMessage.setText("Username not found!");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createUserButton.setOnAction(e -> {
            primaryStage.setScene(newUserScene);
        });

        //uuden käyttäjän luominen
        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username:");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newSurnamePane = new HBox(10);
        newSurnamePane.setPadding(new Insets(10));

        TextField newSurnameInput = new TextField();
        Label newSurnameLabel = new Label("Surname:");
        newSurnameLabel.setPrefWidth(100);

        newSurnamePane.getChildren().addAll(newSurnameLabel, newSurnameInput);

        HBox newForenamePane = new HBox(10);
        newForenamePane.setPadding(new Insets(10));
        TextField newForenameInput = new TextField();
        Label newForenameLabel = new Label("Forename:");
        newForenameLabel.setPrefWidth(100);

        newForenamePane.getChildren().addAll(newForenameLabel, newForenameInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));

        // createNewUserButtonin toiminta
        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String surname = newSurnameInput.getText();
            String forename = newForenameInput.getText();
            User user = new User(surname, forename, username);

            if (username.length() == 2 || surname.length() < 2) { //MUUTA
                userCreationMessage.setText("Username or name too short");
                userCreationMessage.setTextFill(Color.RED);
            } else try {
                if (this.application.createUser(user)) { //palauttaa false jos tulee SQLEx
                    userCreationMessage.setText("");
                    loginMessage.setText("New user created!");
                    loginMessage.setTextFill(Color.GREEN);
                    primaryStage.setScene(loginScene);
                } else {
                    userCreationMessage.setText("username has to be unique");
                    userCreationMessage.setTextFill(Color.RED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicalUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //uuden käyttäjän näkymä
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newSurnamePane,
                newForenamePane, createNewUserButton);
        newUserScene = new Scene(newUserPane, 300, 250);

        // uuden laskun luominen        
        VBox newBillPane = new VBox(15);
        TextField destinationInput = new TextField();
        TextField startDateInput = new TextField();
        TextField endDateInput = new TextField();
        TextField expensesInput = new TextField();

        Label destinationLabel = new Label("Destination:");
        Label startDateLabel = new Label("Start date (YYYY-MM-DD):");
        Label endDateLabel = new Label("End date (YYYY-MM-DD):");
        Label expensesLabel = new Label("Expenses:");
        //Label allowance = new Label(); ? kenttä johon lasketaan päiväraha
        //Label abroad = new Label(); tämän kanssa nappi kotimaassa / ulkomailla

        Button createBillButton = new Button("Create a travel expenses statement");
        newBillPane.setPadding(new Insets(20));
        newBillPane.getChildren().addAll(destinationLabel, destinationInput, startDateLabel, startDateInput,
                endDateLabel, endDateInput, expensesLabel, expensesInput, createBillButton);

        createBillButton.setOnAction(e -> {
            User currentUser = application.getCurrentUser();
            int user_id = currentUser.getId();
            String newdestination = destinationInput.getText();
            String newStartDate = startDateInput.getText();
            String newEndDate = endDateInput.getText();

            String[] parts1 = newStartDate.split("-");
            int year1 = Integer.valueOf(parts1[0]);
            int month1 = Integer.valueOf(parts1[1]);
            int day1 = Integer.valueOf(parts1[2]);
            LocalDate beginning = LocalDate.of(year1, month1, day1);

            String[] parts2 = newEndDate.split("-");
            int year2 = Integer.valueOf(parts2[0]);
            int month2 = Integer.valueOf(parts1[1]);
            int day2 = Integer.valueOf(parts1[2]);
            LocalDate end = LocalDate.of(year2, month2, day2);

            Double newexpenses = Double.valueOf(expensesInput.getText());
            //kulut eivät vielä mene tietokantaan!
            Bill bill = new Bill(newdestination, beginning, end);
            if (this.application.addBill(bill)) {
                primaryStage.setScene(logoutScene);
                //jos onnistui niin uusi näkymä jossa ilmoitetaan tämä
                //ja kysytään lopetetaanko vai palataanko alkuun tms.
            } //jos ei niin Something went wrong

        });

        VBox logoutPane = new VBox(10);
        Label billCreatedMessage = new Label("The travel statement was created succesfully!");
        Button logoutButton = new Button("Logout");
        Label closeWindowLabel = new Label("");
        logoutPane.getChildren().addAll(billCreatedMessage, logoutButton, closeWindowLabel);

        logoutButton.setOnAction(e -> {
            application.logout();
            closeWindowLabel.setText("You can now close this window");
        });

        expensesScene = new Scene(newBillPane);
        logoutScene = new Scene(logoutPane, 300, 250);

        primaryStage.setTitle("Travel expenses");
        loginScene = new Scene(loginPane, 300, 250);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(application.getCurrentUser());
            if (application.getCurrentUser() != null) {
                e.consume();
            }

        });

    }

    @Override
    public void stop() throws SQLException {
        this.application.closeDatabaseConnection();
        System.out.println("Application closes!");
    }

    public static void main(String[] args) {
        //tietokanta luodaan
        CreateDatabase database = new CreateDatabase();
        launch(args);
    }
}
