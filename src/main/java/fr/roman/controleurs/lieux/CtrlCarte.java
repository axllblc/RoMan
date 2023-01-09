package fr.roman.controleurs.lieux;

import com.gluonhq.maps.MapView;
import fr.roman.vues.carte.PointCarte;

import java.util.ArrayList;

/**
 * Contrôleur de la carte pour afficher des points.
 * Le fond de carte affiché provient d'OpenStreetMap
 *  et on utilise les outils développés par de Gluon Maps.
 */
public class CtrlCarte {
    /**
     * La vue de la carte.
     */
    private final MapView carte;
    /**
     * La liste des points affichés sur la carte.
     */
    private final ArrayList<PointCarte> points = new ArrayList<>();

    /**
     * Constructeur qui créer une carte.
     */
    public CtrlCarte() {
        this.carte = new MapView();
    }

    /**
     * Renvoie la vue de la carte associée au contrôleur.
     *
     * @return la vue de la carte associée au contrôleur
     */
    public MapView getCarte() {
        return carte;
    }

    /**
     * Pour ajouter un point à la carte.
     *
     * @param pointCarte le point à ajouter
     */
    public void ajouterPoint(PointCarte pointCarte){
        carte.addLayer(pointCarte);
        points.add(pointCarte);
        carte.setZoom(13);
        carte.flyTo(0, pointCarte.getPoint(), 0.1);
    }

    /**
     * Retire un point de la carte, s'il est présent.
     *
     * @param pointARetirer le point à retirer
     */
    public void retirerPoint(PointCarte pointARetirer){
        if (points.contains(pointARetirer)){
            points.remove(pointARetirer);
            carte.removeLayer(pointARetirer);
        }
    }

    /**
     * Retourne les points affichés sur la carte.
     *
     * @return Les points affichés sur la carte.
     */
    public ArrayList<PointCarte> getPoints() {
        return points;
    }
}
