package hu.bme.aut.hf.customchat2;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balázs on 2015.05.17..
 */
public class MsgAdapter extends BaseAdapter {
    private final List<Msg> items;

    public MsgAdapter(final Context context, final ArrayList<Msg> msgs)
    {
        items = msgs;
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
        final Msg item = items.get(position); //lekéri az item-t, amit fel kell fújni

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService( //beszerzünk egy inflatert
                Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.msg, null);

        RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.msglayout);
        if(item.senderID != Session.user.id) {
            relativeLayout.setBackgroundColor(Color.parseColor("#FFFFA293"));
        }

        TextView textViewNew = (TextView) itemView.findViewById(R.id.message); //meg a szövegeket, és be is állítjuk
        textViewNew.setText(item.msg);

        TextView textViewCnv = (TextView) itemView.findViewById(R.id.stamp);
        textViewCnv.setText(item.geocode + "@" + item.timestamp); //TODO other ppl

        return itemView;
    }
}
