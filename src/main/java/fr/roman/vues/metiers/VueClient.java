package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlClient;
import fr.roman.modeles.Client;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Classe de l'objet client, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueClient extends VueMetier {

  /**
   * Contrôleur de la vue Client.
   */
  private CtrlClient ctrl = null;

  /**
   * Nom du client.
   */
  private final Label nom;

  /**
   * Prénom du client.
   */
  private final Label prenom;

  /**
   * Numéro de téléphone du client.
   */
  private final Label telephone;

  /**
   * Adresse mail du client.
   */
  private final Label email;

  /**
   * Adresse du client.
   */
  private final Label adresse;

  /**
   * Tableau de label qui sera envoyé en paramètre à la méthode structure.
   */
  private final ArrayList<Label> labels;


  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object client.
   */
  public VueClient(Client client) {
    nom = new Label("Nom : " + client.getNom());
    prenom = new Label("Prénom : " + client.getPrenom());
    telephone = new Label("Téléphone : " + client.getTel());
    email = new Label("E-mail : " + client.getMail());
    adresse = new Label("Adresse : " + client.getAdresse().getLibelle());

    labels = new ArrayList<>();

    labels.add(nom);
    labels.add(prenom);
    labels.add(telephone);
    labels.add(email);
    labels.add(adresse);


    structure(labels);

    btnOui.setOnAction((event) -> {
      ctrl.removeClient(client);
    });
  }

  public void setCtrl(CtrlClient ctrl) {
    this.ctrl = ctrl;
  }
}
