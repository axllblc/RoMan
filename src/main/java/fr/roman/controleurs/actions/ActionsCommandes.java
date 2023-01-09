package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEditionCommande;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.controleurs.metiers.CtrlCommande;
import fr.roman.dao.DAOCommande;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.Confirmation;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueCommande;
import java.util.List;

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
      new CtrlEditionCommande(producteur.getUtilisateur(), commande, vue, TypeEdition.CREATION, role);
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
    new CtrlEditionCommande(commande.getProducteur().getUtilisateur(), commande, vue, TypeEdition.MODIFICATION, role);
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

  /**
   * Supprimer de la base les commandes passées en paramètre.
   *
   * @param commandes Commandes à supprimer
   * @return {@code true} si la suppression a été effectuée, {@code false} en cas d'erreur ou si
   *         l'utilisateur a annulé la suppression.
   */
  public static boolean supprimer(List<Commande> commandes) {
    boolean statut = false;

    try {
      String message = "Vous êtes sur le point de supprimer %s commande%s."
          .formatted(commandes.size(), commandes.size() == 1 ? "" : "s");
      if (commandes.size() != 0 && Confirmation.afficher(message)) {
        DAOCommande daoCommande = new DAOCommande();
        statut = daoCommande.deleteAll(commandes);
        if (!statut) {
          // Dans ce cas la suppression a échoué
          throw new RuntimeException("La suppression de commandes a échoué");
        }
      }
    } catch (Exception e) {
      RoManErreur.afficher(e, "La suppression de commandes n'a pas pu être effectuée.");
      e.printStackTrace();
    }

    return statut;
  }
}
