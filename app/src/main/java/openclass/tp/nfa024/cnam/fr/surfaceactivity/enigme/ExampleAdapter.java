package openclass.tp.nfa024.cnam.fr.surfaceactivity.enigme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> mExampleList;
    private Context mContext;
    private final static String TAG="ExampleAdapter";


    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_list, parent,
                false);

        int height=parent.getMeasuredHeight()/4;
        int width=parent.getMeasuredWidth();

        v.setLayoutParams(new RelativeLayout.LayoutParams(width, height));

        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, final int position) {

        final ExampleItem currentItem=mExampleList.get(position);
        int row_index=-1;

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, currentItem.getText1(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(mContext, Enigme.class);
                intent.putExtra("image", currentItem.getText1());
                mContext.startActivity(intent);
            }
        });

        if(row_index==position){
            holder.mTextView.setBackgroundColor(Color.parseColor("#90EE90"));
            holder.mTextView2.setBackgroundColor(Color.parseColor("#90EE90"));
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public ExampleAdapter (Context context, ArrayList<ExampleItem> exampleList){

        mExampleList=exampleList;
        mContext=context;

    }

    private OnitemClickListener mListener;

    public interface OnitemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnitemClickListener listener) {
        mListener=listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;
        public TextView mTextView2;
        public RelativeLayout mRelativeLayout;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView);
            mTextView=itemView.findViewById(R.id.textView);
            mTextView2=itemView.findViewById(R.id.textView2);
            mRelativeLayout=itemView.findViewById(R.id.relativeLayout);
        }
    }
}
