package fr.roman.vues.metiers;

import fr.roman.RoManErreur;
import fr.roman.controleurs.metiers.CtrlVehicule;
import fr.roman.modeles.Utilisateur;
import fr.roman.modeles.Vehicule;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Classe de l'objet vehicule, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueVehicule extends VueMetier {

  /**
   * Contrôleur de la vue Vehicule.
   */
  private CtrlVehicule ctrl = null;

  /**
   * Tableau de label qui sera envoyé en paramètre à la méthode structure.
   */
  private final ArrayList<Label> labels;

  /**
   * Immatricuation du vehicule
   */
  private final Label immatriculation;

  /**
   * Poids maximal que peut contenir le vehicule.
   */
  private final Label poidsMax;

  /**
   * Libellé du véhicule.
   */
  private final Label libelle;

  /**
   * Producteur propriétaire du véhicule.
   */
  private final Label producteur;

  /**
   * Label introduction Vehicule.
   */
  private final Label introduction;



  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object vehicule.
   */
  public VueVehicule(Vehicule vehicule) {
    super(new Utilisateur());

    immatriculation = new Label("Immatriculation : " + vehicule.getImmatriculation());
    poidsMax = new Label("Poids Max : " + vehicule.getPoidsMax());
    libelle = new Label("Libellé : " + vehicule.getLibelle());
    producteur = new Label("Producteur : " + vehicule.getProducteur().getUtilisateur().getNom()
        + vehicule.getProducteur().getUtilisateur().getPrenom());
    introduction = new Label("Information du " + vehicule.getLibelle());

    labels = new ArrayList<>();

    labels.add(immatriculation);
    labels.add(poidsMax);
    labels.add(libelle);
    labels.add(producteur);

    structure(labels, introduction);

    btnOui.setOnAction((event) -> {
      try {
        ctrl.removeVehicule(vehicule);
      } catch (SQLException e) {
        RoManErreur.afficher(e);
      }
      redirectionAccueil();
    });

    btnModifier.setOnAction((event) -> {
      try {
        close();
        ctrl.modification(vehicule);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void setCtrl(CtrlVehicule ctrl) {
    this.ctrl = ctrl;
  }
}
