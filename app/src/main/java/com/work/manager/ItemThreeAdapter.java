package com.work.manager;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ItemThreeAdapter extends BaseAdapter {

    private List<EventInfo> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemThreeAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setObjects(List<EventInfo> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public EventInfo getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_three, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(EventInfo object, ViewHolder holder) {
        holder.weekid.setText(object.weekid);
        holder.tv.setText(object.description);
        for (String name:object.links.keySet()){
            Pattern p = Pattern.compile(name);
            Linkify.addLinks(holder.tv,p,object.links.get(name));
        }
    }

    protected class ViewHolder {
        public TextView tv;
        public TextView weekid;
        public ViewHolder(View view) {
            tv = view.findViewById(R.id.tv);
            weekid = view.findViewById(R.id.weekid);
        }
    }
}
