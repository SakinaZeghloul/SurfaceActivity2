package openclass.tp.nfa024.cnam.fr.surfaceactivity.imagefinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

public final class MyAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Red",       R.drawable.passage_des_panoramas_1_4));
        mItems.add(new Item("Magenta",   R.drawable.passage_des_panoramas_2_4));
        mItems.add(new Item("Dark Gray", R.drawable.passage_des_panoramas_3_4));
        mItems.add(new Item("Gray",      R.drawable.passage_des_panoramas_4_4));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v=mInflater.inflate(R.layout.grille, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }

        picture = (ImageView) v.getTag(R.id.picture);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}