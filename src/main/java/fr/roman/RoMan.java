package fr.roman;

import fr.roman.controleurs.authentification.CtrlAuthentification;
import fr.roman.controleurs.lieux.CtrlCarte;
import fr.roman.controleurs.lieux.rechercherLieu;
import fr.roman.modeles.Adresse;
import fr.roman.vues.authentification.VueAuthentification;
import fr.roman.vues.carte.PointCarte;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Classe principale de l'application, utilisée comme point d'entrée pour l'exécution.
 */
public class RoMan extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      /*
      // Instanciation de la vue d'authentification et de son contrôleur
      VueAuthentification vueAuthentification = new VueAuthentification(primaryStage);
      CtrlAuthentification ctrlAuthentification = new CtrlAuthentification(vueAuthentification);
      vueAuthentification.setCtrl(ctrlAuthentification);
      */

      // Démo API Adresse
      Adresse adresse = new Adresse(); // On utilise le métier Adresse
      adresse.setNumeroVoie(31); // numéro
      adresse.setVoie("avenue monge"); // rue
      adresse.setCodePostal(37200); // code postal
      adresse.setVille("tours"); // ville
      ArrayList<Adresse> resultatAPIAdresse = rechercherLieu.rechercher(adresse); // recherche
      System.out.println("Résultat de recherche adresse via API Adresse :");
      // On affiche le resultat dans le terminal
      resultatAPIAdresse.forEach(a -> System.out.println(a.toString()));

      // Démo GluonMaps
      CtrlCarte carte = new CtrlCarte(); // On crée la carte
      // On ajoute les résultats de la recherche à la carte
      resultatAPIAdresse.forEach(a ->
      {
        PointCarte pointCarte = new PointCarte(a.getCoordonneesGPS()[0], a.getCoordonneesGPS()[1]);
        carte.ajouterPoint(pointCarte);
      });
      System.setProperty("javafx.platform", "DESKTOP"); // plateforme utilisée : ordinateur
      Scene scene = new Scene(carte.getCarte(), 800,400);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      // En cas d'exception, une fenêtre d'erreur est affichée
      RoManErreur.afficher(e);
      e.printStackTrace();
    }
  }

}