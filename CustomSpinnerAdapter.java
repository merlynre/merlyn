package com.pixelturf.singpraises.Utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 9/12/2017.
 */

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> asr;


        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(activity);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            Typeface tf=Typeface.createFromAsset(activity.getAssets(),"fonts/OpenSans.ttf");
            txt.setTypeface(tf);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(activity);
            txt.setGravity(Gravity.CENTER);
            Typeface tf=Typeface.createFromAsset(activity.getAssets(),"fonts/OpenSans.ttf");
            txt.setTypeface(tf);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(14);
       //     txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_down, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }

