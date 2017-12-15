package com.example.senamit.spinnertest;

import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerType;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    int postion;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("Option", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Button btnNewActvity = (Button)findViewById(R.id.btnnewInten);
        Log.i(LOG_TAG, "the value os position in oncreate "+postion);

        spinnerType = (Spinner)findViewById(R.id.spinner_type);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.movieType, android.R.layout.simple_spinner_item );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(arrayAdapter);

        spinnerType.setOnItemSelectedListener(this);

        btnNewActvity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChildOpen.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        postion=pos;
        Toast.makeText(MainActivity.this, "the selected item is "+spinnerType.getSelectedItem() +"positionis "+pos, Toast.LENGTH_LONG).show();
        Log.i(LOG_TAG, "the selected items is "+postion);
        editor.putInt("positionKey",pos);
        editor.commit();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(LOG_TAG, "the postion is inside saveinstanve "+postion);
        outState.putInt("position",postion);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        postion= savedInstanceState.getInt("position");
        Log.i(LOG_TAG, "the value of position obtained is "+postion);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "inside onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "inside ondestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "inside onpause");
    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        Log.i(LOG_TAG, "inside onresume");
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "inside onStart");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "inside restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "inside on resume");
        int snipperValue = sharedPreferences.getInt("positionKey",-1);
        if (snipperValue!=-1){
            spinnerType.setSelection(snipperValue);
        }
        Log.i(LOG_TAG, "the value os sharedprefernce in oncreate "+snipperValue);
    }
}
