package com.example.m214_retrofit_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private List<Book> listBooks;
    private Context mContext;
    private int layoutID;

    public BookAdapter(List<Book> listBooks, Context mContext, int layoutID) {
        this.listBooks = listBooks;
        this.mContext = mContext;
        this.layoutID = layoutID;
    }

    @Override
    public int getCount() {
        //Taille de la liste View
        return listBooks.size();
    }

    @Override
    public Object getItem(int position) {

        return listBooks.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater)   mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(layoutID,null);

        viewHolder.txt = convertView.findViewById(R.id.textView3);
        viewHolder.img = convertView.findViewById(R.id.imageView3);

        Book b = listBooks.get(position);

        viewHolder.txt.setText(b.getTitre());

        Picasso.get().load(b.getImg()).into(viewHolder.img);

        return convertView;
    }

    private class ViewHolder {

        TextView txt;
        ImageView img;
    }
}
