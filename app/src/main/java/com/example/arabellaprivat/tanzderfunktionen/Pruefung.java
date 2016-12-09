package com.example.arabellaprivat.tanzderfunktionen;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kathi on 09.12.2016.
 * enthält alle Methoden, die für die Prüfung der gezeichneten Funktion mit der original Funktion notwendig sind
 * dadurch wird v.a. die KLasse "Spiel" übersichtlicher
 */

public class Pruefung {
     /** Instanzvariablen */
    /** Liste, die die übergebenen x-Werte des Pfades abspeichert */
    private Liste listeX;
    /** Liste, die die übergebenen y-Werte des Pfades abspeichert */
    private Liste listeY;
    /** Zeichenfläche */
    private Zeichenfläche z;
    /** Hilfspunkte */
    private Hilfspunkte h;
    /** Levelanzeige */
    private int x;

    /**
     *Constructor
     */
    Pruefung (Zeichenfläche zf, Hilfspunkte hp){
        z=zf;
        h=hp;
        listeX=z.getListX();
        listeY=z.getListY();
    }


    /** Methoden *************************************************** */

    /** check ()
     * vergleicht den gezeichneten Pfad mit der jeweils vorgegebenen Funktion
     * @param level gibt das aktuelle level an
     * @return true falls gezeichnete Funktion der Original-Funktion entspricht
    */


    boolean check(int level) {
        double xWert;
        double yWert;
        // index i wird fortlaufend hochgezählt, bis zum Ende der Liste
        int index=0;
        int listLength=listeX.size();
        String s="";
        // Zähler zählt richtige Vergleiche des gezeichneten und berechneten Wert
        int counter=0;

        //TODO nur wenn Extremstellen-Check positiv ist, dann wird weiter geprüft
        if (comparePoints(convertViewToBitmap(h),x)) {
            while (index < listLength) {
                // x-Wert aus der Liste auslesen
                xWert = (listeX.get(index));
                //s=s+" xP: "+xWert;
                // in x-Koordinaten-Werte umwandeln
                xWert = pixelToCoordinate(xWert, z, 10);
                //s=s+" x-K: "+ xWert;
                // entsprechenden y-Wert mit der Funktion berechnen , Level x zeigt an welche Funktion aufgerufen werden soll
                // TODO VICKY + ARABELLA: wie liest man das Level aus??
                switch (x) {
                    case 1:
                        yWert = linearFunction(xWert);
                        // s=s+" y-K: "+yWert;
                        break;
                    case 2:
                        yWert = quadratFunction(xWert);
                        //s=s+" y-K: "+yWert;
                        break;
                    default:
                        yWert = 0;
                }
                // y-Koordinaten-Wert in Pixel umrechnen
                yWert = coordinateToPixel(yWert, z, 6);
                //s=s+" y-P: "+yWert;
                // berechneten y-Wert mit y-Wert aus der Liste vergleichen
                boolean comparison = compare(yWert, listeY, index);
                //s=s+" yV: "+ listY.get(index)+"\n";
                // counter hochzählen
                if (comparison) counter++;
                // index hochzählen
                index++;
            }
            // je nach dem wie viele Vergleiche richtig sind wird der gezeichnete Pfad akzeptiert
            return  (counter > 8);
        }
        else return false;
    }


    /**
     * lineare Funktion: f(x)=0,5x+2
     * hier könnte man eventuell vorgegeben Werte auch aus Datenbank auslesen?
     * @param x zu berechneter x-Wert in Koordinaten-Angaben
     * @return yWert berechneter y-Wert
     */
    private double linearFunction (double x){
        // TODO VICKY auslesen aus der Liste
        // Daten evtl. aus Datenbank auslesen
        double yWert=(0.5*x)+2;
        return  round(yWert);
    }// Ende linearFunction()

    /**
     * quadratische Funktion: 0.25*x^2+1x-4
     * Todo Vicky Datenbank auslesen
     */
    private double quadratFunction (double x){
        double yWert=(0.25*x*x)+x-4;
        return round(yWert);
    }


    /** Methode pixelToCoordinate
     *  rechnet den ausgelesen xWert in Koordinaten-Werte um
     *  abhängig von den maximalen x-WErten und y-WErten!
     *  Vorrausgesetzt dass der Koordinatenursprung in der Mitte des Views liegt
     *
     *  @param pixel  ausgelesener x-WErt in Pixelangaben
     *  @param xMax   maximaler Wert der x-Achse
     *  @return xWert dieser Wert wird in die Funktion eingesetzt
     */
    double pixelToCoordinate ( double pixel, Zeichenfläche z, int xMax){
        double xWert;
        // Liest die maximalen Pixelwerte des Views zurück
        //float maxXPixel = v. getRight-getLeft??
        float widthView=z.getRight()-z.getLeft();
        // Länge von x=0 bis x=xMax
        float halfView=widthView/2;
        // LängenMaß einer Koordinate
        float stepPixels= widthView/(2*xMax);
        xWert=(pixel-halfView)/stepPixels;
        return  round(xWert);
    }// Ende pixelToCoordinate

    /** Methode round
     * rundet einen Float-Wert auf 2 Dezimalstellen genau und gibt ihn als Double-Wert zurück
     * @param f zurundender Float-Wert
     * @return gerunderter Wert als Double
     */
    double round (float f){
        double rounded=Math.round(f*100);
        rounded=rounded/100;
        return rounded;
    }//Ende round

    /** Methode round
     * rundet einen Double-Wert auf 2 Dezimalstellen ab
     * @param d zu rundender Double-Wert
     * @return gerundeter Double-Wert
     */
    private double round (double d){
        double rounded=Math.round(d*100);
        return rounded/100;
    }//Ende round

    /** Methode coordinateToPixel
     * rechnet einen Koordinaten-Wert in Pixelwert um
     * v.a. für y-Werte
     * abhängig von den maximalen y-Werten
     * Vorraussetzung: Koordinatensystem ist zentriert
     *
     * @param coordinate  berechnete y-Koordinaten
     * @param width     maxi
     * @param yMax
     * @return
     */
    private double coordinateToPixel (double coordinate, Zeichenfläche z, float yMax){
        double yWert;
        float widthView=z.getBottom()-z.getTop();
        float stepPixels=widthView/(2*yMax);
        coordinate=yMax-coordinate;
        yWert=coordinate*stepPixels;
        return yWert;
    } // Ende coordinateToPixel

    /** Methode compare()
     * vergleicht den mitgegebenen Wert, mit dem Wert an ensprechender Stelle in der Liste
     * in Pixel-Werten
     * @param d  zu vergleichender Wert (berechnet)
     * @param l  Liste, in der Vergleichswerte gespeichert sind
     * @param index Stelle in der Liste, an der Vergleichswert steht
     * @return true falls Vergleich der zwei Werte übereinstimmt
     */
    private boolean compare (double d, Liste l, int index){
        double tolerance=50;
        float compareValue=l.get(index);
        if (d>=compareValue-tolerance && d<=compareValue+tolerance ) return true;
        else return false;
    }//Ende compare

//TODO  Umwandlung von View zu Bitmap

    /** Methode
     * wandelt View in eine Bitmap um
     * @param v View die umgewandelt werden soll
     * @return Bitmap
     */
    private Bitmap convertViewToBitmap (View v){
        Bitmap map;
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        return map=v.getDrawingCache();
    }

    private boolean comparePoints (Bitmap map, int x){
        switch (x){
            case 1: break;
            case 2: break;
            case 3: break;

        }
        return true;
    }

}
