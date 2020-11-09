package com.example.dolly.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.dolly.Model.SwipeCardsModel;
import com.example.dolly.R;

import java.util.List;
public class arrayAdapter extends ArrayAdapter<SwipeCardsModel>{

    Context context;
    TextView id, name,description,distance;
    public arrayAdapter(Context context, int resourceId, List<SwipeCardsModel> items){
        super(context, resourceId, items);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        SwipeCardsModel card_item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        id = (TextView) convertView.findViewById(R.id.idSwipe);
        name = (TextView) convertView.findViewById(R.id.userNameSwipe);
        description = (TextView) convertView.findViewById(R.id.userDescriptionSwipe);
        distance = (TextView) convertView.findViewById(R.id.userDistanceSwipe);
        id.setText(card_item.getId());
        name.setText(card_item.getName());
        description.setText(card_item.getDescription());
        distance.setText(card_item.getDistance());

        return convertView;

    }
}
