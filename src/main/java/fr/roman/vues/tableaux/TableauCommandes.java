package fr.roman.vues.tableaux;

import static fr.roman.vues.composants.IconTableColumn.TAILLE_ICONE;

import fr.roman.modeles.Commande;
import fr.roman.vues.composants.FabriqueIcone;
import fr.roman.vues.composants.IconTableColumn;
import fr.roman.vues.composants.Icone;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Tableau de commandes.
 *
 * <p>Les commandes affichées sont les suivantes :
 * <ul>
 *   <li>Statut de la commande (livrée, en attente, reportée, en retard)</li>
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
  private static final String TITRE_COL_STATUT = """
      Statut de la commande
      
      Cliquer pour trier selon l'importance.
      """;

  /**
   * Construire un tableau permettant de lister des commandes.
   */
  public TableauCommandes() {
    super();

    // Création des colonnes

    /* Colonne affichant le statut de la commande. */
    IconTableColumn<Commande, Commande.Statut> colStatut = new IconTableColumn<>(TITRE_COL_STATUT);
    colStatut.setCellValueFactory(c ->
        new ReadOnlyObjectWrapper<>(c.getValue().getStatut())
    );
    colStatut.setCellFactory(tableColumn -> new TableCell<>() {
      @Override
      protected void updateItem(Commande.Statut statut, boolean empty) {
        super.updateItem(statut, empty);

        if (empty || statut == null) {
          setGraphic(null);
          return;
        }

        setGraphic(
            switch (statut) {
              case LIVREE -> FabriqueIcone.get(Icone.OK, TAILLE_ICONE);
              case EN_ATTENTE -> FabriqueIcone.get(Icone.EN_ATTENTE, TAILLE_ICONE);
              case REPORTEE -> FabriqueIcone.get(Icone.DEFAUT_LIVRAISON, TAILLE_ICONE);
              case EN_RETARD -> FabriqueIcone.get(Icone.ATTENTION, TAILLE_ICONE);
            });
        setTooltip(new Tooltip(statut.getLabel()));
        setAlignment(Pos.CENTER);
      }
    });

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
        new ReadOnlyObjectWrapper<>(
            c.getValue().getDateInitiale() != null
                ? c.getValue().getDateInitiale().getTime() : null
        )
    );
    colJourLivraison.setCellFactory(tableColumn ->
        new TextFieldTableCell<>() {
          @Override
          public void updateItem(Date date, boolean empty) {
            super.updateItem(date, empty);

            if (!empty) {
              setText(date != null
                  ? DateFormat.getDateInstance(DateFormat.LONG).format(date)
                  : "Date non fixée");
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
            super.updateItem(creneau, empty);

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
    var colonnes = List.of(colStatut, colLibelle, colDateInitiale, colPoids, colClient);
    colDateInitiale.getColumns().addAll(List.of(colJourLivraison, colCreneauLivraison));
    colClient.getColumns().addAll(List.of(colNomClient, colAdresseClient));
    getTableau().getColumns().addAll(colonnes);

    // Tri
    colStatut.setSortType(TableColumn.SortType.DESCENDING);
    getTableau().getSortOrder().add(colStatut);
  }
}
