package fr.roman.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Classe implémentant la connexion avec la base de donnée.
 * Permet de retourner un objet {@link Connection} pour les {@link fr.roman.dao DAO} de l'application.
 * Suit le patron de conception "Singleton".
 */
public class SingletonConnection {

  private static Connection co;
  /**
   * Fichier de connection à la base de donnée.
   * A comme séparateur '=', et contient au minimum 3 champs : "{@link #url}", "{@link #login}" et "{@link #password}".
   */
  private final File FICHIER_CONNECTION = new File("src/main/resources/connectionBDD.txt");
  private final String url;
  private final String login;
  private final String password;

  /**
   * Constructeur privé de la classe {@link SingletonConnection}.
   *
   * @throws Exception Si la connection n'a pas eu lieu.
   */
  private SingletonConnection() throws Exception {
    try {
      HashMap<String, String> donneesConnection = getDonneesConnection();
      this.url = donneesConnection.get("url");
      this.login = donneesConnection.get("login");
      this.password = donneesConnection.get("password");
      co = DriverManager.getConnection(url,login,password);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("Erreur dans la lecture du fichier de connection connectionBDD.txt");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("La base de données est inaccessible." +
                             "Vérifiez les informations de connection.");
    }

  }

  /**
   * Cette méthode sert à récupérer les informations stockées dans le fichier de connection à la base.
   *
   * @return Un {@link HashMap} des informations de connection trouvé.
   * @throws IOException Si le fichier n'est pas lisible
   */
  private HashMap<String, String> getDonneesConnection() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(FICHIER_CONNECTION));
    HashMap<String, String> donnees = new HashMap<String, String>();
    int indiceLigne = 0;
    String ligne = br.readLine();
    while(ligne != null){
      // On parcourt chaque ligne du fichier de connection.
      String[] colonnesLigne = ligne.split("="); // On récupère les informations avec le séparateur '='
      donnees.put(colonnesLigne[0], colonnesLigne[1]);
      indiceLigne++;
      ligne = br.readLine();
    }
    br.close();
    return donnees;
  }

  /**
   * Méthode statique permettant d'obtenir l'unique instance de {@link Connection}.
   *
   * @return L'objet {@link Connection}.
   * @throws Exception Si la connection n'a pas pu être établie
   */
  public static Connection getInstance() throws Exception {
    if(co == null) {
      new SingletonConnection();
    }
    return co;
  }

}