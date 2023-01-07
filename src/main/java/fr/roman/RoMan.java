package fr.roman;


import fr.roman.controleurs.metiers.CtrlClient;
import fr.roman.controleurs.metiers.CtrlCommande;
import fr.roman.modeles.Adresse;
import fr.roman.modeles.Client;
import fr.roman.modeles.Commande;
import javafx.application.Application;
import javafx.stage.Stage;
import fr.roman.vues.metiers.*;

/**
 * Classe principale de l'application, utilisée comme point d'entrée pour l'exécution.
 */
public class RoMan extends Application {
  public static void main(String[] args) {
    Application.launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    double[] coord = new double[2];
    coord[0] = 13;
    coord[1] = 78;
    Adresse adresse = new Adresse(2, coord, "Febvotte", 98, "pas loin du dentiste", "chp", "bouleveard", 898298, "Tours");
    Client client = new Client(1, "michel", "jean", "098897897678", "arobasse", 876868, true, adresse);

    VueClient vueClient = new VueClient(client);
    CtrlClient ctrlClient = new CtrlClient(vueClient);
    vueClient.setCtrl(ctrlClient);

    /*int idAdresse,
    double[] coordonneesGPS,
    String libelle,
    int numeroVoie,
    String complementNumero,
    String voie,
    String complementAdresse,
    int codePostal,
    String ville*/

    /*int idClient,
    String nom,
    String prenom,
    String tel,
    String mail,
    int siret,
    boolean particulier,
    Adresse adresse*/
  }
}