package fr.roman.controleurs.authentification;

import fr.roman.controleurs.comptes.OutilsMotDePasse;
import fr.roman.dao.DAOUtilisateur;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.authentification.VueAuthentification;
import java.util.Arrays;
import java.util.Base64;

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

  /**
   *
   * @param nomUtilisateur Le nom d'utilisateur
   * @param mdp Le mot de passe renseigné (non chiffré)
   * @return Un objet utilisateur correspondant au nom d'utilisateur.
   *         Renvoie null si le nom d'utilisateur et/ou le mot de passe est incorrect.
   */
  public Utilisateur authentification(String nomUtilisateur, String mdp) {
    try {
      DAOUtilisateur daoU = new DAOUtilisateur();
      Utilisateur u = daoU.findByNomUtilisateur(nomUtilisateur);
      if (u != null){ // Si le nom d'utilisateur existe
        // Note : on utilise Base64 pour convertir le mot de passe (chaine de caractère) en tableau de bits
        if (Arrays.equals(OutilsMotDePasse.chiffrerMDP(mdp, u.getSel()) , Base64.getDecoder().decode(u.getMdp()))){
          // Et si le mot de passe est correct, on retourne l'objet Utilisateur
          return u;
        }
      }
      //Sinon on ne renvoie rien
      return null;
    } catch (Exception e) {
      // En cas d'erreur pour le processus d'authentification, on ne renvoie rien
      return null;
    }
  }
}
