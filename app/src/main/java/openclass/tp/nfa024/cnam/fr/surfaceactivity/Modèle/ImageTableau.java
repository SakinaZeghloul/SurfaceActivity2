package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;


public class ImageTableau {

    String mNomTableau;

    String mNomPerso;
    int mDetecte;

    public ImageTableau (String nomPerso, String nomTableau, int detecte) {

        this.mNomPerso=nomPerso;
        this.mNomTableau=nomTableau;
        this.mDetecte=detecte;
    }

    public String getNomPerso() {
        return mNomPerso;
    }

    public void setNomPerso(String nomPerso) {
        mNomPerso = nomPerso;
    }

    public String getNomTableau() {
        return mNomTableau;
    }

    public void setNomTableau(String nomTableau) {
        mNomTableau = nomTableau;
    }

    public int getDetecte() {
        return mDetecte;
    }

    public void setDetecte(int detecte) {
        mDetecte = detecte;
    }
}
