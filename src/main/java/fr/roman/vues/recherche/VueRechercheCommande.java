package fr.roman.vues.recherche;

import fr.roman.controleurs.recherche.CtrlRechercheCommande;
import fr.roman.modeles.Commande;
import fr.roman.vues.Actualisable;
import fr.roman.vues.tableaux.TableauCommandes;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;

/**
 * Vue de recherche pour les commandes.
 *
 * @see VueRecherche
 * @see CtrlRechercheCommande
 * @see Commande
 *
 * @author Axel Leblanc
 */
public class VueRechercheCommande extends VueRecherche<Commande> implements Actualisable {
  // Éléments graphiques pour le filtrage
  // - Statut de commande
  /**
   * Bouton affichant le menu de sélection du/des statut(s) de commandes.
   */
  private MenuButton menuStatut;
  /**
   * Statuts de commandes associés à l'entrée du menu {@link #menuStatut} correspondante.
   */
  private Map<Commande.Statut, CheckMenuItem> checkStatut;

  // - Date de livraison prévue
  /**
   * Menu de sélection de la date de livraison prévue.
   */
  private MenuButton menuDateInitiale;
  /**
   * Groupe de boutons radio pour le filtre par date.
   */
  private ToggleGroup toggleGroupDateInitiale;
  /**
   * Filtres par date associés au bouton radio {@link RadioMenuItem} correspondant.
   */
  private Map<FiltreDate, RadioMenuItem> radioDateInitiale;
  private DatePicker datePicker;

  /**
   * Filtres par date.
   */
  private enum FiltreDate {
    TOUT_AFFICHER("Tout afficher"),
    AVANT_LE("Avant le..."),
    APRES_LE("Après le..."),
    DATE_NON_FIXEE("Date non fixée");

    public final String label;

    FiltreDate(String label) {
      this.label = label;
    }
  }

  /**
   * Créer une vue de recherche pour les commandes.
   */
  public VueRechercheCommande() {
    super();

    setTableau(new TableauCommandes());

    composantsFiltrage();
  }

  @Override
  public void actualiser() {
    ctrl.actualiserVue();
  }

  /**
   * Définir les composants graphiques utilisés pour le filtrage et les placer dans le panneau
   * des filtres.
   */
  private void composantsFiltrage() {
    // - Statut de commande

    menuStatut = new MenuButton("Statut de commande");
    checkStatut = new LinkedHashMap<>();

    for (Commande.Statut statut : Commande.Statut.values()) {
      // Instanciation d'une entrée de menu (case à cocher) pour chaque statut de commande
      CheckMenuItem item = new CheckMenuItem(statut.getLabel());
      checkStatut.put(statut, item);
      item.setOnAction(event -> appliquerFiltres());
    }

    // Remplissage du menu "Statut" et ajout au panneau des filtres
    menuStatut.getItems().setAll(checkStatut.values());
    panneauFiltres.add(menuStatut, 0, 0);


    // - Date de livraison prévue

    menuDateInitiale = new MenuButton("Date de livraison prévue");
    toggleGroupDateInitiale = new ToggleGroup();
    radioDateInitiale = new LinkedHashMap<>();
    datePicker = new DatePicker(LocalDate.now());

    for (FiltreDate filtreDate : FiltreDate.values()) {
      // Instanciation d'une entrée de menu (bouton radio) pour chaque filtre par date proposé
      RadioMenuItem item = new RadioMenuItem(filtreDate.label);
      radioDateInitiale.put(filtreDate, item);
      item.setToggleGroup(toggleGroupDateInitiale);
    }

    // Sélection par défaut du choix "Tout afficher"
    radioDateInitiale.get(FiltreDate.TOUT_AFFICHER).setSelected(true);

    // Ajout des event-handlers
    toggleGroupDateInitiale.selectedToggleProperty().addListener(toggle -> appliquerFiltres());
    datePicker.setOnAction(event -> appliquerFiltres());

    // Remplissage du menu "Date de livraison prévue" et ajout au panneau de filtres
    CustomMenuItem datePickerMenuItem = new CustomMenuItem(datePicker);
    datePickerMenuItem.setHideOnClick(false);
    menuDateInitiale.getItems().addAll(radioDateInitiale.values());
    menuDateInitiale.getItems().add(new SeparatorMenuItem());
    menuDateInitiale.getItems().add(datePickerMenuItem);
    panneauFiltres.add(menuDateInitiale, 1, 0);
  }

  @Override
  protected void appliquerFiltres() {
    // Filtrage selon le statut de la commande
    Predicate<Commande> predicatStatut = commande -> (
        // Aucun bouton n'est coché : tout est affiché.
        checkStatut.values().stream().noneMatch(CheckMenuItem::isSelected)
        // Si le statut de la commande est l'un des statuts sélectionnés, la commande est affichée.
        || Stream.of(Commande.Statut.values()).anyMatch(
          statut -> (checkStatut.get(statut).isSelected() && commande.getStatut() == statut)
        )
    );

    // Filtrage selon la date de livraison fixée
    Predicate<Commande> predicatDateLivraison;
    if (radioDateInitiale.get(FiltreDate.TOUT_AFFICHER).isSelected()) {
      predicatDateLivraison = commande -> true;
    } else if (radioDateInitiale.get(FiltreDate.DATE_NON_FIXEE).isSelected()) {
      predicatDateLivraison = commande -> commande.getDateInitiale() == null;
    } else {
      // Récupération de la date et conversion de LocalDate vers Calendar
      Calendar date = Calendar.getInstance();
      date.setTime(
          Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
      );

      predicatDateLivraison = commande -> (
          (radioDateInitiale.get(FiltreDate.AVANT_LE).isSelected()
              && commande.getDateInitiale() != null
              && commande.getDateInitiale().before(date))
          || (radioDateInitiale.get(FiltreDate.APRES_LE).isSelected()
              && commande.getDateInitiale() != null
              && commande.getDateInitiale().after(date))
      );
    }

    // Le filtre est appliqué sur le tableau
    getTableau().setFiltre(
        predicatStatut.and(predicatDateLivraison)
    );
  }
}
