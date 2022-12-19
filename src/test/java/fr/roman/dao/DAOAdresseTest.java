package fr.roman.dao;

import fr.roman.modeles.Adresse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la classe {@link DAOAdresse}
 *
 * @author Valentin Leclair
 */
class DAOAdresseTest {
  private final DAOAdresse TEST_DAO = new DAOAdresse();

  /**
   * Compare le résultat de la méthode <i>insert()</i>.
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
    Adresse retourAdresse = TEST_DAO.insert(testAddresse);

    // test si la méthode insert renvoi l'objet donné en paramètre.
    assertEquals(addresseAttendu, retourAdresse);
    assertNotEquals(addresseAttendu.getIdAdresse(), retourAdresse.getIdAdresse());
    addresseAttendu.setVille("paris");
    assertNotEquals(addresseAttendu, retourAdresse);
    assertNotEquals(addresseAttendu.getIdAdresse(), retourAdresse.getIdAdresse());
  }

  /**
   * Test de la méthode <i>update()</i>.
   * Dépend des méthodes <i>insert()</i> et <i>findById()</i>.
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
    Adresse retourAdresse = TEST_DAO.insert(testAddresse);

    // modification de l'objet Adresse.
    retourAdresse.setVille("paris");
    assertTrue(TEST_DAO.update(retourAdresse));
    // recherche l'objet modifier
    Adresse updateAdresse = TEST_DAO.findById(retourAdresse.getIdAdresse());
    // test si l'objet a bien été modifié.
    assertEquals(retourAdresse,updateAdresse);
  }

  /**
   * Test la méthode <i>delete()</i> via l'idAresse.
   * Dépend des méthodes <i>insert()</i> et <i>findById()</i>.
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
    Adresse retourAdresse = TEST_DAO.insert(testAddresse);

    // test suppression de la dernière adresse ajouter à la BD.
    assertTrue(TEST_DAO.delete(retourAdresse.getIdAdresse()));
    // test recherche de l'adresse supprimer.
    assertNull(TEST_DAO.findById(retourAdresse.getIdAdresse()));
    // test suppression d'une adresse qui n'existe pas dans la BD.
    assertFalse(TEST_DAO.delete(retourAdresse.getIdAdresse()+1));
  }

  /**
   * Test de la méthode <i>find()</i>.
   * Dépend de la méthode <i>insert()</i>.
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
    TEST_DAO.insert(testAddresse);

    // recherche les adresses qui ont comme libelle "test".
    HashMap<Adresse.Champs, String> criteres = new HashMap<>();
    criteres.put(Adresse.Champs.libelle, "test");
    ArrayList<Adresse> retourFind = TEST_DAO.find(criteres);
    // test si toutes les adresses retournées par find() sont correctes.
    retourFind.forEach(x -> assertEquals("test", x.getLibelle()));
  }

  /**
   * Test de la méthode <i>finById()</i>.
   * Dépend de la méthode <i>insert()</i>.
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
    Adresse retourAdresse = TEST_DAO.insert(testAddresse);

    // test recherche de l'adresse ajouter.
    assertEquals(retourAdresse, TEST_DAO.findById(retourAdresse.getIdAdresse()));
    // test recherche d'une adresse qui n'existe pas dans la BD.
    assertNull(TEST_DAO.findById(retourAdresse.getIdAdresse()+1));
  }
}