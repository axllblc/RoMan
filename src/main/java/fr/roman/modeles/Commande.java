package fr.roman.modeles;

import java.util.Objects;

/**
* Représente une commande d'un client à un producteur.
*/
public class Commande extends Modele {

  private int idCommande;
  private String libelle;
  private double poids;
  private String horaireDebut;
  private String horaireFin;
  private String note;
  private boolean defautLivraison;
  private String dateInitiale;
  private String dateLivraison;
  private Producteur producteur;
  public Client client;
  public Tournee tournee;

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
  public Commande(int idCommande, String libelle, double poids, String horaireDebut,
                  String horaireFin, String note, boolean defautLivraison, String dateInitiale,
                  String dateLivraison, Producteur producteur, Client client, Tournee tournee) {
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

  /**
   * Le constructeur par défaut de la classe Commande.
   */
  public Commande() {
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

  public String getHoraireDebut() {
    return horaireDebut;
  }

  public void setHoraireDebut(String horaireDebut) {
    this.horaireDebut = horaireDebut;
  }

  public String getHoraireFin() {
    return horaireFin;
  }

  public void setHoraireFin(String horaireFin) {
    this.horaireFin = horaireFin;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public boolean isDefautLivraison() {
    return defautLivraison;
  }

  public void setDefautLivraison(boolean defautLivraison) {
    this.defautLivraison = defautLivraison;
  }

  public String getDateInitiale() {
    return dateInitiale;
  }

  public void setDateInitiale(String dateInitiale) {
    this.dateInitiale = dateInitiale;
  }

  public String getDateLivraison() {
    return dateLivraison;
  }

  public void setDateLivraison(String dateLivraison) {
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

  public enum Champs {dateInitiale, dateLivraison, defautLivraison, horaireDebut, horaireFin,
    idCommande, libelle, note, poids, idProducteur, idTournee, idClient}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Commande commande = (Commande) o;
    return idCommande == commande.idCommande && Double.compare(commande.poids, poids) == 0 && defautLivraison == commande.defautLivraison && Objects.equals(libelle, commande.libelle) && Objects.equals(horaireDebut, commande.horaireDebut) && Objects.equals(horaireFin, commande.horaireFin) && Objects.equals(note, commande.note) && Objects.equals(dateInitiale, commande.dateInitiale) && Objects.equals(dateLivraison, commande.dateLivraison) && Objects.equals(producteur, commande.producteur) && Objects.equals(client, commande.client) && Objects.equals(tournee, commande.tournee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCommande, libelle, poids, horaireDebut, horaireFin, note, defautLivraison, dateInitiale, dateLivraison, producteur, client, tournee);
  }

  @Override
  public String toString() {
    return "Commande{" +
            "idCommande=" + idCommande +
            ", libelle='" + libelle + '\'' +
            ", poids=" + poids +
            ", horaireDebut='" + horaireDebut + '\'' +
            ", horaireFin='" + horaireFin + '\'' +
            ", note='" + note + '\'' +
            ", defautLivraison=" + defautLivraison +
            ", dateInitiale='" + dateInitiale + '\'' +
            ", dateLivraison='" + dateLivraison + '\'' +
            ", producteur=" + producteur.toString() +
            ", client=" + client.toString() +
            ", tournee=" + tournee.toString() +
            '}';
  }
}