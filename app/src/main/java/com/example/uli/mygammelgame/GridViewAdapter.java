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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Item building = tower.getBuildingsArray()[position];

        if (building == null)
            convertView = inflateEmptySlotView(position);
        else
            convertView = inflateBuildingDummyView(position, building);

        return convertView;
    }

    private View inflateBuildingDummyView(int position, Item building) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View buildingView = inflater.inflate(R.layout.gridcell_layout_dummy, null);

        // set building type text
        String buildingText;
        if (building == null)
            buildingText = "EMPTY";
        else
            buildingText = building.getDisplayName();
        final TextView displayNameTextView = (TextView) buildingView.findViewById(R.id.textview_towercell_displayname);
        displayNameTextView.setText(position +"\n"+ buildingText);

        return buildingView;
    }

    private View inflateEmptySlotView(int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slotView = inflater.inflate(R.layout.gridcell_layout_emptyslot, null);

        String slotText = "+";
        final TextView slotLabelTextView = (TextView) slotView.findViewById(R.id.textview_towercell_emptyslotlabel);
        slotLabelTextView.setText(slotText);

        return slotView;
    }
}


