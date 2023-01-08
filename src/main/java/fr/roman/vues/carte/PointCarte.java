package fr.roman.vues.carte;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import fr.roman.controleurs.lieux.CtrlCarte;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Classe implémentant un point sur la carte gérée par {@link CtrlCarte}.
 * Le point est représenté par la forme de JavaFX {@link Circle} et est en rouge.
 */
public class PointCarte extends MapLayer {
    private final MapPoint mapPoint;
    private final Circle cercle = new Circle(5, Color.RED);

    /**
     * Le Constructeur.
     *
     * @param latitude la latitude du point créé
     * @param longitude la longitude du point créé
     */
    public PointCarte(double latitude, double longitude) {
        mapPoint = new MapPoint(latitude, longitude);
        this.getChildren().add(cercle);
    }


    @Override
    protected void layoutLayer() {
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
        cercle.setTranslateX(point2d.getX());
        cercle.setTranslateY(point2d.getY());
    }

    /**
     * Pour récupérer le point en tant que coordonnée pour interprétable par {@link MapView}.
     *
     * @return Le point en tant que coordonnées.
     */
    public MapPoint getPoint() {
        return mapPoint;
    }
}
