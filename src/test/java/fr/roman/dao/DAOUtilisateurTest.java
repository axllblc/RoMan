package fr.roman.dao;

import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DAOUtilisateurTest {
  private final DAOUtilisateur TEST_DAOUtilisateur = new DAOUtilisateur();
  private final DAOAdresse TEST_DAOAdresse = new DAOAdresse();
  private ArrayList<String> nomUtilisateur = new ArrayList<>();

  /**
   * Test de la méthode insert (admin).
   */
  @Test
  void insertAdmin() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(nomUtilisateur());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    testUtilisateur.setMdp(retourUtilisateur.getMdp());

    // compare l'objet retourné par la méthode insert (admin)
    assertEquals(retourUtilisateur, testUtilisateur);
    retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    assertNull(retourUtilisateur);
  }
  
  /**
   * Test à de la méthode insert (producteur)
   * Dépend des méthodes insert (admin) et insert (adresse) de la classe DAOAdresse
   */
  @Test
  void insertProducteur() {
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(nomUtilisateur());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test");
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);

    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement");
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    Producteur retourProducteur = TEST_DAOUtilisateur.insert(testProducteur);

    // compare l'objet retourné par la méthode insert (producteur)
    assertEquals(retourProducteur, testProducteur);
    retourProducteur = TEST_DAOUtilisateur.insert(testProducteur);
    assertNull(retourProducteur);
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  @Test
  void find() {
  }

  @Test
  void findById() {
  }

  @Test
  void findByNomUtilisateur() {
  }

  @Test
  void authentification() {
  }
  
  /**
   * Génère un nouveau nomUtilisateur
   *
   * @return String un nouveau nom utilisateur.
   */
  private String nomUtilisateur() {
    String nouveauNom;
    do {
      nouveauNom = "nomUtilisateur" + (int) ((Math.random() * 1000) + 0);
    } while (nomUtilisateur.contains(nouveauNom));
    this.nomUtilisateur.add(nouveauNom);
    return nouveauNom;
  }
}