package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

    String waterOn, lightOn;

    FirebaseDatabase fbdb;
    DatabaseReference rfWater, rfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        swWater = findViewById(R.id.switchWater);
        swLight = findViewById(R.id.switchLight);
        btn = findViewById(R.id.btn);

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
                    }else if(value.equalsIgnoreCase("off")){
                        swLight.setChecked(false);
                        lightOn = "off";
                    }
                }
                Log.d("From DB - light", "Value is: " + value);
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
                    }else if(value.equalsIgnoreCase("off")){
                        swWater.setChecked(false);
                        waterOn = "off";
                    }
                }
                Log.d("From DB - water", "Value is: " + value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        swLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked == true){
                    Toast.makeText(getApplicationContext(), "Light is switched on", Toast.LENGTH_SHORT).show();
                    lightOn = "on";
                }else{
                    Toast.makeText(getApplicationContext(), "Light is switched off", Toast.LENGTH_SHORT).show();
                    lightOn = "off";
                }
            }
        });

        swWater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    Toast.makeText(getApplicationContext(), "Water is switched on", Toast.LENGTH_SHORT).show();
                    waterOn = "on";
                }else{
                    Toast.makeText(getApplicationContext(), "Water is switched off", Toast.LENGTH_SHORT).show();
                    waterOn = "off";
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rfLight.setValue(lightOn);
                rfWater.setValue(waterOn);

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