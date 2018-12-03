package edu.temple.munz.stockportfolio;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class PortfolioAdapter extends BaseAdapter {

    Context context;
    List<String[]> list;


    public PortfolioAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListView listView = new ListView(context);

        listView.setBackgroundColor(Color.RED);

        return listView;
    }
}
