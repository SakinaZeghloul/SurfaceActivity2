package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;

public class Personnage {

    String nom;
    int image;

    public Personnage (String nom, int image){

        this.nom=nom;
        this.image=image;
    }

    public String getNom() {
        return nom;
    }

    public int getImage() {
        return image;
    }
}
