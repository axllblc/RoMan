package fr.roman.controleurs.carte;
import com.google.gson.*;
import fr.roman.modeles.Adresse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe permettant d'interagir avec l'API Adresse de data.gouv.fr
 */
public class rechercherLieu {
    /**
     * Cette méthode statique permet de rechercher une adresse sur l'API
     *  et retourne un {@link ArrayList} de résultats correspondant, dans la limite de 15
     * @param a Un objet {@link Adresse} avec les champs complété pour la recherche
     * @return Les adresses trouvées
     * @throws Exception Si une erreur s'est produite lors de la recherche
     */
    public static ArrayList<Adresse> rechercher(Adresse a) throws Exception {
        // configuration
        URL url = new URL(construireURL(a));
        HttpURLConnection co = (HttpURLConnection) url.openConnection();
        co.setRequestMethod("GET");
        co.setRequestProperty("Content-Type", "application/json");
        ArrayList<Adresse> lieux = new ArrayList<>();
        if (co.getResponseCode() == 200){ // succès requête
            BufferedReader reader = new BufferedReader(new InputStreamReader(co.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonArray ensemble = JsonParser.parseString(response.toString())
                    .getAsJsonObject().getAsJsonArray("features");
            for (JsonElement element: ensemble){ // Pour chaque adresse trouvée
                JsonObject lieuJson = element.getAsJsonObject();
                Adresse adresse = new Adresse();
                JsonArray coGPS = lieuJson.getAsJsonObject("geometry").getAsJsonArray("coordinates");
                // coordonneesGPS
                adresse.setCoordonneesGPS(new double[]{coGPS.get(1).getAsDouble(),coGPS.get(0).getAsDouble()});
                Map<String, JsonElement> prop = lieuJson.getAsJsonObject("properties").asMap();
                // numeroVoie
                String num = "";
                if(!prop.isEmpty()){
                    if (prop.get("housenumber") != null){
                        num = prop.get("housenumber").getAsString();
                    }
                    Matcher matchNum = Pattern.compile("\\d{1,10}").matcher(num);
                    if(matchNum.find()){
                        adresse.setNumeroVoie(Integer.parseInt(matchNum.group(0)));
                    }
                    // complementNumero
                    Matcher matchComp = Pattern.compile("[a-zA-Z]{1,10}").matcher(num);
                    if(matchComp.find()){
                        adresse.setComplementNumero(matchComp.group(0));
                    }
                    // voie
                    if(prop.get("street")  != null){
                        adresse.setVoie(prop.get("street").getAsString());
                    }
                    // codePostal
                    adresse.setCodePostal(prop.get("postcode").getAsInt());
                    // ville
                    adresse.setVille(prop.get("city").getAsString());
                    lieux.add(adresse); // ajout à la liste/tableau
                }
            }
        }
        else{
            throw new Exception(new Throwable("Echec de la requête pour la recherche d'adresses"));
        }
        return lieux;
    }

    /**
     * Pour construire une url selon les critères de recherche.
     * @param a L'adresse avec les critères de recherche
     * @return L'URL sous forme de chaine de caractère correspondant.
     */
    private static String construireURL(Adresse a) {
        StringBuilder url = new StringBuilder("https://api-adresse.data.gouv.fr/search/?q=");
        if (a.getNumeroVoie() != 0) {
            url.append(a.getNumeroVoie()).append("+");
        }
        if (a.getComplementNumero() != null){
            url.append(a.getComplementNumero()).append("+");
        }
        if (a.getVoie() != null) {
            url.append(a.getVoie().replace(" ","+")).append("+");
        }
        if (a.getComplementAdresse() != null) {
            url.append(a.getComplementAdresse()).append("+");
        }
        if (a.getCodePostal() != 0) {
            url.append(a.getCodePostal()).append("+");
        }
        if(a.getVille() != null) {
            url.append(a.getVille()).append("+");
        }
        url.deleteCharAt(url.length()-1); // on retire le dernier +
        return url.append("&type=housenumber&limit=15").toString();
    }
}
