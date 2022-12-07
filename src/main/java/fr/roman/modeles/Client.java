package fr.roman.modeles;

/**
* Représente un client de producteurs.
*/
public class Client {

  private int idClient;

  private String nom;

  private String prenom;

  private String tel;

  private String mail;

  private int siret;

  private boolean particulier;

  private Adresse adresse;

  /**
   * Constructeur par défaut de la classe Client.
   */
  public Client() {}

  /**
   * Constructeur de la classe Client.
   *
   * @param idClient L'identifiant du client dans la base.
   * @param nom Le nom du client.
   * @param prenom Le prénom du client.
   * @param tel Le numéro de téléphone du client.
   * @param mail L'adresse mail du client.
   * @param adresse L'adresse du client.
   */
  public Client(int idClient, String nom, String prenom, String tel, String mail,
                int siret, boolean particulier, Adresse adresse) {
    this.idClient = idClient;
    this.nom = nom;
    this.prenom = prenom;
    this.tel = tel;
    this.mail = mail;
    this.siret = siret;
    this.particulier = particulier;
    this.adresse = adresse;
  }

  public int getIdClient() { return idClient; }

  public String getNom() { return nom; }

  public String getPrenom() { return prenom; }

  public String getTel() { return tel; }

  public String getMail() { return mail; }

  public int getSiret() { return siret; }

  public boolean isParticulier() { return particulier; }


  public Adresse getAdresse() { return adresse; }

  public void setNom(String nom) { this.nom = nom; }

  public void setPrenom(String prenom) { this.prenom = prenom; }

  public void setTel(String tel) { this.tel = tel; }

  public void setMail(String mail) { this.mail = mail; }

  public void setSiret(int siret) { this.siret = siret; }

  public void setParticulier(boolean particulier) { this.particulier = particulier; }

  public void setAdresse(Adresse adresse) { this.adresse = adresse; }
}