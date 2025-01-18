package com.example.tattannaguilian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class basi extends AppCompatActivity implements GoogleMap.OnMapLoadedCallback, OnMapReadyCallback {

    private GoogleMap gMap;
    private ViewPager imagePager;
    private LinearLayout dotsLayout;
    private int[] imageIds = {R.drawable.basin, R.drawable.basi, R.drawable.basim,
            R.drawable.basit}; // Add your image resources here
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        Button backbutton = findViewById(R.id.backButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(basi.this, HomePage.class);
                startActivity(intent);
            }
        });

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(basi.this, HomePage.class);
                startActivity(intent);
            }
        });

        Button marketButton = findViewById(R.id.marketingButton);
        marketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(basi.this, touristCategory.class);
                startActivity(intent);
            }
        });

        imagePager = findViewById(R.id.imagePager);
        dotsLayout = findViewById(R.id.dotsLayout);

        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageIds);
        imagePager.setAdapter(adapter);

        addDotsIndicator();
        imagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                updateDotsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);

        leftButton.setOnClickListener(v -> {
            int currentItem = imagePager.getCurrentItem();
            if (currentItem > 0) {
                imagePager.setCurrentItem(currentItem - 1);
            }
        });

        rightButton.setOnClickListener(v -> {
            int currentItem = imagePager.getCurrentItem();
            if (currentItem < imageIds.length - 1) {
                imagePager.setCurrentItem(currentItem + 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // This will return to the previous activity in the back stack
    }

    private void addDotsIndicator() {
        dots = new ImageView[imageIds.length];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.dot_inactive); // Replace with your dot inactive drawable
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            dotsLayout.addView(dots[i], params);
        }
        if (dots.length > 0) {
            dots[0].setImageResource(R.drawable.dot_active); // Replace with your dot active drawable
        }
    }

    private void updateDotsIndicator(int position) {
        for (ImageView dot : dots) {
            dot.setImageResource(R.drawable.dot_inactive); // Replace with your dot inactive drawable
        }
        if (dots.length > 0) {
            dots[position].setImageResource(R.drawable.dot_active); // Replace with your dot active drawable
        }
    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location = new LatLng(16.53049, 120.39159);
        googleMap.addMarker(new MarkerOptions().position(location).title("Basi Festival"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
}