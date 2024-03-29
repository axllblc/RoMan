package fr.roman.vues.metiers;

import fr.roman.RoManErreur;
import fr.roman.controleurs.metiers.CtrlClient;
import fr.roman.modeles.Client;
import fr.roman.modeles.Utilisateur;
import javafx.scene.control.Label;

import java.sql.SQLException;
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
   * Label introduction client.
   */
  private final Label introduction;


  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object client.
   */
  public VueClient(Client client, Utilisateur utilisateur) {
    super(utilisateur);
    nom = new Label("Nom : " + client.getNom());
    telephone = new Label("Téléphone : " + client.getTel());
    email = new Label("E-mail : " + client.getEmail());
    adresse = new Label("Adresse : " + client.getAdresse().getLibelle());
    introduction = new Label("Informations de : " + client.getNom());

    labels = new ArrayList<>();

    labels.add(nom);
    labels.add(telephone);
    labels.add(email);
    labels.add(adresse);




    structure(labels, introduction);

    btnOui.setOnAction((event) -> {
      try {
        ctrl.removeClient(client);
      } catch (SQLException e) {
        RoManErreur.afficher(e);
      }
      redirectionAccueil();
    });

    btnModifier.setOnAction((event) -> {
      try {
        close();
        ctrl.modification(utilisateur, client, utilisateur.getRole());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void setCtrl(CtrlClient ctrl) {
    this.ctrl = ctrl;
  }
}
