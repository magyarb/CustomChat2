package hu.bme.aut.hf.customchat2;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        TextView textViewMsg = (TextView) itemView.findViewById(R.id.message); //meg a szövegeket, és be is állítjuk
        textViewMsg.setText(item.msg);

        TextView textViewStamp = (TextView) itemView.findViewById(R.id.stamp);
        textViewStamp.setText(item.geocode + "@" + item.timestamp); //TODO other ppl

        //ha kell, áttoljuk
        LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.msglayout);
        LinearLayout wlayout = (LinearLayout) itemView.findViewById(R.id.msgwhole);
        if(item.senderID != Session.user.id) { //ha miénk az üzenet
            layout.setGravity(Gravity.RIGHT);
            wlayout.setGravity(Gravity.RIGHT);
        }
        else
            layout.setBackgroundColor(Color.parseColor("#FFFFA293"));



        return itemView;
    }
}
