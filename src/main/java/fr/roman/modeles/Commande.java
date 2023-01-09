package fr.roman.modeles;

import java.util.Calendar;
import java.util.Objects;

/**
* Représente une commande d'un client à un producteur.
*/
public class Commande extends Modele {
  private final int idCommande;
  private String libelle;
  private double poids;
  /** Heure de début du créneau fixé pour la livraison. */
  private Calendar horaireDebut;
  /** Heure de fin du créneau fixé pour la livraison. */
  private Calendar horaireFin;
  private String note;
  /** Indique si la commande n'a pas pu être effectuée et doit être associée à une nouvelle
   * tournée. */
  private boolean defautLivraison;
  /** Date fixée pour la livraison. */
  private Calendar dateInitiale;
  /** Date à laquelle la commande a été livrée. */
  private Calendar dateLivraison;
  private Producteur producteur;
  private Client client;
  private Tournee tournee;

  /**
   * Constructeur sans paramètre de la classe {@link Commande}.
   */
  public Commande() {
    idCommande = 0;
  }

  /**
   * Constructeur de la classe Commande.
   *
   * @param idCommande L'identifiant de la commande dans la base.
   * @param libelle Le libellé de la commande, renseigné par le producteur.
   * @param poids Le poids total de la commande, en kg.
   * @param horaireDebut L'horaire de début du créneau de livraison souhaité par le client.
   * @param horaireFin L'horaire de fin du créneau de livraison souhaité par le client.
   * @param note Les notes associées à la commande.
   * @param defautLivraison Si vrai, la commande n'a pas pu être effectuée
   *                         et doit être associé à une nouvelle tournée.
   * @param dateInitiale La date initiale de livraison prévue.
   * @param dateLivraison Le cas échéant, la date où la commande a été livrée.
   * @param producteur Le producteur qui a la commande.
   * @param client Le client qui a fait la commande.
   * @param tournee La tournée à laquelle la commande a été associée.
   */
  public Commande(int idCommande, String libelle, double poids, Calendar horaireDebut,
                  Calendar horaireFin, String note, boolean defautLivraison, Calendar dateInitiale,
                  Calendar dateLivraison, Producteur producteur, Client client, Tournee tournee) {
    this.idCommande = idCommande;
    this.libelle = libelle;
    this.poids = poids;
    this.horaireDebut = horaireDebut;
    this.horaireFin = horaireFin;
    this.note = note;
    this.defautLivraison = defautLivraison;
    this.dateInitiale = dateInitiale;
    this.dateLivraison = dateLivraison;
    this.producteur = producteur;
    this.client = client;
    this.tournee = tournee;
  }

  @Override
  public int getId() {
    return idCommande;
  }

  public int getIdCommande() {
    return idCommande;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public double getPoids() {
    return poids;
  }

  public void setPoids(double poids) {
    this.poids = poids;
  }

  /**
   * Retourne l'heure de début du créneau fixé pour la livraison.
   *
   * @return Heure de début du créneau fixé pour la livraison
   */
  public Calendar getHoraireDebut() {
    return horaireDebut;
  }

  /**
   * Définir l'heure de début du créneau fixé pour la livraison.
   *
   * @param horaireDebut Heure de début du créneau fixé pour la livraison
   */
  public void setHoraireDebut(Calendar horaireDebut) {
    this.horaireDebut = horaireDebut;
  }

  /**
   * Retourne l'heure de fin du créneau fixé pour la livraison.
   *
   * @return Heure de fin du créneau fixé pour la livraison
   */
  public Calendar getHoraireFin() {
    return horaireFin;
  }

  /**
   * Définir l'heure de fin du créneau fixé pour la livraison.
   *
   * @param horaireFin Heure de début du créneau fixé pour la livraison
   */
  public void setHoraireFin(Calendar horaireFin) {
    this.horaireFin = horaireFin;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  /**
   * Retourne {@code true} si la livraison n'a pas pu être effectuée et doit être associée à une
   * nouvelle tournée.
   *
   * @return {@code true} en cas de défaut de livraison, {@code false} sinon
   */
  public boolean isDefautLivraison() {
    return defautLivraison;
  }

  /**
   * Indiquer si la livraison n'a pas pu être effectuée et doit être associée à une nouvelle
   * tournée.
   *
   * @param defautLivraison {@code true} en cas de défaut de livraison, {@code false} sinon
   */
  public void setDefautLivraison(boolean defautLivraison) {
    this.defautLivraison = defautLivraison;
  }

  /**
   * Retourne la date fixée pour la livraison.
   *
   * @return Date fixée pour la livraison
   */
  public Calendar getDateInitiale() {
    return dateInitiale;
  }

  /**
   * Définir la date fixée pour la livraison.
   *
   * @param dateInitiale Date fixée pour la livraison
   */
  public void setDateInitiale(Calendar dateInitiale) {
    this.dateInitiale = dateInitiale;
  }

  /**
   * Retourne la date à laquelle la commande a été livrée.
   *
   * @return Date à laquelle la commande a été livrée
   */
  public Calendar getDateLivraison() {
    return dateLivraison;
  }

  /**
   * Définir la date à laquelle la commande a été livrée.
   *
   * @param dateLivraison Date à laquelle la commande a été livrée
   */
  public void setDateLivraison(Calendar dateLivraison) {
    this.dateLivraison = dateLivraison;
  }

  public Producteur getProducteur() {
    return producteur;
  }

  public void setProducteur(Producteur producteur) {
    this.producteur = producteur;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Tournee getTournee() {
    return tournee;
  }

  public void setTournee(Tournee tournee) {
    this.tournee = tournee;
  }

  /**
   * Retourne le statut de la commande : livrée, en attente, reportée, ou en retard.
   *
   * <ul>
   *   <li>Une commande est <b>livrée</b> ({@link Statut#LIVREE}) si sa date de livraison
   *   ({@link #getDateLivraison()}) est non-{@code null}</li>
   *   <li>Une commande est <b>en retard</b> ({@link Statut#EN_RETARD}) si la date de livraison
   *   initialement fixée ({@link #getDateInitiale()}) est passée</li>
   *   <li>Une commande est <b>reportée</b> ({@link Statut#REPORTEE}) si
   *   {@link #isDefautLivraison()} vaut {@code true}</li>
   *   <li>Une commande est <b>en attente</b> ({@link Statut#EN_ATTENTE}) dans les autres cas</li>
   * </ul>
   *
   * @return Statut de la commande
   *
   * @see Statut
   */
  public Statut getStatut() {
    if (dateLivraison != null) {
      return Statut.LIVREE;
    } else if (dateInitiale != null && dateInitiale.before(Calendar.getInstance())) {
      return Statut.EN_RETARD;
    } else if (defautLivraison) {
      return Statut.REPORTEE;
    } else {
      return Statut.EN_ATTENTE;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Commande commande = (Commande) o;
    return
        Double.compare(commande.poids, poids) == 0
        && defautLivraison == commande.defautLivraison
        && Objects.equals(libelle, commande.libelle)
        && Objects.equals(horaireDebut, commande.horaireDebut)
        && Objects.equals(horaireFin, commande.horaireFin)
        && Objects.equals(note, commande.note)
        && Objects.equals(dateInitiale, commande.dateInitiale)
        && Objects.equals(dateLivraison, commande.dateLivraison)
        && Objects.equals(producteur, commande.producteur)
        && Objects.equals(client, commande.client)
        && Objects.equals(tournee, commande.tournee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        libelle, poids, horaireDebut, horaireFin, note, defautLivraison, dateInitiale,
        dateLivraison, producteur, client, tournee
    );
  }

  @Override
  public String toString() {
    return "Commande{"
        + "idCommande=" + idCommande
        + ", libelle='" + libelle + '\''
        + ", poids=" + poids
        + ", horaireDebut='" + horaireDebut + '\''
        + ", horaireFin='" + horaireFin + '\''
        + ", note='" + note + '\''
        + ", defautLivraison=" + defautLivraison
        + ", dateInitiale='" + dateInitiale + '\''
        + ", dateLivraison='" + dateLivraison + '\''
        + ", producteur=" + producteur
        + ", client=" + client
        + ", tournee=" + tournee
        + '}';
  }

  /**
   * Statut d'une commande : livrée, en attente, reportée, ou en retard.
   */
  public enum Statut {
    LIVREE("Livrée"),
    EN_ATTENTE("En attente de livraison"),
    REPORTEE("Reportée"),
    EN_RETARD("En retard");

    final String label;

    Statut(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }
  }

  /**
   * Liste des champs de la table {@code Commandes} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idCommande(true, false, false, false, false),
    dateInitiale(false, true, true, false, true),
    dateLivraison, defautLivraison, horaireDebut, horaireFin, libelle, note, poids,
    idProducteur(false, false, true, false, false),
    idTournee(false, true, true, false, true),
    idClient(false, true, true, false, false);
    private final boolean modifProd;
    private final boolean modifAdmin;
    private final boolean id;
    private final boolean idExt;
    private final boolean nullable;
    /**
     * Constructeur pour désigner des propriétés du champ
     *
     * @param id True si il s'agit de l'identifiant, false sinon
     * @param modifProd True si le champ est modifiable par un producteur, false sinon
     * @param modifAdmin True si le champ est modifiable par un administrateur, false sinon
     * @param idExt True s'il s'agit d'un identifiant qui correspond
     *              à une clé étrangère dans la BDD, false sinon
     * @param nullable True si le champ peut être null, false sinon
     */
    Champs(boolean id, boolean modifProd, boolean modifAdmin, boolean idExt, boolean nullable) {
      this.id = id;
      this.modifProd = modifProd;
      this.modifAdmin = modifAdmin;
      this.idExt = idExt;
      this.nullable = nullable;
    }
    /**
     * Par défaut, tous les utilisateurs peuvent modifier le champ,
     * ce n'est pas un id et il est nullable
     */
    Champs(){
      modifProd = true;
      modifAdmin = true;
      id = false;
      idExt = false;
      nullable = true;
    }

    @Override
    public boolean isModifProd() {
      return this.modifProd;
    }

    @Override
    public boolean isModifAdmin() {
      return this.modifAdmin;
    }

    @Override
    public boolean isId() {
      return this.id;
    }

    @Override
    public boolean isIdExt() {
      return this.idExt;
    }

    @Override
    public boolean isNullable() {
      return this.nullable;
    }
  }
}
