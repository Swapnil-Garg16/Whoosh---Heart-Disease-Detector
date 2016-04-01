package com.zriton.whoosh;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by hp on 01-04-2016.
 */
public class CustomAdapter extends BaseAdapter {

    MediaPlayer player;
    String [] result;
    Context context;
    ImageView iv;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Context context, String[] sounds) {
        // TODO Auto-generated constructor stub
        result=sounds;
        this.context=context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView iv;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.sound_name);
        holder.iv=(ImageView)rowView.findViewById(R.id.imageView2);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetFileDescriptor afd = null;
                try {
                    afd = context.getAssets().openFd("samplesound.wav");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.start();
            }
        });
        holder.tv.setText(result[position]);
        return rowView;
    }
}
