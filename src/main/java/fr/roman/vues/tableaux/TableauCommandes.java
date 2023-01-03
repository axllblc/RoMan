package fr.roman.vues.tableaux;

import fr.roman.modeles.Commande;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Tableau de commandes.
 *
 * <p>Les commandes affichées sont les suivantes :
 * <ul>
 *   <li>Libellé de commande</li>
 *   <li>Date de livraison initialement fixée (date et créneau)</li>
 *   <li>Poids de la commande</li>
 *   <li>Client (nom et adresse)</li>
 * </ul>
 * </p>
 *
 * @see Tableau
 *
 * @author Axel Leblanc
 */
public class TableauCommandes extends Tableau<Commande> {
  /**
   * Construire un tableau permettant de lister des commandes.
   */
  public TableauCommandes() {
    super();

    // Création des colonnes

    /* Colonne affichant les libellés de commandes. */
    TableColumn<Commande, String> colLibelle = new TableColumn<>("Libellé");
    colLibelle.setPrefWidth(250);
    colLibelle.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(c.getValue().getLibelle())
    );

    /* Groupe de colonnes affichant la date et le créneau de livraison initialement fixés. */
    final TableColumn<Commande, Void> colDateInitiale =
        new TableColumn<>("Date de livraison prévue");

    /* Colonne affichant la date de livraison initialement fixée. */
    TableColumn<Commande, Date> colJourLivraison = new TableColumn<>("Date");
    colJourLivraison.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(c.getValue().getDateInitiale().getTime())
    );
    colJourLivraison.setCellFactory(tableColumn ->
        new TextFieldTableCell<>() {
          @Override
          public void updateItem(Date date, boolean empty) {
            if (!empty) {
              setText(DateFormat.getDateInstance(DateFormat.LONG).format(date));
            }
          }
        }
    );

    /* Colonne affichant le créneau de livraison (heure de début - heure de fin). */
    TableColumn<Commande, Date[]> colCreneauLivraison = new TableColumn<>("Créneau");
    colCreneauLivraison.setSortable(false);
    colCreneauLivraison.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(
            new Date[]{
                c.getValue().getHoraireDebut().getTime(),
                c.getValue().getHoraireFin().getTime()
            }
        )
    );
    colCreneauLivraison.setCellFactory(tableColumn ->
        new TextFieldTableCell<>() {
          @Override
          public void updateItem(Date[] creneau, boolean empty) {
            if (!empty) {
              setText(
                  DateFormat.getTimeInstance(DateFormat.SHORT).format(creneau[0])
                      + " – "
                      + DateFormat.getTimeInstance(DateFormat.SHORT).format(creneau[1])
              );
            }
          }
        }
    );

    /* Colonne indiquant le poids de la commande. */
    TableColumn<Commande, String> colPoids = new TableColumn<>("Poids");
    colPoids.setPrefWidth(100);
    colPoids.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(c.getValue().getPoids() + " kg")
    );

    /* Groupe de colonnes affichant les informations sur le client. */
    final TableColumn<Commande, Void> colClient = new TableColumn<>("Client");

    /* Colonne affichant le nom du client. */
    TableColumn<Commande, String> colNomClient = new TableColumn<>("Nom");
    colNomClient.setPrefWidth(200);
    colNomClient.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(
            c.getValue().getClient().getNom()
        )
    );

    /* Colonne affichant l'adresse du client. */
    TableColumn<Commande, String> colAdresseClient = new TableColumn<>("Adresse");
    colAdresseClient.setSortable(false);
    colAdresseClient.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(
          c.getValue().getClient().getAdresse() != null
              ? c.getValue().getClient().getAdresse().getAdressePostale(true) : ""
        )
    );

    // Insertion des colonnes définies ci-dessus
    var colonnes = List.of(colLibelle, colDateInitiale, colPoids, colClient);
    colDateInitiale.getColumns().addAll(List.of(colJourLivraison, colCreneauLivraison));
    colClient.getColumns().addAll(List.of(colNomClient, colAdresseClient));
    getTableau().getColumns().addAll(colonnes);
  }
}