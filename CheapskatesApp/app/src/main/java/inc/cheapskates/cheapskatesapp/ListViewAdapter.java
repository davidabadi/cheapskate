package inc.cheapskates.cheapskatesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Resturant> {

    private List<Resturant> resturantList;

    private Context mContext;

    public ListViewAdapter(Context mContext, List<Resturant> resturantList) {

        super(mContext, R.layout.layout_row_list_items, resturantList);
        this.resturantList = resturantList;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View listViewItem = inflater.inflate(R.layout.layout_row_list_items, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);

        Resturant resturant = resturantList.get(position);

        textViewName.setText(resturant.getName());

        return listViewItem;
    }
}