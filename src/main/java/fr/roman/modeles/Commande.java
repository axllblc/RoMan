package fr.roman.modeles;

/**
* Représente une commande d'un client à un producteur.
*/
public class Commande {

  private int idCommande;

  private String libelle;

  private double poids;

  private String horaireDebut;

  private String horaireFin;

  private String note;

  private boolean defaultLivraison;

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
   * @param defaultLivraison Si vrai, la commande n'a pas pu être effectuée
   *                         et doit être associé à une nouvelle tournée.
   * @param dateInitiale La date initiale de livraison prévue.
   * @param dateLivraison Le cas échéant, la date où la commande a été livrée.
   * @param producteur Le producteur qui a la commande.
   * @param client Le client qui a fait la commande.
   * @param tournee La tournée à laquelle la commande a été associée.
   */
  public Commande(int idCommande, String libelle, double poids, String horaireDebut,
                  String horaireFin, String note, boolean defaultLivraison, String dateInitiale,
                  String dateLivraison, Producteur producteur, Client client, Tournee tournee) {
    this.idCommande = idCommande;
    this.libelle = libelle;
    this.poids = poids;
    this.horaireDebut = horaireDebut;
    this.horaireFin = horaireFin;
    this.note = note;
    this.defaultLivraison = defaultLivraison;
    this.dateInitiale = dateInitiale;
    this.dateLivraison = dateLivraison;
    this.producteur = producteur;
    this.client = client;
    this.tournee = tournee;
  }

  /**
   * Le constructeur par défaut de la classe Commande.
   */
  public Commande() {}

  public int getIdCommande() { return idCommande; }

  public String getLibelle() { return libelle; }

  public double getPoids() { return poids; }

  public String getHoraireDebut() { return horaireDebut; }

  public String getHoraireFin() { return horaireFin; }

  public String getNote() { return note; }

  public boolean isDefaultLivraison() { return defaultLivraison; }

  public String getDateInitiale() { return dateInitiale; }

  public String getDateLivraison() { return dateLivraison; }

  public Producteur getProducteur() { return producteur; }

  public Client getClient() { return client; }

  public Tournee getTournee() { return tournee; }


  public void setLibelle(String libelle) { this.libelle = libelle; }

  public void setPoids(double poids) { this.poids = poids; }

  public void setHoraireDebut(String horaireDebut) { this.horaireDebut = horaireDebut; }

  public void setHoraireFin(String horaireFin) { this.horaireFin = horaireFin; }

  public void setNote(String note) { this.note = note; }

  public void setDefaultLivraison(boolean defaultLivraison) { this.defaultLivraison = defaultLivraison; }

  public void setDateInitiale(String dateInitiale) { this.dateInitiale = dateInitiale;  }

  public void setDateLivraison(String dateLivraison) { this.dateLivraison = dateLivraison; }

  public void setProducteur(Producteur producteur) { this.producteur = producteur; }

  public void setClient(Client client) { this.client = client; }

  public void setTournee(Tournee tournee) { this.tournee = tournee; }

}