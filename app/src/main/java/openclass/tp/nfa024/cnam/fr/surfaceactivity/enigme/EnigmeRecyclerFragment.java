package openclass.tp.nfa024.cnam.fr.surfaceactivity.enigme;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.HashMap;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.ImageTableau;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.POIHash;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnigmeRecyclerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private HashMap<String, POI> mPOIHashMap;
    private HashMap<Integer, ImageTableau> mImageHash;
 //   private OnButtonClickedListener mCallback;



    public EnigmeRecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mImageHash= POIHash.getImageHash();
        mPOIHashMap= PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);

        final ArrayList<ExampleItem> exampleList=new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.vendome, "Vendôme", "Trouvez " +
                "la couronne qui se trouve sur le socle !"));
        exampleList.add(new ExampleItem(R.drawable.ic_napoleon, "Napoléon", "Trouvez" +
                "Joséphine de Beauharnais, Louis Bonaparte et Cambacérès"));
        exampleList.add(new ExampleItem(R.drawable.ic_beatles, "Beatles", "Trouvez Karl" +
                "Marx, Oscar Wilde et Marlon Brando"));
        exampleList.add(new ExampleItem(R.drawable.ic_raphael, "Raphaël", "Trouvez Aristote, " +
                "Alexandre Le Grand et Pythagore"));

        View view=inflater.inflate(R.layout.fragment_enigme_recycler, container, false);

        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);

        mLayoutManager=new LinearLayoutManager(getActivity());
        mAdapter=new ExampleAdapter(getActivity(), exampleList);

        mRecyclerView.setLayoutManager((mLayoutManager));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                exampleList.get(position);
            }
        });


        return view;
    }
}
