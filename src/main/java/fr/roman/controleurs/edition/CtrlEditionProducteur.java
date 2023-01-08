package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOAdresse;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOUtilisateur;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.edition.LibelleChamp;
import fr.roman.vues.edition.TypeChamp;
import fr.roman.vues.edition.VueEdition;

/**
 * Contrôleur perettant l'edition et l'ajout de Producteurs.
 */
public class CtrlEditionProducteur extends CtrlEdition<Producteur, Producteur.Champs> {

  // Les DAOs nécessaire pour le fonctionnement du contrôleur
  DAOProducteur daoProducteur;
  DAOAdresse daoAdresse;
  DAOUtilisateur daoUtilisateur;

  {
    try {
      daoProducteur = new DAOProducteur();
      daoAdresse = new DAOAdresse();
      daoUtilisateur = new DAOUtilisateur();
    } catch (Exception e) {
      RoManErreur.afficher(e);
    }
  }


  /**
   * Constructeur du contrôleur.
   *
   * @param producteur  Le métier dans lequel des stockés les informations de la vue (vide si typeEdition = CREATION)
   * @param vueEdition  La vue (d'édition) que la classe contrôle
   * @param typeEdition Le type de contrôleur : création ou modification
   * @param role        Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
   */
  public CtrlEditionProducteur(Utilisateur utilisateur, Producteur producteur, VueEdition vueEdition, TypeEdition typeEdition, Role role) throws Exception {
    super(utilisateur, producteur, vueEdition, typeEdition, role);
    superSuite();
  }


  /**
   * Classe à implémenter qui construit les objets {@link TypeChamp} à mettre dans la vue,
   * c.-à-d. les champs du formulaire (préremplis le cas échéant)
   */
  @Override
  public void chargerChamps() {
    int valeurInt;

    // idProducteur
    TypeChamp idProducteur = new TypeChamp(LibelleChamp.TEXTFIELD);
    idProducteur.setRegex("\\d*");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      idProducteur.setValeur(String.valueOf(getModele().getIdProducteur()));
    }
    getChampsFormulaire().put(Producteur.Champs.idProducteur, idProducteur);

    // siret
    TypeChamp siret = new TypeChamp(LibelleChamp.TEXTFIELD);
    siret.setRegex("[\\d]{0,14}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      siret.setValeur(getModele().getSiret());
    }
    getChampsFormulaire().put(Producteur.Champs.siret, siret);

    // nom établissement
    TypeChamp nomEtablissement = new TypeChamp(LibelleChamp.TEXTFIELD);
    siret.setRegex(".{0,150}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      nomEtablissement.setValeur(getModele().getNomEtablissement());
    }
    getChampsFormulaire().put(Producteur.Champs.nomEtablissement, nomEtablissement);

    // tel
    TypeChamp tel = new TypeChamp(LibelleChamp.TEXTFIELD);
    tel.setRegex("[+\\d]{0,10}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      tel.setValeur(getModele().getTel());
    }
    getChampsFormulaire().put(Producteur.Champs.tel, tel);

    // adresse
    valeurInt = 0;
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      valeurInt = getModele().getAdresse().getIdAdresse();
    }
    TypeChamp adresse = new TypeChamp(LibelleChamp.SPINNERINT);
    adresse.setSpinnerInt(0, 9999999, valeurInt);
    adresse.setRegex("\\d{1,50}");
    getChampsFormulaire().put(Producteur.Champs.idAdresse, adresse);

    // utilisateur
    valeurInt = 0;
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      valeurInt = getModele().getUtilisateur().getIdUtilisateur();
    }
    TypeChamp utilisateur = new TypeChamp(LibelleChamp.SPINNERINT);
    utilisateur.setSpinnerInt(0, 9999999, valeurInt);
    utilisateur.setRegex("\\d{1,50}");
    getChampsFormulaire().put(Producteur.Champs.idUtilisateur, utilisateur);

    // nom utilisateur
    TypeChamp nomUtilisateur = new TypeChamp(LibelleChamp.TEXTFIELD);
    nomUtilisateur.setRegex(".{0,150}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      nomUtilisateur.setValeur(getModele().getUtilisateur().getNomUtilisateur());
    }
    getChampsFormulaire().put(Producteur.Champs.nomUtilisateur, nomUtilisateur);

    //mdp
    TypeChamp mdp = new TypeChamp(LibelleChamp.TEXTFIELD);
    mdp.setRegex(".{0,150}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      mdp.setValeur(getModele().getUtilisateur().getMdp());
    }
    getChampsFormulaire().put(Producteur.Champs.mdp, mdp);

    //nom
    TypeChamp nom = new TypeChamp(LibelleChamp.TEXTFIELD);
    nom.setRegex(".{0,150}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      nom.setValeur(getModele().getUtilisateur().getNom());
    }
    getChampsFormulaire().put(Producteur.Champs.nom, nom);

    //prenom
    TypeChamp prenom = new TypeChamp(LibelleChamp.TEXTFIELD);
    prenom.setRegex(".{0,50}");
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      prenom.setValeur(getModele().getUtilisateur().getPrenom());
    }
    getChampsFormulaire().put(Producteur.Champs.prenom, prenom);


    // email
    TypeChamp email = new TypeChamp(LibelleChamp.TEXTFIELD);
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      email.setValeur(getModele().getUtilisateur().getEmail());
    }
    getChampsFormulaire().put(Producteur.Champs.email, email);


    // role
    TypeChamp role = new TypeChamp(LibelleChamp.TEXTFIELD);
    if (getTypeEdition() == TypeEdition.MODIFICATION) {
      role.setValeur(getModele().getUtilisateur().getRole().toString());
    }
    getChampsFormulaire().put(Producteur.Champs.role, role);





  }

  /**
   * Classe appelée par le bouton de validation du formulaire pour
   * effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
   *
   * @return L'objet Client correspondant à ce qui a été ajouté dans la base de données
   */
  @Override
  public Producteur validerSaisie() throws Exception {

    int idUser;

    // siret
    getModele().setSiret(getChampsFormulaire().get(Producteur.Champs.siret).getValeur());

    // nom établissement
    getModele().setNomEtablissement(
        getChampsFormulaire().get(Producteur.Champs.nomEtablissement).getValeur());

    // tel
    getModele().setTel(getChampsFormulaire().get(Producteur.Champs.tel).getValeur());

    // idAdresse
    getModele().setAdresse(daoAdresse.findById(
        getChampsFormulaire().get(Producteur.Champs.idAdresse).getValeurInt()));

    //idUtilisateurProducteur
    getModele().setUtilisateur(daoUtilisateur.findById(
        getChampsFormulaire().get(Producteur.Champs.idUtilisateur).getValeurInt()));


    //nom utilisateur
    getModele().getUtilisateur().setNomUtilisateur(
        getChampsFormulaire().get(Producteur.Champs.nomUtilisateur).getValeur());

    //mdp
    getModele().getUtilisateur().setMdp(
        getChampsFormulaire().get(Producteur.Champs.mdp).getValeur());

    //nom
    getModele().getUtilisateur().setNom(
        getChampsFormulaire().get(Producteur.Champs.nom).getValeur());

    //prenom
    getModele().getUtilisateur().setPrenom(
        getChampsFormulaire().get(Producteur.Champs.prenom).getValeur());

    //email
    getModele().getUtilisateur().setEmail(
        getChampsFormulaire().get(Producteur.Champs.email).getValeur());

    //role
    getModele().getUtilisateur().setRole(Role.PRODUCTEUR);


    Producteur producteur = null;
    Utilisateur utilisateur = null;

    switch (getTypeEdition()) {
      case CREATION -> {
        producteur = daoProducteur.insert(getModele());
      }
      case MODIFICATION -> {

        if (daoProducteur.update(getModele())) {
          producteur = getModele();
        }

        Utilisateur user;
        user = getModele().getUtilisateur();
        daoUtilisateur.update(user);

      }


    }
    return producteur;
  }
}

