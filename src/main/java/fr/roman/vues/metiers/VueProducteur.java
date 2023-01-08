package fr.roman.vues.metiers;

import fr.roman.RoManErreur;
import fr.roman.controleurs.metiers.CtrlProducteur;
import fr.roman.modeles.Producteur;
import java.util.ArrayList;

import fr.roman.modeles.Utilisateur;
import javafx.scene.control.Label;


/**
 * Classe de l'objet producteur, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueProducteur extends VueMetier {

  /**
   * Contrôleur de la vue Producteur.
   */
  private CtrlProducteur ctrl = null;

  /**
   * Tableau de label qui sera envoyé en paramètre à la méthode structure.
   */
  private final ArrayList<Label> labels;

  /**
   * Siret du producteur.
   */
  private final Label siret;

  /**
   * Numéro de téléphone du producteur.
   */
  private final Label telephone;

  /**
   * Adresse du producteur.
   */
  private final Label adresse;

  /**
   * Nom du producteur.
   */
  private final Label nom;

  /**
   * Prénom du producteur.
   */
  private final Label prenom;

  /**
   * E-mail du producteur.
   */
  private final Label email;

  /**
   * Label introduction producteur
   */
  private final Label introduction;


  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object producteur.
   */
  public VueProducteur(Producteur producteur, Utilisateur utilisateur) {
    super(utilisateur);
    siret = new Label("Siret : " + producteur.getSiret());
    telephone = new Label("Téléphone : " + producteur.getTel());
    adresse = new Label("Adresse : " + producteur.getAdresse().getLibelle());
    nom = new Label("Nom : " + producteur.getUtilisateur().getNom());
    prenom = new Label("Prenom : " + producteur.getUtilisateur().getPrenom());
    email = new Label("E-mail : " + producteur.getUtilisateur().getEmail());
    introduction = new Label("Informations de : " + producteur.getUtilisateur().getNom() + " "
        + producteur.getUtilisateur().getPrenom());


    labels = new ArrayList<>();

    labels.add(siret);
    labels.add(telephone);
    labels.add(adresse);
    labels.add(nom);
    labels.add(prenom);
    labels.add(email);

    structure(labels, introduction);

    btnOui.setOnAction((event) -> {
      try {
        ctrl.removeProducteur(producteur);
      } catch (Exception e) {
        RoManErreur.afficher(e);
      }
      redirectionAccueil();
    });

    btnModifier.setOnAction((event) -> {
      try {
        close();
        ctrl.modification(producteur, utilisateur.getRole());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

  }

  public void setCtrl(CtrlProducteur ctrl) {
    this.ctrl = ctrl;
  }
}
