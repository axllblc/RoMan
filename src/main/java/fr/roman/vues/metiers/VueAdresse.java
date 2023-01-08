package fr.roman.vues.metiers;

import fr.roman.RoManErreur;
import fr.roman.controleurs.metiers.CtrlAdresse;
import fr.roman.modeles.Adresse;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe de l'objet adresse, elle nous permettra de visualiser les objets de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueAdresse extends VueMetier {

  /**
   * Contrôleur de la vue Adresse.
   */
  private CtrlAdresse ctrl = null;

  /**
   * Libelle de l'adresse de l'utilisateur.
   */
  private final Label libelle;

  /**
   * Complément d'adresse de l'utilisateur.
   */
  private final Label complementAdresse;

  /**
   * Voie de l'adresse de l'utilisateur.
   */
  private final Label voie;

  /**
   * Complément du numéro de voie du l'utilisateur.
   */
  private final Label complementNumero;

  /**
   * Code postal de la ville de l'utilisateur;
   */
  private final Label codePostal;

  /**
   * Ville de l'utilisateur.
   */
  private final Label ville;

  //private final Button btnTest;

  /**
   * Tableau de label qui sera envoyé en paramètre à la méthode structure.
   */
  private final ArrayList<Label> labels;

  /**
   * Label introduction adresse.
   */
  private final Label introduction;

  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object adresse.
   */
  public VueAdresse(Adresse adresse) {
    libelle = new Label("Libellé : " + adresse.getLibelle());
    complementAdresse = new Label("Complément d'adresse : " + adresse.getComplementAdresse());
    voie = new Label("Voie : " + adresse.getVoie());
    complementNumero = new Label("Complément numéro : " + adresse.getComplementNumero());
    codePostal = new Label("Code postal : " + String.valueOf(adresse.getCodePostal()));
    ville = new Label("Ville : " + adresse.getVille());
    introduction = new Label("Informations adresse : " + adresse.getLibelle());

    labels = new ArrayList<>();

    labels.add(libelle);
    labels.add(complementAdresse);
    labels.add(voie);
    labels.add(complementNumero);
    labels.add(codePostal);
    labels.add(ville);


    structure(labels, introduction);

    btnOui.setOnAction((event) -> {
      try {
        ctrl.removeAdresse(adresse);
      } catch (SQLException e) {
        RoManErreur.afficher(e);
      }
    });
  }





  public void setCtrl(CtrlAdresse ctrl) {
    this.ctrl = ctrl;
  }




}




