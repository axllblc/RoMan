package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.controleurs.recherche.CtrlRechercheCommande;
import fr.roman.dao.DAOCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.dao.DAOVehicule;
import fr.roman.modeles.*;
import fr.roman.vues.edition.LibelleChamp;
import fr.roman.vues.edition.TypeChamp;
import fr.roman.vues.edition.VueEdition;
import fr.roman.vues.recherche.VueRechercheCommande;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CtrlEditionTournee extends CtrlEdition<Tournee, Tournee.Champs> {
  private final String BTN_COMMANDE = "Recherche commande";
  private final String BTN_VEHICULE = "Recherche véhicule";
  private Producteur producteur;
  private ArrayList<Commande> commandes = new ArrayList<>();
  private ArrayList<Vehicule> vehicules = new ArrayList<>();
  // DAO nécessaire pour le fonctionnement du contrôleur
  DAOTournee daoTournee;
  DAOVehicule daoVehicule;
  DAOProducteur daoProducteur;
  DAOCommande daoCommande;

  {
    try {
      daoProducteur = new DAOProducteur();
      daoVehicule = new DAOVehicule();
      daoTournee = new DAOTournee();
      daoCommande = new DAOCommande();
    } catch (Exception e) {
      RoManErreur.afficher(e);
    }
  }
  /**
   * Constructeur du contrôleur
   *
   * @param tournee     Le métier dans lequel des stockés les informations de la vue (vide si typeEdition = CREATION)
   * @param vueEdition  La vue (d'édition) que la classe contrôle
   * @param typeEdition Le type de contrôleur : création ou modification
   * @param role        Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
   */
  public CtrlEditionTournee(Utilisateur utilisateur,Tournee tournee, VueEdition vueEdition, TypeEdition typeEdition, Role role) throws Exception {
    super(utilisateur, tournee, vueEdition, typeEdition, role);
    this.producteur = daoProducteur.find(utilisateur);
    LinkedHashMap<Commande.Champs, String> criteresC = new LinkedHashMap<>();
    criteresC.put(Commande.Champs.idProducteur, String.valueOf(producteur.getId()));
    this.commandes = daoCommande.find(criteresC);
    LinkedHashMap<Vehicule.Champs, String> criteresV = new LinkedHashMap<>();
    criteresV.put(Vehicule.Champs.idProducteur, String.valueOf(producteur.getId()));
    criteresV.put(Vehicule.Champs.idProducteur, String.valueOf(producteur.getId()));
    this.vehicules = daoVehicule.find(criteresV);
    superSuite();
  }

  @Override
  void chargerChamps() {
    boolean valeurBool;
    int valeurInt;

    // idTournee
    TypeChamp idTournee = new TypeChamp(LibelleChamp.TEXTFIELD);
    idTournee.setRegex("\\d*");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      idTournee.setValeur(String.valueOf(getModele().getIdTournee()));
    }
    getChampsFormulaire().put(Tournee.Champs.idTournee, idTournee);

    // note
    TypeChamp note = new TypeChamp(LibelleChamp.TEXTFIELD);
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      note.setValeur(getModele().getNote());
    }
    note.setRegex(".{0,500}");
    getChampsFormulaire().put(Tournee.Champs.note, note);

    // valide
    valeurBool = false;
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      valeurBool = (getModele().isValide());
    }
    TypeChamp valide = new TypeChamp(LibelleChamp.CHECKBOX);
    valide.setValeurBool(valeurBool);
    getChampsFormulaire().put(Tournee.Champs.valide, valide);

    // horaireDebut
    TypeChamp horaireDebut = new TypeChamp(LibelleChamp.CALENDEARTEXTFIELD);
    horaireDebut.setRegex("dd/MM/yyyy HH:mm");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      horaireDebut.setCalendar(getModele().getHoraireDebut());
    }
    getChampsFormulaire().put(Tournee.Champs.horaireDebut, horaireDebut);

    // horaireFin
    TypeChamp horaireFin = new TypeChamp(LibelleChamp.CALENDEARTEXTFIELD);
    horaireFin.setRegex("dd/MM/yyyy HH:mm");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      horaireFin.setCalendar(getModele().getHoraireFin());
    }
    getChampsFormulaire().put(Tournee.Champs.horaireFin, horaireFin);

    // véhicule
    valeurInt = 0;
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      valeurInt = getModele().getVehicule().getIdVehicule();
    }
    TypeChamp vehicule = new TypeChamp(LibelleChamp.VEHICULE);
    vehicule.setVehicules(vehicules);
    getChampsFormulaire().put(Tournee.Champs.idVehicule, vehicule);

    // idProducteur
    TypeChamp idProducteur = new TypeChamp(LibelleChamp.SPINNERINT);
    System.err.println(producteur);
    idProducteur.setSpinnerInt(0, 9999999, producteur.getId());
    idProducteur.setRegex("\\d{1,50}");
    getChampsFormulaire().put(Tournee.Champs.idProducteur, idProducteur);

    // commande
    TypeChamp commande = new TypeChamp(LibelleChamp.COMMANDE);
    commande.setCommande(commandes);
    getChampsFormulaire().put(Tournee.Champs.commande, commande);
  }

  @Override
  public Tournee validerSaisie() throws Exception {
    verification();

    // note
    getModele().setNote(getChampsFormulaire().get(Tournee.Champs.note).getValeur());

    // valide
    getModele().setValide(getChampsFormulaire().get(Tournee.Champs.valide).isValeurBool());

    // horaireDebut
    getModele().setHoraireDebut(getChampsFormulaire().get(Tournee.Champs.horaireDebut).getCalendar());

    // horaireFin
    getModele().setHoraireFin(getChampsFormulaire().get(Tournee.Champs.horaireFin).getCalendar());

    // véhicule
    getModele().setVehicule(daoVehicule.findById(
            getChampsFormulaire().get(Tournee.Champs.idVehicule).getValeurInt()));

    // idProducteur
    getModele().setProducteur(daoProducteur.findById(
            getChampsFormulaire().get(Tournee.Champs.idProducteur).getValeurInt()));

    Tournee tournee = null;
    switch (getTypeEdition()){
      case CREATION -> tournee = daoTournee.insert(getModele());
      case MODIFICATION -> {  if(daoTournee.update(getModele())) tournee = getModele();}
    }
    getVueEdition().close();
    return tournee;
  }

  private void verification() throws Exception {
    // On ne vérifie que le poids total
    boolean poids;
    if(getChampsFormulaire().get(Tournee.Champs.idVehicule).getValeurInt() == 0) {
      poids = false;
    } else {
      double poidsTotal = 0;
      for(Commande c : this.commandes){
        poidsTotal += c.getPoids();
      }
      Vehicule vehicule = daoVehicule.findById(
              getChampsFormulaire().get(Tournee.Champs.idVehicule).getValeurInt());
      poids = poidsTotal <= vehicule.getPoidsMax();
    }

    // mise à jour du champ "valide" de la tournée
    getChampsFormulaire()
            .get(Tournee.Champs.valide).setValeurBool(poids);
  }

  @Override
  public int action(String nom) {
    if (nom.equals(BTN_VEHICULE)) {
      // TODO: recherche véhicule
      System.out.println(BTN_VEHICULE);
    } else if (nom.equals(BTN_COMMANDE)) {
      // TODO: recherche commande
      VueRechercheCommande vueR = new VueRechercheCommande();
      CtrlRechercheCommande ctrlR = new CtrlRechercheCommande(producteur,vueR);
      System.out.println(BTN_COMMANDE);
    }
    return getChampsFormulaire().get(Tournee.Champs.idVehicule).getValeurInt();
  }
}
