package hu.bme.aut.hf.customchat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balázs on 2015.05.17..
 */
public class CnvAdapter extends BaseAdapter {
    private final List<Cnv> items;

    public CnvAdapter(final Context context, final ArrayList<Cnv> cnvs)
    {
        items = cnvs;
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Cnv item = items.get(position); //lekéri az item-t, amit fel kell fújni

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService( //beszerzünk egy inflatert
                Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.cnv, null);

        TextView textViewNew = (TextView) itemView.findViewById(R.id.newcnt); //meg a szövegeket, és be is állítjuk
        textViewNew.setText(Integer.toString(item.id)+ " new"); //TODO new messages

        TextView textViewCnv = (TextView) itemView.findViewById(R.id.cnvname);
        textViewCnv.setText(item.uid1); //TODO other ppl

        return itemView;
    }
}
