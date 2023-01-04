package fr.roman.controleurs.edition;

import fr.roman.dao.DAOClient;
import fr.roman.dao.DAOCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.*;
import fr.roman.vues.edition.VueEdition;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.util.*;

import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.CalendarTimeTextField;

public class CtrlEditionCommande extends CtrlEdition<Commande, Commande.Champs> {

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
            e.printStackTrace();
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
    public CtrlEditionCommande(Commande commande, VueEdition vueEdition,
                               TypeEdition typeEdition, Role role) {
        super(commande, vueEdition, typeEdition, role);
    }

    /**
     * Classe à implémenter qui construit les objets {@link Node} à mettre dans la vue,
     *  c.-à-d. les champs du formulaire (préremplis le cas échéant)
     */
    @Override
    public void chargerChamps() {
        // idCommande
        TextField idCommande = new TextField();
        idCommande.setDisable(true);
        idCommande.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idCommande.setText(String.valueOf(getModele().getIdCommande()));
        }
        getChampsFormulaire().put(Commande.Champs.idCommande, idCommande);

        // libelle
        TextField libelle = new TextField();
        libelle.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches(".{0,50}")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            libelle.setText(getModele().getLibelle());
        }
        getChampsFormulaire().put(Commande.Champs.libelle, libelle);

        // poids
        Spinner<Double> poids = new Spinner<>();
        poids.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 255, 1));
        poids.setEditable(true);
        poids.setTooltip(new Tooltip("en kg"));
        getChampsFormulaire().put(Commande.Champs.poids, poids);

        // horaireDebut
        CalendarTimeTextField horaireDebut = new CalendarTimeTextField();
        horaireDebut.setLocale(Locale.FRANCE);
        horaireDebut.setMinuteStep(15);
        horaireDebut.setDateFormat(new SimpleDateFormat("HH:mm"));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            horaireDebut.setCalendar(getModele().getHoraireDebut());
        }
        getChampsFormulaire().put(Commande.Champs.horaireDebut, horaireDebut);

        // horaireFin
        CalendarTimeTextField horaireFin = new CalendarTimeTextField();
        horaireFin.setLocale(Locale.FRANCE);
        horaireFin.setMinuteStep(15);
        horaireFin.setDateFormat(new SimpleDateFormat("HH:mm"));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            horaireFin.setCalendar(getModele().getHoraireFin());
        }
        getChampsFormulaire().put(Commande.Champs.horaireFin, horaireFin);

        // note
        TextField note = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            note.setText(getModele().getNote());
        }
        getChampsFormulaire().put(Commande.Champs.note, note);

        // defautLivraison
        CheckBox defautLivraison = new CheckBox();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            defautLivraison.setSelected(getModele().isDefautLivraison());
        }
        getChampsFormulaire().put(Commande.Champs.defautLivraison, defautLivraison);

        // dateInitiale
        CalendarTextField dateInitiale = new CalendarTextField();
        dateInitiale.autosize();
        dateInitiale.setLocale(Locale.FRANCE);
        dateInitiale.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        dateInitiale.setAllowNull(true);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            dateInitiale.setCalendar(getModele().getDateInitiale());
        }
        getChampsFormulaire().put(Commande.Champs.dateInitiale, dateInitiale);

        // dateLivraison
        CalendarTextField dateLivraison = new CalendarTextField();
        dateLivraison.setShowTime(true);
        dateLivraison.autosize();
        dateLivraison.setLocale(Locale.FRANCE);
        dateLivraison.setDateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm"));
        dateLivraison.setAllowNull(true);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            dateLivraison.setCalendar(getModele().getDateLivraison());
        }
        getChampsFormulaire().put(Commande.Champs.dateLivraison, dateLivraison);

        // producteur
        Spinner<Integer> idProducteur = new Spinner<>();
        idProducteur.setEditable(true);
        idProducteur.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999999, 0));
        idProducteur.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d{1,50}")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idProducteur.getValueFactory().setValue(getModele().getProducteur().getIdProducteur());
        }
        getChampsFormulaire().put(Commande.Champs.idProducteur, idProducteur);

        // client
        Spinner<Integer> idClient = new Spinner<>();
        idClient.setEditable(true);
        idClient.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d{1,50}")) {
                return null;
            } else {
                return change;
            }
        }));
        idClient.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999999, 0));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idClient.getValueFactory().setValue(getModele().getClient().getIdClient());
        }
        getChampsFormulaire().put(Commande.Champs.idClient, idClient);

        // tournee
        Spinner<Integer> idTournee = new Spinner<>();
        idTournee.setEditable(true);
        idTournee.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d{1,50}")) {
                return null;
            } else {
                return change;
            }
        }));
        idTournee.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999999, 0));
        if(getTypeEdition() == TypeEdition.MODIFICATION && getModele().getTournee() != null){
            idTournee.getValueFactory().setValue(getModele().getTournee().getIdTournee());
        }
        getChampsFormulaire().put(Commande.Champs.idTournee, idTournee);

        getVueEdition().definirChamps(getChampsFormulaire(), "Commande");
    }

    /**
     * Classe appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet Commande correspondant à ce qui a été ajouté dans la base de données
     */
    @Override
    public Commande validerSaisie() throws Exception {

        // libelle
        champsFormulaire = (Map<Commande.Champs, Node>) getVueEdition().getChamps();
        getModele().setLibelle(((TextField) champsFormulaire.get(Commande.Champs.libelle)).getText());

        // poids
        getModele().setPoids(((Spinner<Double>) champsFormulaire
                .get(Commande.Champs.poids)).getValue());

        // horaireDebut
        Calendar horaireDebut = ((CalendarTimeTextField) champsFormulaire
                .get(Commande.Champs.horaireDebut)).getCalendar();
        getModele().setHoraireDebut(horaireDebut);

        // horaireFin
        Calendar horaireFin = ((CalendarTimeTextField) champsFormulaire
                .get(Commande.Champs.horaireFin)).getCalendar();
        getModele().setHoraireFin(horaireFin);

        // note
        getModele().setNote(((TextField) champsFormulaire.get(Commande.Champs.note)).getText());

        // defautLivraison
        getModele().setDefautLivraison(((CheckBox) champsFormulaire
                .get(Commande.Champs.defautLivraison)).isSelected());

        // dateInitiale
        Calendar dateInitiale = ((CalendarTextField) champsFormulaire
                .get(Commande.Champs.dateInitiale)).getCalendar();
        getModele().setDateInitiale(dateInitiale);

        // dateLivraison
        Calendar calendar = ((CalendarTextField) champsFormulaire
                .get(Commande.Champs.dateLivraison)).getCalendar();
        getModele().setDateLivraison(calendar);

        // idProducteur
        getModele().setProducteur(daoProducteur.findById(((Spinner<Integer>) champsFormulaire
                        .get(Commande.Champs.idProducteur)).getValue()));

        // idClient
        getModele().setClient(daoClient.findById(((Spinner<Integer>) champsFormulaire
                .get(Commande.Champs.idClient)).getValue()));

        // idTournee
        getModele().setTournee(daoTournee.findById(((Spinner<Integer>) champsFormulaire
                .get(Commande.Champs.idTournee)).getValue()));

        Commande commande = null;
        switch (getTypeEdition()){
            case CREATION -> commande = daoCommande.insert(getModele());
            case MODIFICATION -> {  if(daoCommande.update(getModele()))
                                        commande = getModele();
                                 }

        }
        return commande;
    }
}
