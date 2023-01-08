package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOCommande;
import fr.roman.modeles.Commande;
import fr.roman.vues.Confirmation;
import java.util.List;

/**
 * Classe regroupant les actions possibles pour la gestion des commandes.
 */
public class ActionsCommandes {
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
