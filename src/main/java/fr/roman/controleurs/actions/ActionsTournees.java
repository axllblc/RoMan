package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.Tournee;
import fr.roman.vues.Confirmation;

/**
 * Classe regroupant les actions possibles pour la gestion des tournées.
 */
public abstract class ActionsTournees {
  /**
   * Supprimer de la base la tournée passée en paramètre.
   *
   * @param tournee Tournée à supprimer
   * @return {@code true} si la suppression a été effectuée, {@code false} en cas d'erreur ou si
   *         l'utilisateur a annulé la suppression.
   */
  public static boolean supprimer(Tournee tournee) {
    boolean statut = false;

    try {
      String message = "Vous êtes sur le point de supprimer une tournée.";
      if (tournee != null && Confirmation.afficher(message)) {
        DAOTournee daoTournee = new DAOTournee();
        statut = daoTournee.delete(tournee.getId());
        if (!statut) {
          // Dans ce cas la suppression a échoué
          throw new RuntimeException("La suppression de la tournée a échoué");
        }
      }
    } catch (Exception e) {
      RoManErreur.afficher(e, "La suppression de la tournée n'a pas pu être effectuée.");
      e.printStackTrace();
    }

    return statut;
  }
}
