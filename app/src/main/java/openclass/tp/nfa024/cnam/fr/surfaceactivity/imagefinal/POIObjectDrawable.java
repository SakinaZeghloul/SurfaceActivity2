package openclass.tp.nfa024.cnam.fr.surfaceactivity.imagefinal;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

public class POIObjectDrawable extends AppCompatActivity {

    private ClipDrawable mClipDrawable;
    private int levelInt=0;
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);
        ImageView imageView=(ImageView)findViewById(R.id.image);
        mClipDrawable = (ClipDrawable) imageView.getDrawable();

        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelInt=levelInt+1000;
                mClipDrawable.setLevel(mClipDrawable.getLevel()+levelInt);
            }
        });


    //    GridView gridView = (GridView)findViewById(R.id.gridview);
      //  gridView.setAdapter(new MyAdapter(this));
    }
}
