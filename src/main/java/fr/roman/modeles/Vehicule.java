package fr.roman.modeles;

/**
* Représente le véhicule d'un producteur.
*/
public class Vehicule {

  private int idVehicule;

  private String immatriculation;

  private double poidsMax;

  private String libelle;

  private Producteur producteur;

  /**
   * Constructeur de la classe Vehicule.
   *
   * @param idVehicule L'identifiant du véhicule
   * @param immatriculation L'immatriculation du véhicule
   * @param poidsMax Le poids maximal de chargement du véhicule
   * @param libelle Le libellé du véhicule.
   * @param producteur Le producteur propriétaire du véhicule.
   */
  public Vehicule(int idVehicule, String immatriculation, double poidsMax, String libelle, Producteur producteur) {
    this.idVehicule = idVehicule;
    this.immatriculation = immatriculation;
    this.poidsMax = poidsMax;
    this.libelle = libelle;
    this.producteur = producteur;
  }

  /**
   * Le constructeur par défaut de la classe Vehicule.
   */
  public Vehicule() {}

  public int getIdVehicule() { return idVehicule; }

  public String getImmatriculation() { return immatriculation; }

  public double getPoidsMax() { return poidsMax; }

  public String getLibelle() { return libelle; }

  public Producteur getProducteur() { return producteur; }


  public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

  public void setPoidsMax(double poidsMax) { this.poidsMax = poidsMax; }

  public void setLibelle(String libelle) { this.libelle = libelle; }

  public void setProducteur(Producteur producteur) { this.producteur = producteur; }

}