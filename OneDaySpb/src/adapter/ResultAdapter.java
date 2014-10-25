package adapter;

/**
 * Created by Alexander on 18.10.2014.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import com.example.onedayspb.R;

import logic.Attraction;

public class ResultAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Attraction> objects;


    public ResultAdapter(Context context, List<Attraction> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_layout, parent, false);
        }
       /* TextClock clock = (TextClock) view.findViewById(R.id.textClock);
        TextView textView = (TextView) view.findViewById(R.id.ListItemTextView);
        textView.setText(objects.get(position));*/
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        String[] data = objects.get(position).getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public int getCount() {
        return objects.size();
    }
}
