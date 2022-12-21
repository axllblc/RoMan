package fr.roman.controleurs.authentification;

import fr.roman.modeles.Utilisateur;
//import fr.roman.dao.DAOUtilisateur;
import fr.roman.vues.authentification.VueAuthentification;

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
  private String nomUtilisateur;

  /**
   * Mot de passe de l'utilisateur à verifier.
   */
  private String mdp;


  /**
   * Constructeur de la classe.
   *
   * @param vueAuthentification Instance de la vue authentification.
   */
  public CtrlAuthentification(VueAuthentification vueAuthentification) {
    this.vueAuthentification = vueAuthentification;
    this.nomUtilisateur = this.vueAuthentification.getNomUtilisateur();
    this.mdp = this.vueAuthentification.getPassword();
  }

  public void verify() {
    this.nomUtilisateur = vueAuthentification.getNomUtilisateur();
    this.mdp = vueAuthentification.getPassword();
    /*Utilisateur u = DAOUtilisateur.authentification(this.nomUtilisateur, this.mdp);
    if(u == null)
      this.vueAuthentification.erreurSaisie();
    else
      appel accueil
     */
    this.vueAuthentification.erreurSaisie();


  }
}
