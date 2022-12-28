package fr.roman.controleurs.authentification;

import fr.roman.modeles.Utilisateur;
//import fr.roman.controleurs.inscription;
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
   * Constructeur de la classe qui nours permettra de faire les traitements de la partie
   * authentification et de gérer sa logique.
   *
   * @param vueAuthentification Instance de la vue authentification.
   */
  public CtrlAuthentification(VueAuthentification vueAuthentification) {
    this.vueAuthentification = vueAuthentification;
  }

  /**
   * Cette methode permet de verifier si l'utilisateur est un utilisateur valide :
   * - Si il est valide on renvoie vers l'accueil
   * - Si il ne l'est pas on affiche un message d'erreur .
   */
  public void verify() {
    this.nomUtilisateur = vueAuthentification.getNomUtilisateur();
    this.mdp = vueAuthentification.getPassword();
    /*Utilisateur u = CtrlInscription.authentification(this.nomUtilisateur, this.mdp);
    if(u == null)
      this.vueAuthentification.erreurSaisie();
    else
      appel accueil
     */
    this.vueAuthentification.erreurSaisie();


  }
}
