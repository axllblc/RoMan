package fr.roman.modeles;

import java.util.Arrays;
import java.util.Objects;

/**
* Représente une adresse d'un producteur ou d'un client.
*/
public class Adresse extends Modele {
  private final int idAdresse;
  private double[] coordonneesGPS;
  private String libelle;
  private int numeroVoie;
  private String complementNumero;
  private String voie;
  private String complementAdresse;
  private int codePostal;
  private String ville;

  /**
   * Constructeur sans paramètre de la classe {@link Adresse}.
   */
  public Adresse() {
    idAdresse = 0;
  }

  /**
   * Constructeur de la classe Adresse.
   *
   * @param idAdresse L'identifiant de l'adresse
   * @param coordonneesGPS Les coordonnées GPS de l'adresse
   * @param libelle Le libellé de l'adresse
   * @param numeroVoie Le numéro de voie de l'adresse
   * @param complementNumero Le complément du numéro
   * @param voie Le nom de la voie
   * @param complementAdresse Le complément de l'adresse
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

  @Override
  public int getId() {
    return idAdresse;
  }

  public int getIdAdresse() {
    return idAdresse;
  }

  public double[] getCoordonneesGPS() {
    return coordonneesGPS;
  }

  /**
   * Permet de définir les coordonnées GPS d'une adresse.
   *
   * @param coordonneesGPS un tableau de 2 doubles : le premier est la coordonnée des abscisses
   *                       (longitude), le second est celle des ordonnées (latitude)
   */
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Adresse adresse = (Adresse) o;
    return
        numeroVoie == adresse.numeroVoie
        && codePostal == adresse.codePostal
        && Arrays.equals(coordonneesGPS, adresse.coordonneesGPS)
        && Objects.equals(libelle, adresse.libelle)
        && Objects.equals(complementNumero, adresse.complementNumero)
        && Objects.equals(voie, adresse.voie)
        && Objects.equals(complementAdresse, adresse.complementAdresse)
        && Objects.equals(ville, adresse.ville);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(
        libelle, numeroVoie, complementNumero, voie, complementAdresse, codePostal, ville
    );
    result = 31 * result + Arrays.hashCode(coordonneesGPS);
    return result;
  }

  @Override
  public String toString() {
    return "Adresse{"
        + "idAdresse=" + idAdresse
        + ", coordonneesGPS=" + Arrays.toString(coordonneesGPS)
        + ", libelle='" + libelle + '\''
        + ", numeroVoie=" + numeroVoie
        + ", complementNumero='" + complementNumero + '\''
        + ", voie='" + voie + '\''
        + ", complementAdresse='" + complementAdresse + '\''
        + ", codePostal=" + codePostal
        + ", ville='" + ville + '\''
        + '}';
  }
}