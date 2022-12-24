package fr.roman.dao;

import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la classe {@link DAOProducteur}
 * Dépend des méthodes insert (DAOUtilisateur et DAOAdresse).
 *
 * @author Valentin Leclair
 */
class DAOProducteurTest {
  private final DAOProducteur TEST_DAOProducteur;

  {
    try {
      TEST_DAOProducteur = new DAOProducteur();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private final DAOUtilisateur TEST_DAOUtilisateur;

  {
    try {
      TEST_DAOUtilisateur = new DAOUtilisateur();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private final DAOAdresse TEST_DAOAdresse;

  {
    try {
      TEST_DAOAdresse = new DAOAdresse();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test de la méthode insert().
   */
  @Test
  void insert() {
    // TODO: 24/12/2022 Modifier car on ajoute l'utilisateur en même temps que le producteur et on chiffre le mdp dans le ctrl
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur("nomUtilisateur"+UUID.randomUUID());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
  
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test"+UUID.randomUUID());
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    Producteur retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    
    // compare l'objet retourné par la méthode insert.
    assertEquals(retourProducteur, testProducteur);
    // test d'insérer un producteur déjà ajouter.
    retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    assertNull(retourProducteur);
  }
  
  /**
   * Test de la méthode update().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void update() {
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur("nomUtilisateur"+UUID.randomUUID());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
  
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test"+UUID.randomUUID());
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    Producteur retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    
    //
    retourProducteur.setNomEtablissement("upgradeNomÉtablissement");
    retourProducteur.setNomEtablissement("upgradeNomÉtablissement");
    assertTrue(TEST_DAOProducteur.update(retourProducteur));
    //
    Producteur updateProducteur = TEST_DAOProducteur.findById(retourProducteur.getIdProducteur());
    //
    assertEquals(retourProducteur, updateProducteur);
  }
  
  /**
   * Test de la méthode delete().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void delete() {
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
  
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test"+UUID.randomUUID());
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    Producteur retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    
    // test de suppression du Producteur ajouter à la BD.
    assertTrue(TEST_DAOProducteur.delete(retourProducteur.getIdProducteur()));
    // test recherche de l'utilisateur supprimer.
    assertNull(TEST_DAOProducteur.findById(retourProducteur.getIdProducteur()));
    // test de suppression d'un Producteur inexistant.
    assertFalse(TEST_DAOProducteur.delete(retourProducteur.getIdProducteur()+1));
  }
  
  /**
   * Test de la méthode find().
   * Dépend de la méthode insert().
   */
  @Test
  void find() {
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur("nomUtilisateur"+UUID.randomUUID());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
  
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test"+UUID.randomUUID());
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    TEST_DAOProducteur.insert(testProducteur);
  
    // recherche les producteurs qui ont comme tel "01234567891234"
    HashMap<Producteur.Champs, String> criteres = new HashMap<>();
    criteres.put(Producteur.Champs.tel, "01234567891234");
    ArrayList<Producteur> retourFind = TEST_DAOProducteur.find(criteres);
    // test si tous les producteurs retournés par find() sont corrects.
    retourFind.forEach(x -> assertEquals("01234567891234", x.getTel()));
  }
  
  /**
   * Test de la méthode findById().
   * Dépend de la méthode insert().
   */
  @Test
  void findById() {
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur("nom utilisateur"+UUID.randomUUID());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
  
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test"+UUID.randomUUID());
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    Producteur retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    
    // test rechercher du producteur ajouter.
    assertEquals(retourProducteur, TEST_DAOProducteur.findById(retourProducteur.getIdProducteur()));
    // test recherche d'un producteur qui n'existe pas dans la BD.
    assertNull(TEST_DAOProducteur.findById(retourUtilisateur.getIdUtilisateur()));
  }
}