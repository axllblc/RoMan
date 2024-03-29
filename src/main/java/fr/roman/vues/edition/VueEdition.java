package fr.roman.vues.edition;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEdition;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.modeles.ChampsModele;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.CalendarTimeTextField;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe qui définit la vue pour les contrôleurs d'édition (ajout ou modification de métiers)
 */
public class VueEdition {
  Stage stage;
  /**
   * Contrôleur de la vue <i>Edition</i>.
   */
  private CtrlEdition ctrl = null;
  /**
   * Le type de la vue : création ou modification
   */
  private final TypeEdition TYPE_EDITION;
  /**
   * La scène contenant les éléments de la vue
   */
  private Scene scene;
  /**
   * Le titre de la vue.
   */
  private final Label titre;
  
  /**
   * Conteneur des champs du formulaire.
   */
  private final VBox formulaire;
  /**
   * Conteneur principal de la scène.
   */
  private final BorderPane conteneur;
  /**
   * Conteneur du bas de la vue.
   */
  private final GridPane footer;
  /**
   * Bouton pour valider la saisie
   */
  private final Button btnValider;
  /**
   * Bouton de retour pour annuler la saisie
   */
  private final Button btnRetour;
  /**
   * Liste des champs du formulaire de la vue (clé : nom d'un champ dans l'énum,
   *                                           valeur : un objet {@link Node}.
   */
  private final Map<String, Node> composants = new LinkedHashMap<>();
  private Map<? extends ChampsModele, TypeChamp> signal = new LinkedHashMap<>();
  private String nomMetier = "";
  /**
   * Construire la vue d'<i>édition</i>.
   */
  public VueEdition(TypeEdition typeEdition) {
    
    // Définition des éléments de la vue
    this.TYPE_EDITION = typeEdition;
    titre = new Label();
    conteneur = new BorderPane();
    formulaire = new VBox();
    footer = new GridPane();
    btnValider = new Button("Valider");
    btnRetour = new Button("Annuler");
    structureEdition();
    // création des composants graphique
  }
  
  /**
   * Établir la structure de base de la vue d'<i>édition</i> : créer une {@link Scene} et lui
   * ajouter des éléments (titre, bouton de validation de saisie sans les champs).
   */
  private void structureEdition() {
    scene = new Scene(conteneur);
    // Placement des éléments
    conteneur.setTop(titre);
    conteneur.setCenter(formulaire);
    conteneur.setBottom(footer);
    footer.add(btnValider, 0, 0);
    footer.add(btnRetour, 1, 0);
    
    btnValider.setOnAction((event) -> {
      actualiser();
      ctrl.setChampsFormulaire(this.signal);
      try {
        ctrl.validerSaisie();
      } catch (Exception e) {
        RoManErreur.afficher(e);
      }
    });
    btnRetour.setOnAction((event) -> {
      ctrl.annulerSaisie();

      // Fermeture de la fenêtre
      stage.close();
    });
    
    // Marges, espacement, alignement
    conteneur.setPadding(new Insets(15));
  }
  
  /**
   * Affectation du contrôleur de la vue d'édition.
   *
   * @param ctrl Contrôleur à affecter
   */
  public void setCtrl(CtrlEdition ctrl) {
    this.ctrl = ctrl;
  }
  
  /**
   * Définir et ajouter les champs du formulaire qui seront affichés
   *
   * @param nomMetier le nom du métier qui va être édité
   */
  public void definirChamps(String nomMetier) {
    this.nomMetier = nomMetier;
    this.titre.setText(this.TYPE_EDITION.libelle + " : " + nomMetier);
    this.composants.forEach((nom, noeud) -> formulaire.getChildren().addAll(new Label(nom),noeud));
  }

  /**
   * Retourne la {@link Scene} contenant la vue d'édition
   */
  public Scene getScene() {
    return scene;
  }
  
  /**
   * Méthode qui permet de gérer les différentes méthodes de création d'élément graphique.
   *
   * @param signal des composants demandés par le contrôleur.
   */
  public void signalCtrlEdition(Map<? extends ChampsModele, TypeChamp> signal) {
    this.signal = signal;
    signal.forEach((c,t) -> {
      switch (t.getLibelle()) {
        case TEXTFIELD ->
          // création d'un "textField".
                this.composants.put(c.toString(), textField(t));
        case SPINNERDOUBLE ->
          // création d'un "spinner" de Double.
                this.composants.put(c.toString(), spinnerDouble(t));
        case DOUBLESPINNERDOUBLE ->
          // création d'un "spinner" de Double.
                this.composants.put(c.toString(), doubleSpinnerDouble(t));
        case SPINNERINT ->
          // création d'un "spinner" de Integer.
                this.composants.put(c.toString(), spinnerInteger(t));
        case CALENDEARTIMETEXTFIELD ->
          // création d'un "calendarTimeTextField".
                this.composants.put(c.toString(), calendarTimeTextField(t));
        case CALENDEARTEXTFIELD ->
          // création d'un "calendarTextField".
                this.composants.put(c.toString(), calendarTextField(t));
        case CHECKBOX ->
          // création d'une "checkbox"
                this.composants.put(c.toString(), checkBox(t));
        case BUTTON ->
          // création d'un "button"
                this.composants.put(c.toString(), button(t));
        case COMMANDE ->
          //création d'un composant "commande"
          this.composants.put(c.toString(), commande(t));
        case VEHICULE ->
          //création d'un composant "véhicule"
          this.composants.put(c.toString(), vehicule(t));
        default -> throw new RuntimeException("L'élément graphique" + t + " est inconnue.");
      }
    });
  }
  
  private Node textField(TypeChamp t) {
    TextField resultat = new TextField();
    resultat.setDisable(t.isDisable());
    if(!t.getRegex().isEmpty()) {
      resultat.setTextFormatter(new TextFormatter<>(change -> {
        if (!change.getControlNewText().matches(t.getRegex())) {
          return null;
        } else {
          return change;
        }
      }));
    }
    if(TYPE_EDITION == TypeEdition.MODIFICATION){
      resultat.setText(t.getValeur());
    }
    return resultat;
  }
  
  private Node spinnerDouble(TypeChamp t) {
    Spinner<Double> resultat = new Spinner<>();
    resultat.setDisable(t.isDisable());
    resultat.setEditable(true);
    resultat.setValueFactory(new SpinnerValueFactory
            .DoubleSpinnerValueFactory(t.getMinDouble(), t.getMaxDouble(), t.getValeurDouble()));
    if(!t.getRegex().isEmpty()){
      resultat.getEditor().setTextFormatter(new TextFormatter<>(change -> {
        if (!change.getControlNewText().matches(t.getRegex())) {
          return null;
        } else {
          return change;
        }
      }));
    }
    resultat.setTooltip(new Tooltip(t.getPlaceholder()));
    return resultat;
  }
  
  private Node doubleSpinnerDouble(TypeChamp t) {
    HBox conteneurDouble = new HBox();
    Spinner<Double> longitude = new Spinner<>();
    Spinner<Double> latitude = new Spinner<>();
    conteneurDouble.getChildren().add(longitude);
    conteneurDouble.getChildren().add(latitude);
    longitude.setDisable(t.isDisable());
    longitude.setEditable(true);
    longitude.setValueFactory(new SpinnerValueFactory
            .DoubleSpinnerValueFactory(t.getMinDouble(), t.getMaxDouble(), t.getValeurDoubleTab1x2()[0]));
    if(!t.getRegex().isEmpty()){
      longitude.getEditor().setTextFormatter(new TextFormatter<>(change -> {
        if (!change.getControlNewText().matches(t.getRegex())) {
          return null;
        } else {
          return change;
        }
      }));
    }

    latitude.setDisable(t.isDisable());
    latitude.setEditable(true);
    latitude.setValueFactory(new SpinnerValueFactory
            .DoubleSpinnerValueFactory(t.getMinDouble(), t.getMaxDouble(), t.getValeurDoubleTab1x2()[1]));
    if(!t.getRegex().isEmpty()){
      latitude.getEditor().setTextFormatter(new TextFormatter<>(change -> {
        if (!change.getControlNewText().matches(t.getRegex())) {
          return null;
        } else {
          return change;
        }
      }));
    }
    return conteneurDouble;
  }
  private Node spinnerInteger(TypeChamp t) {
    Spinner<Integer> resultat = new Spinner<>();
    resultat.setDisable(t.isDisable());
    resultat.setEditable(true);
    resultat.setValueFactory(new SpinnerValueFactory
            .IntegerSpinnerValueFactory(t.getMinInt(), t.getMaxInt(), t.getValeurInt()));
    if(!t.getRegex().isEmpty()){
      resultat.getEditor().setTextFormatter(new TextFormatter<>(change -> {
        if (!change.getControlNewText().matches(t.getRegex())) {
          return null;
        } else {
          return change;
        }
      }));
    }
    return resultat;
  }

  private Node calendarTimeTextField(TypeChamp t) {
    CalendarTimeTextField resultat = new CalendarTimeTextField();
    resultat.setDisable(t.isDisable());
    resultat.setLocale(Locale.FRANCE);
    resultat.setMinuteStep(15);
    if(!(t.getRegex().isEmpty())){
      resultat.setDateFormat(new SimpleDateFormat(t.getRegex()));
    }
    if(!(t.getCalendar().getAvailableCalendarTypes().isEmpty())){
      resultat.setCalendar(t.getCalendar());
    }
    return resultat;
  }

  private Node calendarTextField(TypeChamp t) {
    CalendarTextField resultat = new CalendarTextField();
    resultat.setDisable(t.isDisable());
    resultat.setShowTime(true);
    resultat.autosize();
    resultat.setLocale(Locale.FRANCE);
    resultat.setDateFormat(new SimpleDateFormat(t.getRegex()));
    resultat.setAllowNull(true);
    if(!(t.getCalendar().getAvailableCalendarTypes().isEmpty())){
      resultat.setCalendar(t.getCalendar());
    }
    return resultat;
  }
  private Node checkBox(TypeChamp t) {
    CheckBox resultat = new CheckBox();
    resultat.setDisable(t.isDisable());
    resultat.setSelected(t.isValeurBool());
    return resultat;
  }
  private Node button(TypeChamp t) {
    HBox resultat = new HBox();
    Node spinnerInteger = spinnerInteger(t);
    spinnerInteger.setDisable(t.isDisable());
    Button btn = new Button();
    btn.setText(t.getValeur());
    btn.setOnAction((event) -> t.setValeurInt(ctrl.action(t.getValeur())));
    resultat.getChildren().addAll(spinnerInteger, btn);
    return resultat;
  }
  private Node commande(TypeChamp t){
    ArrayList<String> i = new ArrayList<>();
    for(Commande c: t.getCommande()){
      i.add(String.valueOf(c.getIdCommande()));
    }
    t.setIteam(i);
    Node resultat = combox(t);
    return resultat;
  }
  private Node vehicule(TypeChamp t){
    ArrayList<String> i = new ArrayList<>();
    for(Vehicule v: t.getVehicules()){
      i.add(String.valueOf(v.getIdVehicule()));
    }
    t.setIteam(i);
    Node resultat = combox(t);
    return resultat;
  }
  private Node combox(TypeChamp t) {
    ObservableList<String> items =
            FXCollections.observableArrayList(
                    t.getIteam()
            );
    ComboBox resultat = new ComboBox();
    resultat.setItems(items);
    return resultat;
  }
  public void actualiser() {
    for (Map.Entry<? extends ChampsModele, TypeChamp> signal : this.signal.entrySet()) {
      TypeChamp value = signal.getValue();
      System.out.println(this.composants.get(String.valueOf(signal.getKey())));
      Node c = this.composants.get(String.valueOf(signal.getKey()));
      if(c instanceof TextField){
        if(!((TextField) c).getText().isEmpty()){
          value.setValeur("");
        }
        value.setValeur(((TextField) c).getText());
      } else if(c instanceof CalendarTextField){
        if(!Calendar.getAvailableCalendarTypes().isEmpty()) {
          value.setCalendar(((CalendarTextField) c).getCalendar());
        }
      } else if(c instanceof CalendarTimeTextField){
        if(!Calendar.getAvailableCalendarTypes().isEmpty()){
          value.setCalendar(((CalendarTimeTextField) c).getCalendar());
        }
      } else if(c instanceof Spinner<?>){
        if(((Spinner<?>) c).getValue() instanceof Integer){
          value.setValeurInt(((Spinner<Integer>) c).getValue());
        } else if (((Spinner<?>) c).getValue() instanceof Double){
          value.setValeurDouble(((Spinner<Double>) c).getValue());
        }
      } else if(c instanceof CheckBox){
        value.setValeurBool(((CheckBox) c).isIndeterminate());
      } else if(c instanceof HBox){
        value.setValeurInt(((Spinner<Integer>) (((HBox) c).getChildren().get(0))).getValue());
      } else if(c instanceof ComboBox){
        value.setValeurInt(Integer.parseInt(((ComboBox) c).getValue().toString()));
      }
    }
  }

  /**
   * Créer un {@link Stage} contenant la vue <i>Edition</i> et l'afficher.
   */
  public void show() {
    stage = new Stage();
    stage.setScene(scene);
    stage.setTitle(this.TYPE_EDITION.libelle + " : " + nomMetier);

    stage.setMinWidth(300);
    stage.setMinHeight(500);

    stage.show();
  }

  public void close(){
    if(stage != null){
      stage.close();
    }
  }
}
