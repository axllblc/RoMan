package fr.roman.authentification;

import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.awt.*;

public class VueAuthentification {


    private final Scene scene;

    private final Label labelConnexion;

    private final Label labelErreurConnexion;

    private PasswordField passwordField;

    private TextField textFieldIdentifiant;

    private final Button btnConnexion;

    private final VBox vBox;


    public VueAuthentification(Stage stage){

        textFieldIdentifiant = new TextField();
        passwordField = new PasswordField();
        btnConnexion = new Button("Connexion");
        labelErreurConnexion = new Label("L'indentifiant et/ou le mot de passe est/sont incorrect(s)");
        labelConnexion = new Label("Connexion");
        vBox = new VBox();
        scene = new Scene(vBox);

        stuctureAuthentification(stage);
    }

    public void stuctureAuthentification(Stage stage){

        // Ajout chaine de caratère grisée dans les TextFields
        textFieldIdentifiant.setPromptText("Identifiant");
        passwordField.setPromptText("Mot de passe");

        // Gestion taille des éléments de la scène
        textFieldIdentifiant.setMaxWidth(200);
        passwordField.setMaxWidth(200);
        btnConnexion.setMaxWidth(200);

        // Gestion espacements + alignement dans la box
        vBox.setSpacing(10);
        Insets padding = new Insets(20);
        vBox.setPadding(padding);
        vBox.setAlignment(Pos.CENTER);

        // Ajout des éléments dans la scène
        vBox.getChildren().add(labelConnexion);
        vBox.getChildren().add(textFieldIdentifiant);
        vBox.getChildren().add(passwordField);
        vBox.getChildren().add(btnConnexion);

        // Gestion scène
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setTitle("RoMan");


        // Affichage scène
        stage.show();

    }



    public void close(){

    }

    public void erreurSaisie(){

    }


}
