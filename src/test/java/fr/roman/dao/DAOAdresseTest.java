package fr.roman.dao;

import fr.roman.modeles.Adresse;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la classe {@link DAOAdresse}
 *
 * @author Valentin Leclair
 */
class DAOAdresseTest {
  private final DAOAdresse TEST_DAO;

  {
    try {
      TEST_DAO = new DAOAdresse();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Compare le résultat de la méthode insert().
   * Seul l'idAdresse ne doit pas être égal.
   */
  @Test
  void insert() {
    // création de deux objets Adresse sans identifiant.
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

    Adresse addresseAttendu = new Adresse();
    addresseAttendu.setCoordonneesGPS(coord);
    addresseAttendu.setLibelle("test");
    addresseAttendu.setNumeroVoie(1);
    addresseAttendu.setComplementNumero("numéro");
    addresseAttendu.setVoie("voie");
    addresseAttendu.setComplementAdresse("adresse");
    addresseAttendu.setCodePostal(37000);
    addresseAttendu.setVille("tours");
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAO.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test si la méthode insert renvoi l'objet donné en paramètre.
    assertEquals(addresseAttendu, retourAdresse);
    assertNotEquals(addresseAttendu.getIdAdresse(), retourAdresse.getIdAdresse());
    addresseAttendu.setVille("paris");
    assertNotEquals(addresseAttendu, retourAdresse);
    assertNotEquals(addresseAttendu.getIdAdresse(), retourAdresse.getIdAdresse());
  }

  /**
   * Test de la méthode update().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void update() {
    // création d'un objet Adresse sans identifiant.
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
      retourAdresse = TEST_DAO.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // modification de l'objet Adresse.
    retourAdresse.setVille("paris");
    try {
      assertTrue(TEST_DAO.update(retourAdresse));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // recherche l'objet modifier
    Adresse updateAdresse;
    try {
      updateAdresse = TEST_DAO.findById(retourAdresse.getIdAdresse());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    // test si l'objet a bien été modifié.
    assertEquals(retourAdresse,updateAdresse);
  }

  /**
   * Test la méthode delete() via l'idAdresse.
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void delete() {
    // création d'un objet Adresse sans identifiant.
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
      retourAdresse = TEST_DAO.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test suppression de la dernière adresse ajouter à la BD.
    try {
      assertTrue(TEST_DAO.delete(retourAdresse.getIdAdresse()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    // test recherche de l'adresse supprimer.
    try {
      assertNull(TEST_DAO.findById(retourAdresse.getIdAdresse()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    // test suppression d'une adresse qui n'existe pas dans la BD.
    try {
      assertFalse(TEST_DAO.delete(retourAdresse.getIdAdresse()+1));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test de la méthode find().
   * Dépend de la méthode insert().
   */
  @Test
  void find() {
    // création d'un objet Adresse sans identifiant.
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
    try {
      TEST_DAO.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // recherche les adresses qui ont comme libelle "test".
    HashMap<Adresse.Champs, String> criteres = new HashMap<>();
    criteres.put(Adresse.Champs.libelle, "test");
    ArrayList<Adresse> retourFind;
    try {
      retourFind = TEST_DAO.find(criteres);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    // test si toutes les adresses retournées par find() sont correctes.
    retourFind.forEach(x -> assertEquals("test", x.getLibelle()));
  }

  /**
   * Test de la méthode finById().
   * Dépend de la méthode insert().
   */
  @Test
  void findById() {
    // création d'un objet Adresse sans identifiant.
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
      retourAdresse = TEST_DAO.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test recherche de l'adresse ajouter.
    try {
      assertEquals(retourAdresse, TEST_DAO.findById(retourAdresse.getIdAdresse()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    // test recherche d'une adresse qui n'existe pas dans la BD.
    try {
      assertNull(TEST_DAO.findById(retourAdresse.getIdAdresse()+1));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}