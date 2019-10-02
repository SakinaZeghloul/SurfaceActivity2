package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;

import java.util.HashMap;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.alexandre_le_grand;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.archimede;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.beatlesString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.bonaparte;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.cambaceres;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.couronne;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.josephine;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.karl_marx;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.marlon_brando;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.napoleonString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.oscar_wilde;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.pythagore;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.raphaelString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.vendomeString;

public class POIHash {

    static HashMap<String, POI> mPOIHash;
    static HashMap<Integer, ImageTableau> mImageHash;
    static List<ClickableArea> clickableAreas;


    static {

        final ImageTableau couronneTab=new ImageTableau(couronne, vendomeString, 0);

        final ImageTableau josephine_de_beauharnais=new ImageTableau(josephine, napoleonString, 0);
        final ImageTableau cambaceresTab=new ImageTableau(cambaceres, napoleonString, 0);
        final ImageTableau louis_bonaparte=new ImageTableau(bonaparte, napoleonString, 0);

        final ImageTableau marlon_brandoTab=new ImageTableau(marlon_brando, beatlesString, 0);
        final ImageTableau karl_marxTab=new ImageTableau(karl_marx, beatlesString, 0);
        final ImageTableau oscar_wildeTab=new ImageTableau(oscar_wilde, beatlesString, 0);

        final ImageTableau archimedeTab=new ImageTableau(archimede, raphaelString, 0);
        final ImageTableau pythagoreTab=new ImageTableau(pythagore, raphaelString, 0);
        final ImageTableau alexandre_le_grandTab=new ImageTableau(alexandre_le_grand, raphaelString, 0);


        mImageHash=new HashMap<>();

        mImageHash.put(1, couronneTab);

        mImageHash.put(2, josephine_de_beauharnais);
        mImageHash.put(3, cambaceresTab);
        mImageHash.put(4, louis_bonaparte);

        mImageHash.put(5, marlon_brandoTab);
        mImageHash.put(6, karl_marxTab);
        mImageHash.put(7, oscar_wildeTab);

        mImageHash.put(8, archimedeTab);
        mImageHash.put(9, pythagoreTab);
        mImageHash.put(10, alexandre_le_grandTab);
    }

    public static HashMap<Integer, ImageTableau> getImageHash(){
        return mImageHash;
    }


    public static HashMap<String, POI> getPOIHash(){
        return mPOIHash;
    }

    public static List<ClickableArea> getClickableAreasList() { return clickableAreas;}
}
