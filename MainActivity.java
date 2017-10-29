package com.pixelturf.singpraises.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pixelturf.singpraises.R;
import com.pixelturf.singpraises.Utility.CustomAdapter;
import com.pixelturf.singpraises.Utility.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    CustomSpinnerAdapter customSpinnerAdapter;
    ListView listview1;
    Toolbar toolbar;
    ListView lv;
    Context context;
    ArrayList prgmName;
    Locale myLocale;
    ArrayList<String> languages;
    ArrayAdapter<String> aa;
    Spinner spinnerCustom;
    ProgressDialog progressdialog;
    public static int [] prgmImages={
            R.drawable.daily_prayer,
            R.drawable.cross,
            R.drawable.cross_bible,
            R.drawable.cross_icon,
            R.drawable.images1,
            R.drawable.holy_spirit,
            R.drawable.cal,
            R.drawable.rosary,
            R.drawable.image_2,
            R.drawable.holy_spirit,
            R.drawable.images};
    public String [] prgmNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/OpenSans.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );
            //....

        setContentView(R.layout.activity_main);
        /*Locale firstLaunchLocale = Locale.FRENCH;
        Locale baseLocale = Locale.ENGLISH;*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listview1= (ListView) findViewById(R.id.listview1);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        prgmNameList=MainActivity.this.getResources().getStringArray(R.array.fi);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        spinnerCustom= (Spinner) findViewById(R.id.spinnerCustom);
        // Spinner Drop down elements
        languages = new ArrayList<String>();
        languages.add("Select your Language");
        languages.add("English");
        languages.add("Kannada");
        languages.add("Tamil");
        languages.add("Hindi");
       // String viewoption[] = {"Select your Language","English", "Kannada","Tamil","Hindi"};
       // aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, viewoption);

       // aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //spinnerCustom.setAdapter(aa);
        progressdialog=new ProgressDialog(MainActivity.this);
        customSpinnerAdapter=new CustomSpinnerAdapter(MainActivity.this,languages);
        spinnerCustom.setAdapter(customSpinnerAdapter);


        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                // Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
                if (pos == 1) {

                    progressdialog.setMessage("Loading Language");
                    progressdialog.show();
                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                   // setLocale("en");=
                            new localeData()
                                    .execute("en");

                } else if (pos == 2) {
                    progressdialog.setMessage("Loading Language");
                    progressdialog.show();
                    Toast.makeText(parent.getContext(),
                            "You have selected Kannada", Toast.LENGTH_SHORT)
                            .show();
                   // setLocale("kn");
                    new localeData()
                            .execute("kn");
                } else if (pos == 3) {
                    progressdialog.setMessage("Loading Language");
                    progressdialog.show();
                    Toast.makeText(parent.getContext(),
                            "You have selected Tamil", Toast.LENGTH_SHORT)
                            .show();
                   // setLocale("ta");
                    new localeData()
                            .execute("ta");
                } else if (pos == 4) {
                    progressdialog.setMessage("Loading Language");
                    progressdialog.show();
                    Toast.makeText(parent.getContext(),
                            "You have selected Hindi", Toast.LENGTH_SHORT)
                            .show();
                   // setLocale("hi");
                    new localeData()
                            .execute("hi");

                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
        context=this;

        lv=(ListView) findViewById(R.id.listview1);
        lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages));

    }

    public class localeData extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            myLocale = new Locale(params[0]);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(MainActivity.this,MainActivity.class);

            startActivity(refresh);
            finish();
            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressdialog.dismiss();
        }
    }

        public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this,MainActivity.class);

        startActivity(refresh);
        finish();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
