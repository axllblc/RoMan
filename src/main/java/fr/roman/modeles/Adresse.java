package fr.roman.modeles;

/**
* Représente une adresse d'un producteur ou d'un client.
*/
public class Adresse {

  private int idAdresse;
  private double[] coordonneesGPS;
  private String libelle;
  private int numeroVoie;
  private String complementNumero;
  private String voie;
  private String complementAdresse;
  private int codePostal;
  private String ville;

  /**
   * Le constructeur par défaut de la classe Adresse.
   */
  public Adresse() {
  }

  /**
   * Constructeur de la classe Adresse.
   *
   * @param idAdresse L'identifiant de l'adresse
   * @param coordonneesGPS Les coordonnées GPS de l'adresse
   * @param libelle Le libellé de l'adresse
   * @param numeroVoie Le numéro de voie de l'adresse
   * @param complementNumero Le complément du numéro
   * @param codePostal Le code postal
   * @param ville La ville
   */
  public Adresse(int idAdresse, double[] coordonneesGPS, String libelle, int numeroVoie,
                 String complementNumero, String voie, String complementAdresse,
                 int codePostal, String ville) {
    this.idAdresse = idAdresse;
    this.coordonneesGPS = coordonneesGPS;
    this.libelle = libelle;
    this.numeroVoie = numeroVoie;
    this.complementNumero = complementNumero;
    this.voie = voie;
    this.complementAdresse = complementAdresse;
    this.codePostal = codePostal;
    this.ville = ville;
  }

  public int getIdAdresse() {
    return idAdresse;
  }

  public double[] getCoordonneesGPS() {
    return coordonneesGPS;
  }

  public void setCoordonneesGPS(double[] coordonneesGPS) {
    this.coordonneesGPS = coordonneesGPS;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public int getNumeroVoie() {
    return numeroVoie;
  }

  public void setNumeroVoie(int numeroVoie) {
    this.numeroVoie = numeroVoie;
  }

  public String getComplementNumero() {
    return complementNumero;
  }

  public void setComplementNumero(String complementNumero) {
    this.complementNumero = complementNumero;
  }

  public String getVoie() {
    return voie;
  }

  public void setVoie(String voie) {
    this.voie = voie;
  }

  public String getComplementAdresse() {
    return complementAdresse;
  }

  public void setComplementAdresse(String complementAdresse) {
    this.complementAdresse = complementAdresse;
  }

  public int getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(int codePostal) {
    this.codePostal = codePostal;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }
}