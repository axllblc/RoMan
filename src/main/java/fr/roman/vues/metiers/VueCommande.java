package fr.roman.vues.metiers;

import fr.roman.controleurs.metiers.CtrlCommande;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Tournee;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Classe de l'object commande, elle nous permettra de visualiser les objects de ce type
 * et de rediriger vers une  suppression ou une modification.
 */
public class VueCommande extends VueMetier {

  /**
   * Contrôleur de la vue Commande.
   */
  private CtrlCommande ctrl = null;

  /**
   * Libellé de la commande.
   */
  private final Label libelle;

  /**
   * Poids de la commande.
   */
  private final Label poids;

  /**
   * Horaire de début de la commande.
   */
  private final Label horaireDebut;

  /**
   * Horaire de fin de la commande.
   */
  private final Label horaireFin;

  /**
   * Notes de la commande.
   */
  private final Label notes;

  /**
   * Affiche un défaut de livraison si la commande n'a pas été livrée ou la date de livraison
   * si elle a eu lieu.
   */
  private final Label livraison;

  /**
   * Producteur qui a fourni la commande.
   */
  private final Label producteur;

  /**
   * Client qui a effectué la commande.
   */
  private final Label client;

  /**
   * Tableau de label qui sera envoyé en paramètre à la méthode structure.
   */
  private final ArrayList<Label> labels;


  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object commande.
   */
  public VueCommande(Commande commande) {

    if (commande.isDefaultLivraison()) {
      livraison = new Label("Livraison n'a pas été effectuée");
    } else {
      livraison = new Label("Date Livraison : " + commande.getDateLivraison());
    }

    libelle = new Label("Libellé : " + commande.getLibelle());
    poids = new Label("Poids : " + commande.getPoids());
    horaireDebut = new Label("Horaire Début : " + commande.getHoraireDebut());
    horaireFin = new Label("Horaire Fin : " + commande.getHoraireFin());
    notes = new Label("Notes : " + commande.getNote());
    producteur = new Label("Producteur : " + commande.getProducteur().getUtilisateur().getNom()
        + commande.getProducteur().getUtilisateur().getPrenom());
    client = new Label("Client : " + commande.getClient().getNom()
        + commande.getClient().getPrenom());

    labels = new ArrayList<>();

    labels.add(livraison);
    labels.add(libelle);
    labels.add(poids);
    labels.add(horaireDebut);
    labels.add(horaireFin);
    labels.add(notes);
    labels.add(producteur);
    labels.add(client);

    structure(labels);

    btnOui.setOnAction((event) -> {
      ctrl.removeCommande(commande);
    });



  }

  public void setCtrl(CtrlCommande ctrl) {
    this.ctrl = ctrl;
  }

}
