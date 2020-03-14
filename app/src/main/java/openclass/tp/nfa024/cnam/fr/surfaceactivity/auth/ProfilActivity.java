package openclass.tp.nfa024.cnam.fr.surfaceactivity.auth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;

import butterknife.BindView;
import butterknife.OnClick;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.api.UserHelper;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.User;

public class ProfilActivity extends BaseActivity{

    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    private static final int UPDATE_USERNAME = 30;

    @BindView(R.id.profil_activity_imageview_profil)
    ImageView imageViewProfile;
    @BindView(R.id.profil_activity_edit_text_username)
    TextInputEditText textInputEditTextUsername;
    @BindView(R.id.profil_activity_text_view_email)
    TextView textViewEmail;
    @BindView(R.id.profil_activity_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.profil_activity_check_box_is_mentor)
    CheckBox checkBoxIsMentor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureToolbar();
        this.updateUIWhenCreating();
    }

    @OnClick(R.id.profil_activity_button_update)
    public void onClickUpdateButton() {
        this.updateUsernameInFirebase();
    }

    @OnClick(R.id.profil_activity_button_sign_out)
    public void onClickSignOutButton() {
        this.signOutUserFromFirebase();
    }

    @OnClick(R.id.profil_activity_button_delete)
    public void onClickDeleteButton() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteUserFromFirebase();
                            }
                        })
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show();
    }

    private void signOutUserFromFirebase() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this,
                        this.updateUIAfterRESTRequestCompleted(SIGN_OUT_TASK));
    }

    private void deleteUserFromFirebase() {
        if (this.getCurrentUser() != null) {
            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this,
                            this.updateUIAfterRESTRequestCompleted(DELETE_USER_TASK));
        }
    }

    private OnSuccessListener<Void> updateUIAfterRESTRequestCompleted(final int origin) {
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                switch (origin) {
                    case UPDATE_USERNAME:
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
                    case SIGN_OUT_TASK:
                        finish();
                        break;
                    case DELETE_USER_TASK:
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void updateUsernameInFirebase() {

        this.progressBar.setVisibility(View.VISIBLE);
        String username = this.textInputEditTextUsername.getText().toString();

        if (this.getCurrentUser() != null) {
            if (!username.isEmpty() && !username.equals(getString(R.string.info_no_username_found))) {
                UserHelper.updateUsername(username, this.getCurrentUser().getUid())
                        .addOnFailureListener(this.onFailureListener())
                        .addOnSuccessListener(this.updateUIAfterRESTRequestCompleted(UPDATE_USERNAME));
            }
        }
    }

    private void updateUserIsMentor() {
        if (this.getCurrentUser() != null) {
            UserHelper.updateIsMentor(this.getCurrentUser().getUid(),
                    this.checkBoxIsMentor.isChecked())
                    .addOnFailureListener(this.onFailureListener());
        }
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_profil;
    }

    // 1 - Update UI when activity is creating
    private void updateUIWhenCreating() {

        if (this.getCurrentUser() != null) {

            //Get picture URL from Firebase
            if (this.getCurrentUser().getPhotoUrl() != null) {
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageViewProfile);
            }

            //Get email & username from Firebase
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();

            this.textViewEmail.setText(email);

            UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    User currentUser = documentSnapshot.toObject(User.class);
                    String username = TextUtils.isEmpty(currentUser.getUsername()) ? getString(R.string.info_no_username_found) :
                            currentUser.getUsername();
                    checkBoxIsMentor.setChecked(currentUser.getIsMentor());
                    textInputEditTextUsername.setText(username);
                }
            });
        }
    }
}