package fr.roman.controleurs.edition;

import fr.roman.dao.DAOClient;
import fr.roman.dao.DAOCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.*;
import fr.roman.vues.edition.VueEdition;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
     * @param commande La commande dans laquelle des stockés les informations de la vue (vide si typeEdition = CREATION)
     * @param vueEdition La vue (d'édition) que la classe contrôle
     * @param typeEdition Le type de contrôleur : création ou modification
     * @param role Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
     */
    public CtrlEditionCommande(Commande commande, VueEdition vueEdition, TypeEdition typeEdition, Role role) {
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
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            libelle.setText(getModele().getLibelle());
        }
        getChampsFormulaire().put(Commande.Champs.libelle, libelle);

        // poids
        TextField poids = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            poids.setText(String.valueOf((int) getModele().getPoids()));
        }
        poids.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return change;
            }
        }));
        getChampsFormulaire().put(Commande.Champs.poids, poids);

        // horaireDebut
        // TODO: 30/12/2022 Rajouter les minutes ?
        TextField horaireDebut = new TextField();

        if(getTypeEdition() == TypeEdition.MODIFICATION){
            horaireDebut.setText(String.valueOf(getModele().getHoraireDebut().get(Calendar.HOUR_OF_DAY)));
        }
        horaireDebut.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("[0-9]|[01][0-9]|2[0-3]")) {
                return null;
            } else {
                return change;
            }
        }));
        getChampsFormulaire().put(Commande.Champs.horaireDebut, horaireDebut);

        // horaireFin
        // TODO: 30/12/2022 Rajouter les minutes ?
        TextField horaireFin = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            horaireFin.setText(String.valueOf(getModele().getHoraireFin().get(Calendar.HOUR_OF_DAY)));
        }
        horaireFin.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("[0-9]|[01][0-9]|2[0-3]")) {
                return null;
            } else {
                return change;
            }
        }));
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
        DatePicker dateInitiale = new DatePicker(LocalDate.now());
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            Calendar dateI = getModele().getDateInitiale();
            dateInitiale.setValue(LocalDate.of(dateI.get(Calendar.YEAR),
                    dateI.get(Calendar.MONTH)+1, dateI.get(Calendar.DAY_OF_MONTH)));

        }
        getChampsFormulaire().put(Commande.Champs.dateInitiale, dateInitiale);

        // dateLivraison
        DatePicker dateLivraison = new DatePicker(LocalDate.now());
        //DateTimePicker dtp = new DateTimePicker();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            Calendar dateL = getModele().getDateLivraison();
            dateLivraison.setValue(LocalDate.of(dateL.get(Calendar.YEAR),
                    dateL.get(Calendar.MONTH) + 1, dateL.get(Calendar.DAY_OF_MONTH)));
        }
        getChampsFormulaire().put(Commande.Champs.dateLivraison, dateLivraison);

        // producteur
        TextField idProducteur = new TextField();
        idProducteur.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idProducteur.setText(String.valueOf(getModele().getProducteur().getIdProducteur()));
        }
        getChampsFormulaire().put(Commande.Champs.idProducteur, idProducteur);

        // client
        TextField idClient = new TextField();
        idClient.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idClient.setText(String.valueOf(getModele().getClient().getIdClient()));
        }
        getChampsFormulaire().put(Commande.Champs.idClient, idClient);

        // tournee
        TextField idTournee = new TextField();
        idTournee.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idTournee.setText(String.valueOf(getModele().getTournee().getIdTournee()));
        }
        getChampsFormulaire().put(Commande.Champs.idTournee, idTournee);

        getVueEdition().definirChamps(getChampsFormulaire(), "Commande");
    }

    /**
     * Classe abstraite appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet métier correspondant à ce qui a été ajouté dans la base de données
     */
    @Override
    public Commande validerSaisie() {
        // TODO: 01/01/2023 vérifier que tous les champs obligatoires sont saisis

        // libelle
        champsFormulaire = (Map<Commande.Champs, Node>) getVueEdition().getChamps();
        getModele().setLibelle(((TextField) champsFormulaire.get(Commande.Champs.libelle)).getText());
        getModele().setPoids(Double.parseDouble(((TextField) champsFormulaire.get(Commande.Champs.poids)).getText()));

        // horaireDebut
        Calendar hd = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        hd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(((TextField) champsFormulaire.get(Commande.Champs.horaireDebut)).getText()));
        getModele().setHoraireDebut(hd);

        // horaireFin
        Calendar hf = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        hf.set(Calendar.HOUR_OF_DAY, Integer.parseInt(((TextField) champsFormulaire
                .get(Commande.Champs.horaireFin)).getText()));
        getModele().setHoraireFin(hf);

        // note
        getModele().setNote(((TextField) champsFormulaire.get(Commande.Champs.note)).getText());

        // defautLivraison
        getModele().setDefautLivraison(((CheckBox) champsFormulaire
                .get(Commande.Champs.defautLivraison)).isSelected());

        // dateInitiale
        Calendar di = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        LocalDate dateI = ((DatePicker) champsFormulaire.get(Commande.Champs.dateInitiale)).getValue();
        di.setTime(Date.from(dateI.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        getModele().setDateInitiale(di);

        // dateLivraison
        Calendar dl = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        LocalDate dateL = ((DatePicker) champsFormulaire.get(Commande.Champs.dateLivraison)).getValue();
        dl.setTime(Date.from(dateL.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        getModele().setDateInitiale(dl);

        // idProducteur
        Producteur p = daoProducteur.findById(Integer.parseInt(((TextField) champsFormulaire
                                                     .get(Commande.Champs.idProducteur)).getText()));
        getModele().setProducteur(p);

        // idClient
        Client c = daoClient.findById(Integer.parseInt(((TextField) champsFormulaire.get(Commande.Champs.idClient)).getText()));
        getModele().setClient(c);

        // idTournee
        Tournee t = daoTournee.findById(Integer.parseInt(((TextField) champsFormulaire.get(Commande.Champs.idTournee)).getText()));
        getModele().setTournee(t);

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
