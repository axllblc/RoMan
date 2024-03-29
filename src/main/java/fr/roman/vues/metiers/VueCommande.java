package fr.roman.vues.metiers;

import fr.roman.RoManErreur;
import fr.roman.controleurs.actions.ActionsCommandes;
import fr.roman.controleurs.metiers.CtrlCommande;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Utilisateur;
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
   * Label introduction commande.
   */
  private final Label introduction;


  /**
   * Constructeur de la classe elle permettra de mettre en place tout les éléments
   * graphique de l'object commande.
   */
  public VueCommande(Commande commande, Utilisateur utilisateur) {
    super(utilisateur);
    if (commande.isDefautLivraison()) {
      livraison = new Label("Livraison n'a pas été effectuée");
    } else {
      if(commande.getDateLivraison() != null){
        livraison = new Label("Date Livraison : " + commande.getDateLivraison().getTime());
      }
      else{
        livraison = new Label("Date Livraison : aucune");
      }
    }

    libelle = new Label("Libellé : " + commande.getLibelle());
    poids = new Label("Poids : " + commande.getPoids());
    if(commande.getDateLivraison() != null) {
      horaireDebut = new Label("Horaire Début : " + commande.getHoraireDebut().getTime());
    }
    else{
      horaireDebut = new Label("Horaire Début : aucune");
    }
    if(commande.getDateLivraison() != null) {
      horaireFin = new Label("Horaire Fin : " + commande.getHoraireDebut().getTime());
    }
    else{
      horaireFin = new Label("Horaire Fin : aucune");
    }
    notes = new Label("Notes : " + commande.getNote());
    producteur = new Label("Producteur : " + commande.getProducteur().getUtilisateur().getNom()
        + commande.getProducteur().getUtilisateur().getPrenom());
    client = new Label("Client : " + commande.getClient().getNom());
    introduction = new Label("Information commmande : "  + commande.getLibelle());

    labels = new ArrayList<>();

    labels.add(livraison);
    labels.add(libelle);
    labels.add(poids);
    labels.add(horaireDebut);
    labels.add(horaireFin);
    labels.add(notes);
    labels.add(producteur);
    labels.add(client);

    structure(labels, introduction);

    btnOui.setOnAction((event) -> {
      try {
        ArrayList<Commande> commandes = new ArrayList<>();
        commandes.add(commande);
        ActionsCommandes.supprimer(commandes);
        close();
      } catch (Exception e) {
        RoManErreur.afficher(e);
      }
      redirectionAccueil();
    });

    btnModifier.setOnAction((event) -> {
      try {
        close();
        ActionsCommandes.modifierCommande(commande, utilisateur.getRole());
      } catch (Exception e) {
        RoManErreur.afficher(e);
      }
    });



  }

  public void setCtrl(CtrlCommande ctrl) {
    this.ctrl = ctrl;
  }

}
