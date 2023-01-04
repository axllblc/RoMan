package fr.roman.vues.tableaux;

import static fr.roman.vues.composants.IconTableColumn.TAILLE_ICONE;

import fr.roman.modeles.Tournee;
import fr.roman.vues.composants.FabriqueIcone;
import fr.roman.vues.composants.IconTableColumn;
import fr.roman.vues.composants.Icone;
import java.text.DateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Tableau de tournées.
 *
 * <p>Les colonnes affichées sont les suivantes :
 * <ul>
 *   <li>Validité de la tournée</li>
 *   <li>Date</li>
 *   <li>Créneau horaire</li>
 *   <li>Durée estimée</li>
 *   <li>Nombre de commandes</li>
 *   <li>Poids</li>
 *   <li>Véhicule</li>
 * </ul>
 * </p>
 *
 * @see Tableau
 *
 * @author Axel Leblanc
 */
public class TableauTournees extends Tableau<Tournee> {
  private static final String TITRE_COL_VALIDITE = """
      Validité de la tournée
      
      Cliquer pour trier selon la validité.
      """;

  /**
   * Construire un tableau permettant de lister des tournées.
   */
  public TableauTournees() {
    super();

    // Création des colonnes

    /* Colonne affichant la validité de la tournée. */
    IconTableColumn<Tournee, Boolean> colValidite = new IconTableColumn<>(TITRE_COL_VALIDITE);
    colValidite.setCellValueFactory(t ->
        new ReadOnlyObjectWrapper<>(t.getValue().isValide())
    );
    colValidite.setCellFactory(tableColumn -> new TableCell<>() {
      @Override
      protected void updateItem(Boolean valide, boolean empty) {
        super.updateItem(valide, empty);

        if (!empty) {
          setGraphic(valide
              ? FabriqueIcone.get(Icone.OK, TAILLE_ICONE)
              : FabriqueIcone.get(Icone.ATTENTION, TAILLE_ICONE));
          setTooltip(new Tooltip(valide ? "Tournée valide" : "La tournée contient des erreurs"));
          setAlignment(Pos.CENTER);
        } else {
          setGraphic(null);
        }
      }
    });

    /* Colonne affichant la date de la tournée. */
    TableColumn<Tournee, Date> colDate = new TableColumn<>("Date");
    colDate.setCellValueFactory(t ->
        new ReadOnlyObjectWrapper<>(t.getValue().getHoraireDebut().getTime())
    );
    colDate.setCellFactory(tableColumn -> new TextFieldTableCell<>() {
      @Override
      public void updateItem(Date date, boolean empty) {
        super.updateItem(date, empty);

        if (!empty) {
          setText(DateFormat.getDateInstance(DateFormat.LONG).format(date));
        }
      }
    });

    /* Colonne affichant le créneau horaire de la tournée. */
    TableColumn<Tournee, Date[]> colCreneau = new TableColumn<>();
    colCreneau.setSortable(false);
    colCreneau.setCellValueFactory(t ->
        new ReadOnlyObjectWrapper<>(new Date[]{
            t.getValue().getHoraireDebut().getTime(),
            t.getValue().getHoraireFin().getTime()
        })
    );
    colCreneau.setCellFactory(tableColumn -> new TextFieldTableCell<>() {
      @Override
      public void updateItem(Date[] dates, boolean empty) {
        super.updateItem(dates, empty);

        if (!empty) {
          setText(
              DateFormat.getTimeInstance(DateFormat.SHORT).format(dates[0])
              + " – "
              + DateFormat.getTimeInstance(DateFormat.SHORT).format(dates[1])
          );
        }
      }
    });

    /* Colonne affichant la durée estimée. */
    TableColumn<Tournee, Duration> colDuree = new TableColumn<>("Durée estimée");
    colDuree.setCellValueFactory(t ->
        new ReadOnlyObjectWrapper<>(t.getValue().getEstimationDuree())
    );
    colDuree.setCellFactory(tableColumn -> new TextFieldTableCell<>() {
      @Override
      public void updateItem(Duration duree, boolean empty) {
        super.updateItem(duree, empty);

        if (!empty) {
          setText(duree.toHoursPart() + " h " + duree.toMinutesPart() + " min");
        }
      }
    });

    /* Colonne affichant le véhicule utilisé. */
    TableColumn<Tournee, String> colVehicule = new TableColumn<>("Véhicule");
    colVehicule.setCellValueFactory(t ->
        new ReadOnlyObjectWrapper<>(t.getValue().getVehicule().getLibelle())
    );

    // Insertion des colonnes définies ci-dessus
    var colonnes = List.of(colValidite, colDate, colCreneau, colDuree, colVehicule);
    getTableau().getColumns().addAll(colonnes);
  }
}
