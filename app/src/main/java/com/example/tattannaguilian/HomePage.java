package com.example.tattannaguilian;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.Arrays;
import java.util.List;

public class HomePage extends AppCompatActivity implements GoogleMap.OnMapLoadedCallback, OnMapReadyCallback {

    private GoogleMap gMap;
    private int currentPage = 0;
    private final int DELAY_MS= 3000;
    private Handler handler;
    private ViewPager viewPager;
    private TextPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        viewPager = findViewById(R.id.textViewPager);
        String[] texts = {
                "I Love You So Much (Ay-ayatenka, Launay)",
                "Thank You! (Agyamanak!)",
                "How Are You, My Friend? (Kamusta ka, Gayyemko?)",
                "I'm Doing Great, My Friend (Nasulin-at latta met, Gayyem)",
                "What's Your Name? (Ania ti nagan mo?)",
                "Minnie, But You Can Call Me Mine (Minnie, ngem mabalinnak nga awagan a Mine)",
                "How Much Is This? (Sagmamano daytoy?)",
                "Can I Ask For A Discount? (Mabalinak tumawar?)",
                "Where Are You Going, Marites? (Papanam ngay, Marites?)",
                "I'll Go To La Union (Apanak ijay La Union)",
                "Have You Eaten? What Did You Have? (Nagankan? Ania ti ninidam?)",
                "Yes, I Have Eaten, I Had Fried Tilapia (Wen, nanganakon, nagsidaak ti prinito atilapia)",
                "Do You Want To Come With Us? (Kayat mo sumurot kadakami?)",
                "Sure, I'll Go Eat With You (Sige, apanak makipangan)",
                "Your Place Is Beautiful (Nagpintas ditoy ayan yo)",
                "You Come Back Here, Okay? (Umay kan to manen ditoy, wen?)"
        };

        TextPagerAdapter pagerAdapter = new TextPagerAdapter(this, texts);
        viewPager.setAdapter(pagerAdapter);

        handler = new Handler(Looper.getMainLooper());
        startAutoSlide();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        ImageView municipalImage = findViewById(R.id.municipalImage);
        municipalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, municipal.class);
                startActivity(intent);
            }
        });

        ImageView churchImage = findViewById(R.id.churchImage);
        churchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, church.class);
                startActivity(intent);
            }
        });

        ImageView basiImage = findViewById(R.id.basiImage);
        basiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, basi.class);
                startActivity(intent);
            }
        });

        ImageView pissbuk = findViewById(R.id.pisbuuk);
        pissbuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb:https://www.facebook.com/NaguilianLU"));
                    startActivity(intent);
                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/NaguilianLU"));
                    startActivity(intent);
                }
            }
        });

        Button navigationButton = findViewById(R.id.navigationButton);
        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, touristCategory.class);
                startActivity(intent);
            }
        });

        Button marketButton = findViewById(R.id.marketingButton);
        marketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, marketing.class);
                startActivity(intent);
            }
        });
        /*
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, HomePage.class);
                startActivity(intent);
            }
        });
        */
    }

    private void startAutoSlide() {
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == viewPager.getAdapter().getCount()) {
                    currentPage = 0; // Reset to the first page if it reaches the end
                }
                viewPager.setCurrentItem(currentPage++, true); // Set the next page with a smooth scroll
            }
        };

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(update, DELAY_MS);
            }
        }, DELAY_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any pending posts of the runnable when the activity is destroyed
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        List<LatLng> naguilianBoundary = Arrays.asList(
                new LatLng(16.604023, 120.411477),
                new LatLng(16.582940, 120.400663),
                new LatLng(16.561218, 120.396477),
                new LatLng(16.559281, 120.379155),
                new LatLng(16.522750, 120.377134),
                new LatLng(16.468217, 120.389548),
                new LatLng(16.470155, 120.403406),
                new LatLng(16.470571, 120.412788),
                new LatLng(16.463788, 120.419140),
                new LatLng(16.464757, 120.424336),
                new LatLng(16.456312, 120.433863),
                new LatLng(16.454512, 120.438627),
                new LatLng(16.448144, 120.442091),
                new LatLng(16.448006, 120.449742),
                new LatLng(16.455343, 120.458403),
                new LatLng(16.462542, 120.452918),
                new LatLng(16.472924, 120.453928),
                new LatLng(16.478876, 120.453784),
                new LatLng(16.490226, 120.446566),
                new LatLng(16.510156, 120.442236),
                new LatLng(16.531053, 120.448443),
                new LatLng(16.543646, 120.446999),
                new LatLng(16.563847, 120.459702),
                new LatLng(16.569105, 120.463599),
                new LatLng(16.577544, 120.459991),
                new LatLng(16.581418, 120.451763),
                new LatLng(16.588474, 120.443535),
                new LatLng(16.588612, 120.431987),
                // ... add as many coordinates as needed to define the entire border
                new LatLng(16.604023, 120.411477) // Closing the polygon
        );

        // Adding a polygon to the Google Map
        googleMap.addPolygon(new PolygonOptions()
                .addAll(naguilianBoundary)
                .strokeColor(Color.RED) // Set the border color of the polygon
                .fillColor(Color.argb(50, 255, 0, 0))); // Set the fill color of the polygon (semi-transparent red)

        // Add markers for key locations
        LatLng municipal = new LatLng(16.5316801, 120.3943205);
        LatLng basi = new LatLng(16.53049, 120.39159);
        LatLng camplt = new LatLng(16.525417, 120.390778);
        LatLng church = new LatLng(16.53019, 120.39183);
        LatLng daughters = new LatLng(16.510278, 120.404583);
        LatLng gabaldon = new LatLng(16.532472, 120.391361);
        LatLng ivys = new LatLng(16.487861, 120.409194);
        LatLng mangkaeng = new LatLng(16.561278, 120.442250);
        LatLng minzi = new LatLng(16.583056, 120.418333);
        LatLng rapids = new LatLng(16.546111, 120.385833);
        LatLng sangbay = new LatLng(16.559071, 120.423452);
        LatLng terace = new LatLng(16.569083, 120.444806);
        LatLng tomb = new LatLng(16.530944, 120.394556);
        LatLng tudinga = new LatLng(16.5519, 120.4181);

        googleMap.addMarker(new MarkerOptions().position(municipal).title("Municipal of Naguilian La, Union"));
        googleMap.addMarker(new MarkerOptions().position(basi).title("Naguilian Basi"));
        googleMap.addMarker(new MarkerOptions().position(camplt).title("Camp Lt. Col. Jose M. Laberinto"));
        googleMap.addMarker(new MarkerOptions().position(church).title("Saint Augustine of Parish Church"));
        googleMap.addMarker(new MarkerOptions().position(daughters).title("Daughters Of Mary Consolatrix Monastery"));
        googleMap.addMarker(new MarkerOptions().position(gabaldon).title("Gabaldon Building (Dr. Hermogenes F. Belen Elementary School"));
        googleMap.addMarker(new MarkerOptions().position(ivys).title("Ivy's Garden Amore"));
        googleMap.addMarker(new MarkerOptions().position(mangkaeng).title("Mangkaeng Memorial Site"));
        googleMap.addMarker(new MarkerOptions().position(minzi).title("Minzi Falls"));
        googleMap.addMarker(new MarkerOptions().position(rapids).title("Baraoas Sur Rapids"));
        googleMap.addMarker(new MarkerOptions().position(sangbay).title("Sangbay Falls"));
        googleMap.addMarker(new MarkerOptions().position(terace).title("San Antonio Mini Rice Terraces"));
        googleMap.addMarker(new MarkerOptions().position(tomb).title("Tomb Of The Unknown Soldiers"));
        googleMap.addMarker(new MarkerOptions().position(tudinga).title("Tuddingan Falls"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(municipal, 11));
    }

}