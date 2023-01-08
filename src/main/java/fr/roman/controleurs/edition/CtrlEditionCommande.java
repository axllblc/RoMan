package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOClient;
import fr.roman.dao.DAOCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.*;
import fr.roman.vues.edition.LibelleChamp;
import fr.roman.vues.edition.TypeChamp;
import fr.roman.vues.edition.VueEdition;

public class CtrlEditionCommande extends CtrlEdition<Commande, Commande.Champs> {
  private final String BTN_CLIENT = "Recherche client";
  // Les DAOs nécessaire pour le fonctionnement du contrôleur
  DAOCommande daoCommande;
  DAOProducteur daoProducteur;
  DAOClient daoClient;
  DAOTournee daoTournee;

  {
    try {
      daoProducteur = new DAOProducteur();
      daoClient = new DAOClient();
      daoTournee = new DAOTournee();
      daoCommande = new DAOCommande();
    } catch (Exception e) {
      RoManErreur.afficher(e);
    }
  }

  /**
   * Constructeur du contrôleur
   * @param commande La commande dans laquelle des stockés les informations de la vue
   *                 (vide si typeEdition = CREATION)
   * @param vueEdition La vue (d'édition) que la classe contrôle
   * @param typeEdition Le type de contrôleur : création ou modification
   * @param role Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
   */
  public CtrlEditionCommande(Utilisateur utilisateur,Commande commande, VueEdition vueEdition,
                             TypeEdition typeEdition, Role role) {
    super(utilisateur, commande, vueEdition, typeEdition, role);
    superSuite();
  }
  
  /**
   * Classe à implémenter qui construit les objets {@link TypeChamp} à mettre dans la vue,
   *  c.-à-d. les champs du formulaire (préremplis le cas échéant)
   */
  @Override
  public void chargerChamps() {
    double valeurDouble;
    int valeurInt;
    boolean valeurBool;

    // idCommande
    TypeChamp idCommande = new TypeChamp(LibelleChamp.TEXTFIELD);
    idCommande.setRegex("\\d*");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      idCommande.setValeur(String.valueOf(getModele().getIdCommande()));
    }
    getChampsFormulaire().put(Commande.Champs.idCommande, idCommande);

    // libelle
    TypeChamp libelle = new TypeChamp(LibelleChamp.TEXTFIELD);
    libelle.setRegex(".{0,50}");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      libelle.setValeur(getModele().getLibelle());
    }
    getChampsFormulaire().put(Commande.Champs.libelle, libelle);

    // poids
    valeurDouble = 1.;
    if(getTypeEdition() == TypeEdition.MODIFICATION) {
      valeurDouble = getModele().getPoids();
    }
    TypeChamp poids = new TypeChamp(LibelleChamp.SPINNERDOUBLE);
    poids.setSpinnerDouble(0., 9999., valeurDouble);
    poids.setValeurDouble(valeurDouble);
    poids.setPlaceholder("en kg");
    getChampsFormulaire().put(Commande.Champs.poids, poids);

    // horaireDebut
    TypeChamp horaireDebut = new TypeChamp(LibelleChamp.CALENDEARTIMETEXTFIELD);
    horaireDebut.setRegex("HH:mm");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      horaireDebut.setCalendar(getModele().getHoraireDebut());
    }
    getChampsFormulaire().put(Commande.Champs.horaireDebut, horaireDebut);

    // horaireFin
    TypeChamp horaireFin = new TypeChamp(LibelleChamp.CALENDEARTIMETEXTFIELD);
    horaireFin.setRegex("HH:mm");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      horaireFin.setCalendar(getModele().getHoraireDebut());
    }
    getChampsFormulaire().put(Commande.Champs.horaireFin, horaireFin);

    // note
    TypeChamp note = new TypeChamp(LibelleChamp.TEXTFIELD);
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      note.setValeur(getModele().getNote());
    }
    note.setRegex(".{0,500}");
    getChampsFormulaire().put(Commande.Champs.note, note);

    // defautLivraison
    valeurBool = false;
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      valeurBool = (getModele().isDefautLivraison());
    }
    TypeChamp defautLivraison = new TypeChamp(LibelleChamp.CHECKBOX);
    defautLivraison.setValeurBool(valeurBool);
    getChampsFormulaire().put(Commande.Champs.defautLivraison, defautLivraison);

    // dateInitiale
    TypeChamp dateInitiale = new TypeChamp(LibelleChamp.CALENDEARTEXTFIELD);
    dateInitiale.setRegex("dd/MM/yyyy");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      dateInitiale.setCalendar(getModele().getDateInitiale());
    }
    getChampsFormulaire().put(Commande.Champs.dateInitiale, dateInitiale);

    // dateLivraison
    TypeChamp dateLivraison = new TypeChamp(LibelleChamp.CALENDEARTEXTFIELD);
    dateLivraison.setRegex("dd/MM/yyyy HH:mm");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      dateLivraison.setCalendar(getModele().getDateLivraison());
    }
    getChampsFormulaire().put(Commande.Champs.dateLivraison, dateLivraison);

    // producteur
    valeurInt = 0;
    if(getTypeEdition() == TypeEdition.MODIFICATION) {
      valeurInt = getModele().getProducteur().getIdProducteur();
    }
    TypeChamp producteur = new TypeChamp(LibelleChamp.SPINNERINT);
    producteur.setSpinnerInt(0,9999999,valeurInt);
    getChampsFormulaire().put(Commande.Champs.idProducteur, producteur);

    // client
    valeurInt = 0;
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      valeurInt = getModele().getClient().getIdClient();
    }
    TypeChamp client = new TypeChamp(LibelleChamp.BUTTON);
    client.setValeurInt(valeurInt);
    client.setMaxInt(9999999);
    client.setValeur(BTN_CLIENT);
    getChampsFormulaire().put(Commande.Champs.idClient, client);

    // tournee
    valeurInt = 0;
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      valeurInt = getModele().getTournee().getIdTournee();
    }
    TypeChamp tournee = new TypeChamp(LibelleChamp.SPINNERINT);
    tournee.setSpinnerInt(0, 9999999, valeurInt);
    client.setRegex("\\d{1,50}");
    getChampsFormulaire().put(Commande.Champs.idTournee, tournee);
  }

  /**
   * Classe appelée par le bouton de validation du formulaire pour
   *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
   * @return L'objet Commande correspondant à ce qui a été ajouté dans la base de données
   */
  @Override
  public Commande validerSaisie() throws Exception {
    // libelle
    getModele().setLibelle(getChampsFormulaire().get((Commande.Champs.libelle))
            .getValeur());

    // poids
    getModele().setPoids(getChampsFormulaire().get(Commande.Champs.poids).getValeurDouble());

    // horaireDebut
    getModele().setHoraireDebut(getChampsFormulaire().get(Commande.Champs.horaireDebut)
            .getCalendar());

    // horaireFin
    getModele().setHoraireFin(getChampsFormulaire().get(Commande.Champs.horaireFin)
            .getCalendar());

    // note
    getModele().setNote(getChampsFormulaire().get(Commande.Champs.note).getValeur());

    // defautLivraison
    getModele().setDefautLivraison(getChampsFormulaire().get(Commande.Champs.defautLivraison)
            .isValeurBool());

    // dateInitiale
    getModele().setDateInitiale(getChampsFormulaire().get(Commande.Champs.dateInitiale)
            .getCalendar());

    // dateLivraison
    getModele().setDateLivraison(getChampsFormulaire().get(Commande.Champs.dateLivraison)
            .getCalendar());

    // idProducteur
    getModele().setProducteur(daoProducteur.findById(
            getChampsFormulaire().get(Commande.Champs.idProducteur).getValeurInt()));

    // idClient
    getModele().setClient(daoClient.findById(
            getChampsFormulaire().get(Commande.Champs.idClient).getValeurInt()));

    // idTournee
    getModele().setTournee(daoTournee.findById(
            getChampsFormulaire().get(Commande.Champs.idTournee).getValeurInt()));

    Commande commande = null;
    switch (getTypeEdition()){
      case CREATION -> commande = daoCommande.insert(getModele());
      case MODIFICATION -> {  if(daoCommande.update(getModele()))
        commande = getModele();
      }
    }
    return commande;
  }

  @Override
  public int action(String nom) {
    if (nom.equals(BTN_CLIENT)) {
      // TODO: recherche client
      System.out.println(BTN_CLIENT);
    }
    return getChampsFormulaire().get(Commande.Champs.idClient).getValeurInt();
  }
}
