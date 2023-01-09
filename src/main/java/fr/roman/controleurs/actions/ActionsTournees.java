package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEditionTournee;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.controleurs.metiers.CtrlTournee;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Tournee;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.Confirmation;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.metiers.VueTournee;

/**
 * Classe regroupant les actions possibles pour la gestion des tournées.
 *
 * @author Axel Leblanc, Pierre Saussereau
 */
public abstract class ActionsTournees {
  /**
   * Créer une tournée pour le producteur passé en paramètre. La vue d'édition correspondante est
   * instanciée.
   *
   * @param producteur Producteur auquel associer la nouvelle tournée
   * @param role Rôle de l'utilisateur connecté à l'application
   */
  public static void creerTournee(Producteur producteur, Role role) {
    try {
      Tournee tournee = new Tournee();
      tournee.setProducteur(producteur);
      VueEdition vue = new VueEdition(TypeEdition.CREATION);
      new CtrlEditionTournee(tournee, vue, TypeEdition.CREATION, role);
      vue.show();
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
    }
  }

  /**
   * Modifier la tournée passée en paramètre. La vue d'édition correspondante est instanciée.
   *
   * @param tournee Tournée à modifier
   * @param role Rôle de l'utilisateur connecté à l'application
   */
  public static void modifierTournee(Tournee tournee, Role role) {
    VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
    new CtrlEditionTournee(tournee, vue, TypeEdition.MODIFICATION, role);
    vue.show();
  }

  /**
   * Afficher la tournée passée en paramètre. La vue correspondante est instanciée.
   *
   * @param tournee Tournée à afficher
   * @param utilisateur Utilisateur connecté à l'application
   */
  public static void afficherTournee(Tournee tournee, Utilisateur utilisateur) {
    VueTournee vueTournee = new VueTournee(tournee, utilisateur);
    try {
      new CtrlTournee(vueTournee);
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
    }
  }

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
