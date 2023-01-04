package fr.roman.dao;

import fr.roman.controleurs.inscription.OutilsMotDePasse;
import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
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
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur("nomUtilisateur"+UUID.randomUUID());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);

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
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAOAdresse.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret(UUID.randomUUID().toString().substring(0, 14));
    testProducteur.setNomEtablissement("nomÉtablissement");
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(testUtilisateur);
    Producteur retourProducteur;
    try {
      retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // compare l'objet retourné par la méthode insert (producteur).
    assertEquals(retourProducteur, testProducteur);
    // test d'insérer un producteur déjà ajouter.
    try {
      TEST_DAOUtilisateur.insert(testProducteur);
      fail();
    } catch (Exception e) {
      assertEquals("Siret déjà renseignée", e.getMessage());
    }
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
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);

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
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAOAdresse.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret(UUID.randomUUID().toString().substring(0, 14));
    testProducteur.setNomEtablissement("nomÉtablissement");
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(testUtilisateur);
    Producteur retourProducteur;
    try {
      retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // modification de l'objet Producteur.
    retourProducteur.setNomEtablissement("upgradeNomÉtablissement");
    retourProducteur.setNomEtablissement("upgradeNomÉtablissement");
    try {
      assertTrue(TEST_DAOProducteur.update(retourProducteur));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // recherche l'objet Producteur récemment modifier.
    Producteur updateProducteur;
    try {
      updateProducteur = TEST_DAOProducteur.findById(retourProducteur.getIdProducteur());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test l'égalité entre l'objet et les données de la BD.
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
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
  
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
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAOAdresse.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret(UUID.randomUUID().toString().substring(0, 14));
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(testUtilisateur);
    Producteur retourProducteur;
    try {
      retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test de suppression du Producteur ajouter à la BD.
    try {
      assertTrue(TEST_DAOProducteur.delete(retourProducteur.getIdProducteur()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test recherche de l'utilisateur supprimer.
    try {
      assertNull(TEST_DAOProducteur.findById(retourProducteur.getIdProducteur()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
  
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
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAOAdresse.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret(UUID.randomUUID().toString().substring(0, 14));
    testProducteur.setNomEtablissement("nomÉtablissement"+UUID.randomUUID());
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(testUtilisateur);
    try {
      TEST_DAOProducteur.insert(testProducteur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // recherche les producteurs qui ont comme tel "01234567891234"
    HashMap<Producteur.Champs, String> criteres = new HashMap<>();
    criteres.put(Producteur.Champs.tel, "01234567891234");
    ArrayList<Producteur> retourFind;
    try {
      retourFind = TEST_DAOProducteur.find(criteres);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
    testUtilisateur.setNomUtilisateur("nomUtilisateur"+UUID.randomUUID());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);

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
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAOAdresse.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret(UUID.randomUUID().toString().substring(0, 14));
    testProducteur.setNomEtablissement("nomÉtablissement");
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(testUtilisateur);
    Producteur retourProducteur;
    try {
      retourProducteur = TEST_DAOProducteur.insert(testProducteur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test rechercher du producteur ajouter.
    try {
      assertEquals(retourProducteur, TEST_DAOProducteur.findById(retourProducteur.getIdProducteur()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test recherche d'un producteur qui n'existe pas dans la BD.
    try {
      assertNull(TEST_DAOProducteur.findById(testUtilisateur.getIdUtilisateur()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}