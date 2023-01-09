package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEditionCommande;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.controleurs.metiers.CtrlCommande;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueCommande;

/**
 * Classe regroupant les actions possibles pour la gestion des commandes.
 *
 * @author Axel Leblanc, Pierre Saussereau
 */
public abstract class ActionsCommandes {
  /**
   * Créer une commande pour le producteur passé en paramètre. La vue d'édition correspondante
   * est instanciée.
   *
   * @param producteur Producteur auquel associer la nouvelle commande
   * @param role Rôle de l'utilisateur connecté à l'application
   */
  public static void creerCommande(Producteur producteur, Role role) {
    try {
      Commande commande = new Commande();
      if (producteur != null) {
        commande.setProducteur(producteur);
      }
      VueEdition vue = new VueEdition(TypeEdition.CREATION);
      new CtrlEditionCommande(commande, vue, TypeEdition.CREATION, role);
      vue.show();
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
    }
  }

  /**
   * Modifier la commande passée en paramètre. La vue d'édition correspondante est instanciée.
   *
   * @param commande Commande à modifier
   * @param role Rôle de l'utilisateur connecté à l'application
   */
  public static void modifierCommande(Commande commande, Role role) {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    new CtrlEditionCommande(commande, vue, TypeEdition.MODIFICATION, role);
    vue.show();
  }

  /**
   * Afficher la commande passée en paramètre. La vue correspondante est instanciée.
   *
   * @param commande Commande à afficher
   * @param utilisateur Utilisateur connecté à l'application
   */
  public static void afficherCommande(Commande commande, Utilisateur utilisateur) {
    VueCommande vueCommande = new VueCommande(commande, utilisateur);
    try {
      new CtrlCommande(vueCommande);
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
    }
  }
}
