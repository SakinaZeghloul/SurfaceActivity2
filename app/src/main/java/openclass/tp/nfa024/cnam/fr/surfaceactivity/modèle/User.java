package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;


import androidx.annotation.Nullable;

public class User {

    private String uid;
    private String username;
    private Boolean isMentor;
    @Nullable
    private String urlPicture;

    public User(){}

    public User(String uid, String username, String urlPicture){
        this.uid=uid;
        this.username=username;
        this.urlPicture=urlPicture;
        this.isMentor=false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsMentor() {
        return isMentor;
    }

    public void setMentor(Boolean mentor) {
        isMentor = mentor;
    }

    @Nullable
    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(@Nullable String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
