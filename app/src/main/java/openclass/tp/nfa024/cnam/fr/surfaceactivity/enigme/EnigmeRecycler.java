package openclass.tp.nfa024.cnam.fr.surfaceactivity.enigme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.ImageTableau;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

public class EnigmeRecycler extends AppCompatActivity {//implements EnigmeRecyclerFragment.OnButtonClickedListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private HashMap<String, POI> mPOIHashMap;
    private HashMap<Integer, ImageTableau> mImageHash;
    private EnigmeRecyclerFragment mEnigmeRecyclerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_recycler);
    }
}


