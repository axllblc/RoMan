package fr.roman.vues.metiers;

import fr.roman.RoManErreur;
import fr.roman.controleurs.actions.ActionsTournees;
import fr.roman.controleurs.metiers.CtrlTournee;
import fr.roman.modeles.Tournee;
import fr.roman.modeles.Utilisateur;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Classe de l'objet tournee, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueTournee extends VueMetier {

  /**
   * Contrôleur de la vue Tournee.
   */
  private CtrlTournee ctrl = null;

  /**
   * Tableau de label qui sera envoyé en paramètre à la méthode structure.
   */
  private final ArrayList<Label> labels;

  /**
   * Horaire de début de la tournée.
   */
  private final Label horaireDebut;

  /**
   * Horaire de fin de la tournée.
   */
  private final Label horaireFin;

  /**
   * Durée estimée de la tournée.
   */
  private final Label dureeEstimee;

  /**
   * Notes associées à la tournée.
   */
  private final Label notes;

  /**
   * Validité de la tournée.
   */
  private final Label valide;

  /**
   * Nom et prénom du producteur associé à la tournée.
   */
  private final Label producteur;


  /**
   * Libéllé de véhicule associé à la tournée.
   */
  private final Label vehicule;

  /**
   * Label introduction tournée.
   */
  private final Label introduction;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object tournee.
   */
  public VueTournee(Tournee tournee, Utilisateur utilisateur) {
    super(utilisateur);
    if (tournee.isValide()) {
      valide = new Label("La tournée est valide");
    } else {
      valide = new Label("La tournée n'est pas valide");
    }
    horaireDebut = new Label("Horaire Début : " + tournee.getHoraireDebut().getTime());
    horaireFin = new Label("Horaire Fin : " + tournee.getHoraireFin().getTime());
    dureeEstimee = new Label("Durée estimée : " + tournee.getEstimationDuree().toHours() + " : "
        + tournee.getEstimationDuree().toMinutes() % 60);
    notes = new Label("Notes : " + tournee.getNote());
    producteur = new Label("Producteur : " + tournee.getProducteur().getUtilisateur().getNom()
    + tournee.getProducteur().getUtilisateur().getPrenom());
    vehicule = new Label("Vehicule : " + tournee.getVehicule().getLibelle());
    introduction = new Label("Informations de la tournée de : "
        + tournee.getProducteur().getUtilisateur().getNom() + " "
        + tournee.getProducteur().getUtilisateur().getPrenom());

    labels = new ArrayList<>();

    labels.add(valide);
    labels.add(horaireDebut);
    labels.add(horaireFin);
    labels.add(dureeEstimee);
    labels.add(notes);
    labels.add(producteur);
    labels.add(vehicule);

    structure(labels, introduction);

    btnOui.setOnAction((event) -> {
      try {
        ActionsTournees.supprimer(tournee);
        close();
      } catch (Exception e) {
        RoManErreur.afficher(e);
      }
      redirectionAccueil();
    });

    btnModifier.setOnAction((event) -> {
      try {
        ActionsTournees.modifierTournee(tournee, utilisateur.getRole());
        close();
      } catch (Exception e) {
        RoManErreur.afficher(e);
      }
    });
  }

  public void setCtrl(CtrlTournee ctrl) {
    this.ctrl = ctrl;
  }
}
