package com.aranhid.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Place city = new Place("Irkutsk", 52.17,104.18);

    DatabaseReference databaseReference;

    EditText name, lat, lon;
    TextView tvName, tvLat, tvLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);

        tvName = findViewById(R.id.textViewName);
        tvLat = findViewById(R.id.textViewLat);
        tvLon = findViewById(R.id.textViewLon);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("MyPlace").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Place place = snapshot.getValue(Place.class);
                if (place != null) {
                    tvName.setText("Nmae: " + place.name);
                    tvLat.setText("Lat: " + place.lat.toString());
                    tvLon.setText("Lon: " + place.lon.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClick(View view){
        String sName = name.getText().toString();
        Double sLat = Double.parseDouble(lat.getText().toString());
        Double sLon = Double.parseDouble(lon.getText().toString());
        if (!sName.isEmpty() && sLat != null && sLon != null)
            databaseReference.child("MyPlace").setValue(new Place(sName, sLat, sLon));
    }
}