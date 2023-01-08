package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.dao.DAOVehicule;
import fr.roman.modeles.*;
import fr.roman.vues.edition.LibelleChamp;
import fr.roman.vues.edition.TypeChamp;
import fr.roman.vues.edition.VueEdition;

import java.util.ArrayList;

public class ctrlEditionTournee extends CtrlEdition<Tournee, Tournee.Champs> {
  private ArrayList<Commande> commandes = new ArrayList<>();
  // DAO nécessaire pour le fonctionnement du contrôleur
  DAOTournee daoTournee;
  DAOVehicule daoVehicule;
  DAOProducteur daoProducteur;

  {
    try {
      daoProducteur = new DAOProducteur();
      daoVehicule = new DAOVehicule();
      daoTournee = new DAOTournee();
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
  public ctrlEditionTournee(Tournee tournee, VueEdition vueEdition, TypeEdition typeEdition, Role role) {
    super(tournee, vueEdition, typeEdition, role);
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
    TypeChamp vehicule = new TypeChamp(LibelleChamp.SPINNERINT);
    vehicule.setSpinnerInt(0, 9999999, valeurInt);
    vehicule.setRegex("\\d{1,50}");
    getChampsFormulaire().put(Tournee.Champs.idVehicule, vehicule);
  
    // idProducteur
    valeurInt = 0;
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      valeurInt = getModele().getIdTournee();
    }
    TypeChamp idProducteur = new TypeChamp(LibelleChamp.SPINNERINT);
    idProducteur.setSpinnerInt(0, 9999999, valeurInt);
    idProducteur.setRegex("\\d{1,50}");
    getChampsFormulaire().put(Tournee.Champs.idProducteur, idProducteur);
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
    return tournee;
  }
  
  private void verification() throws Exception {
    boolean poids, temps;
    // TODO: vérification du point de la tournée.
    double poidsTotal = 0;
    for(Commande c : this.commandes){
      poidsTotal += c.getPoids();
    }
    Vehicule vehicule = daoVehicule.findById(
            getChampsFormulaire().get(Tournee.Champs.idVehicule).getValeurInt());
    poids = poidsTotal <= vehicule.getPoidsMax();

    // TODO: vérification du temps de livraison.
    temps = false;

    // mise à jour du champ "valide" de la tournée
    getChampsFormulaire()
            .get(Tournee.Champs.valide).setValeurBool(poids && temps);
  }
}
