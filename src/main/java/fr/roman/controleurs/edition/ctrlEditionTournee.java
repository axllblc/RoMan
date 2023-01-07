package fr.roman.controleurs.edition;

import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.dao.DAOVehicule;
import fr.roman.modeles.Role;
import fr.roman.modeles.Tournee;
import fr.roman.vues.edition.LibelleChamp;
import fr.roman.vues.edition.TypeChamp;
import fr.roman.vues.edition.VueEdition;

public class ctrlEditionTournee extends CtrlEdition<Tournee, Tournee.Champs> {
  // DAO nécessaire pour le fonctionnement du contrôleur
  DAOTournee daoTournee;
  DAOVehicule daoVehicule;
  DAOProducteur daoProducteur;

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
    TypeChamp vehicule = new TypeChamp(LibelleChamp.TEXTFIELD);
    vehicule.setRegex("\\d*");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      vehicule.setValeur(String.valueOf(getModele().getIdTournee()));
    }
    getChampsFormulaire().put(Tournee.Champs.idVehicule, idTournee);

    // TODO: estimationDuree
    /*
    TypeChamp estimationDuree = new TypeChamp(LibelleChamp.CALENDEARTEXTFIELD);
    estimationDuree.setRegex("dd/MM/yyyy HH:mm");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      estimationDuree.setCalendar(getModele().getEstimationDuree());
    }
    getChampsFormulaire().put(Tournee.Champs.estimationDuree, estimationDuree);
     */

    // idProducteur
    TypeChamp idProducteur = new TypeChamp(LibelleChamp.TEXTFIELD);
    idProducteur.setRegex("\\d*");
    if(getTypeEdition() == TypeEdition.MODIFICATION){
      idProducteur.setValeur(String.valueOf(getModele().getIdTournee()));
    }
    getChampsFormulaire().put(Tournee.Champs.idProducteur, idProducteur);
  }

  @Override
  public Tournee validerSaisie() throws Exception {
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

    // TODO: estimationDuree

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
}
