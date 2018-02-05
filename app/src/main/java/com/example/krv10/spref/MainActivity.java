package com.example.krv10.spref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private static final String SHARED_PREF_NAME = "mysharedpref";
    private static final String KEY_NAME = "keyname";
    SharedPreferences spref;
    String name;

    @BindView(R.id.et_name)
    EditText et;
    @BindView(R.id.tv_show)
    TextView tv;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.next)
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.save)
    public void save(View view){
        name();
        show();
    }
    @OnClick(R.id.clear)
    public void setClear(View view){
        spref=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=spref.edit();
        editor.remove(KEY_NAME);
        editor.apply();

        tv.setText(" ");
        Toast.makeText(view.getContext(),"cleared",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.et_name)
    public void name(){

        name=et.getText().toString();
        if(name.isEmpty()){
            et.setError("please enter your name");
            et.requestFocus();
            return;
        }

        spref=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=spref.edit();

        editor.putString(KEY_NAME,name);
        editor.apply();

        et.setText("");
    }
    @OnClick(R.id.tv_show)
    public void show(){

        spref=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String username=spref.getString(KEY_NAME,null);
        if(username!=null) {
            tv.setText(Html.fromHtml("<h2>Welcome to my world</h2>")+username);

        }
    }
    @OnClick(R.id.next)
    public void next(){


        if( tv.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this,"name is empty",Toast.LENGTH_SHORT).show();

        }else{
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            i.putExtra("pass",  tv.getText().toString());
            startActivity(i);
        }
    }
}
