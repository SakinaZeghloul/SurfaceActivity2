package openclass.tp.nfa024.cnam.fr.surfaceactivity.api;


import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.User;


public class UserHelper {

    /**
     * Permet de référencer de manière statique les différentes requêtes réseau CRUD concernant
     * la Collection users.
     */
    private static final String COLLECTION_NAME="users";

    // ---COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createUser(String uid, String username, String urlPicture){
        User userToCreate=new User(uid, username, urlPicture);
        return UserHelper.getUsersCollection()
                .document(uid)
                .set(userToCreate);
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateUsername(String username, String uid){
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateIsMentor(String uid, Boolean isMentor){
        return UserHelper.getUsersCollection().document(uid).update("isMentor", isMentor);
    }

    // --- DELETE ---

    public static Task<Void> deleteUser(String uid){
        return UserHelper.getUsersCollection().document(uid).delete();
    }
}
