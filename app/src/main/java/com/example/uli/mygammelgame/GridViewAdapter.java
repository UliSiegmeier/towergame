package com.example.uli.mygammelgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.tower.Tower;

/**
 * Created by uli on 2/12/17.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    // TODO how to update from model?
    private Tower tower;

    public GridViewAdapter(Context context, Tower tower) {
        this.context = context;
        this.tower = tower;
    }

    @Override
    public int getCount() {
        return tower.getBuildingsArray().length;
    }

    @Override
    public Object getItem(int position) {
        return tower.getBuildingsArray()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
//        Item item = (Item)tower.getBuildings().entrySet().toArray()[position];
//        return item.getGameId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        final Item building = (Item) tower.getBuildingsArray().get(position);
        final Item building = tower.getBuildingsArray()[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.gridcell_layout_dummy, null);
        }

        // what to display
        final TextView displayNameTextView = (TextView) convertView.findViewById(R.id.textview_towercell_displayname);
        String buildingText;
        if (building == null)
            buildingText = "EMPTY";
        else
            buildingText = building.getDisplayName();
        displayNameTextView.setText(position +"\n"+ buildingText);

        return convertView;
    }
}
