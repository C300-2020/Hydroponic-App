package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class ControlActivity extends AppCompatActivity {

    Switch swLight, swWater;
    Button btn;

    String waterOn, lightOn, defLight, defWater;
    Boolean controllable;

    FirebaseDatabase fbdb;
    DatabaseReference rfWater, rfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        swWater = findViewById(R.id.switchWater);
        swLight = findViewById(R.id.switchLight);
        btn = findViewById(R.id.btn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Controls");

        Intent i = getIntent();
        controllable = i.getBooleanExtra("controllable", false);

        if(controllable){

            btn.setText("Apply");

            fbdb = FirebaseDatabase.getInstance();
            rfWater = fbdb.getReference("controls").child("1").child("water");
            rfLight = fbdb.getReference("controls").child("1").child("light");

            rfLight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    if (value != null){
                        if(value.equalsIgnoreCase("on")){
                            swLight.setChecked(true);
                            lightOn = "on";
                            defLight = "on";
                        }else if(value.equalsIgnoreCase("off")){
                            swLight.setChecked(false);
                            lightOn = "off";
                            defLight = "off";
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });

            rfWater.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    if (value != null){
                        if(value.equalsIgnoreCase("on")){
                            swWater.setChecked(true);
                            waterOn = "on";
                            defWater = "on";
                        }else if(value.equalsIgnoreCase("off")){
                            swWater.setChecked(false);
                            waterOn = "off";
                            defWater = "off";
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        }else{
            btn.setText("Return");
            Toast.makeText(getApplicationContext(), "Plant's Environment is Uncontrollable", Toast.LENGTH_LONG).show();
        }

        swLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    lightOn = "on";
                }else{
                    lightOn = "off";
                }
            }
        });

        swWater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    waterOn = "on";
                }else{
                    waterOn = "off";
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(controllable){
                   if(!lightOn.equalsIgnoreCase(defLight) || !waterOn.equalsIgnoreCase(defWater)){
                       rfLight.setValue(lightOn);
                       rfWater.setValue(waterOn);
                       Toast.makeText(getApplicationContext(), "Water is switched " + waterOn + "\nLight is switched " + lightOn, Toast.LENGTH_LONG).show();
                   }else{
                       Toast.makeText(getApplicationContext(), "No Changes Made", Toast.LENGTH_LONG).show();
                   }
               }

                finish();
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( ControlActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String mToken = instanceIdResult.getToken();
                Log.d("FCM Token",mToken);
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("1");

    }
}