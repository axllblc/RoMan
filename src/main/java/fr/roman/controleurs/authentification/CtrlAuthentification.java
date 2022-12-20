package fr.roman.controleurs.authentification;

import fr.roman.vues.authentification.VueAuthentification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import fr.roman.dao.DAOUtilisateur;

/**
 * Controlleur de la partie Authentification.
 */
public class CtrlAuthentification {

  /**
   * Instance de VueAuthentification.
   */
  private final VueAuthentification vueAuthentification;

  /**
   * Nom de l'utilisateur à vérifier.
   */
  private final String nomUtilisateur;

  /**
   * Mot de passe de l'utilisateur à verifier.
   */
  private final String mdp;


  /**
   * Constructeur de la classe.
   *
   * @param vueAuthentification Instance de la vue authentification.
   * @param nomUtilisateur Nom de l'utilisateur à vérifier.
   * @param mdp Mot de passe de l'utilisateur à vérifier.
   */
  public CtrlAuthentification(VueAuthentification vueAuthentification,
                              String nomUtilisateur, String mdp) {
    this.vueAuthentification = vueAuthentification;
    this.nomUtilisateur = nomUtilisateur;
    this.mdp = mdp;

    verify(this.nomUtilisateur, this.mdp);
  }

  private void verify(String nomUtilisateur, String mdp) {
    /*Utilisateur u = DAOUtilisateur.authentification(nomUtilisateur, mdp);
    if(u == null)
      this.vueAuthentification.erreurSaisie();
    else
      appel accueil
     */


  }
}
