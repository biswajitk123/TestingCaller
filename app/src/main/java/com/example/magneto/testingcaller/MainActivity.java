package com.example.magneto.testingcaller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText e;
    TextView t;
    ToggleButton tbtn;
    public static ArrayList<String> s=new ArrayList<String>();
    public static  int count=0,busy=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_PHONE_STATE)){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},1);

            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
            }
        }
        else
        {

        }
        t=(TextView)findViewById(R.id.tv) ;
        e=(EditText)findViewById(R.id.phn);
        btn=(Button)findViewById(R.id.mybtn);
        tbtn=(ToggleButton)findViewById(R.id.tg);
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                String input=e.getText().toString();
                if(input.equals("")){
                    t.setText("Input IS NULL!!!");
                }
                else
                {
                    if(input.length()<10){
                        t.setText("InValid Phone No!!!!");
                    }
                    else
                    {
                        count++;
                        if(count<4){
                            s.add(input);
                            Toast.makeText(MainActivity.this,"Number Added Successfully!->"+input,Toast.LENGTH_LONG).show();


                        }
                        else t.setText("SIZE IS FULL!!!");
                        e.setText("");
                        Intent toy=new Intent(MainActivity.this,Add.class);
                        startActivity(toy);
                    }
                }

            }
        });
        tbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    busy=1;
                    Toast.makeText(MainActivity.this, "Busy Mode Enabled!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    busy=0;
                    Toast.makeText(MainActivity.this, "Busy Mode Disabled!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void onRequestPermissionsResult(int requestcode,String[] permissions,int[] grantResults){
        switch(requestcode)
        {
            case 1:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permission Granted!",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(this,"Permission Not Granted!",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }
}
