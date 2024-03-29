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

  /**
   * Retourne l'adresse postale sous la forme d'une chaîne de caractères.
   *
   * @param newline {@code true} pour activer les retours à la ligne
   *
   * @return Adresse postale sous la forme d'une chaîne de caractères
   */
  public String getAdressePostale(boolean newline) {
    StringBuilder sb = new StringBuilder();

    // Libellé (nom du destinataire)
    if (getLibelle() != null && !getLibelle().equals("")) {
      sb.append(getLibelle())
          .append(newline ? "\n" : " – ");
    }

    // Numéro de voie et complément de numéro (ex : "bis")
    sb.append(getNumeroVoie() != 0 ? getNumeroVoie() + " " : "");
    if (getComplementNumero() != null && !getComplementNumero().equals("")) {
      sb.append(getComplementNumero()).append(" ");
    }

    // Nom de la voie
    sb.append(getVoie());

    // Complément d'adresse (bâtiment, étage, etc.)
    if (getComplementAdresse() != null && !getComplementAdresse().equals("")) {
      sb.append(", ")
          .append(getComplementAdresse());
    }

    sb.append(newline ? ",\n" : ", ");

    // Code postal et ville
    sb.append("%05d".formatted(getCodePostal()))
        .append(" ")
        .append(getVille());

    return sb.toString();
  }

  /**
   * Liste des champs de la table {@code Adresses} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idAdresse(true, false, false, false, false),
    coordonneesGPS(false, true, true, false, false),
    libelle,
    numeroVoie,
    complementNumero,
    voie,
    complementAdresse,
    codePostal,
    ville;
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