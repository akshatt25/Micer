package com.example.micer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bottomnav = findViewById(R.id.bottomnav);
        bottomnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        // Replace the current fragment with the HomeFragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragcont,
                                new HomeFrag()).commit();
                        return true;
                    case R.id.menu_search:
                        // Replace the current fragment with the SearchFragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragcont,
                                new search()).commit();
                        return true;
                    case R.id.menu_record:
                        // Replace the current fragment with the RecordFragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragcont,
                                new record()).commit();
                        return true;
                    case R.id.menu_library:
                        // Replace the current fragment with the LibraryFragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragcont,
                                new Library()).commit();
                        return true;
                }
                return false;
            }


        });
        if(checkPermission()==false)
        {
            requestPermission();
            return;
        }



    }void requestPermission()
    {
        if(a>32)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_MEDIA_AUDIO))
            {
                Toast.makeText(this, "Give Permission to App From Settings", Toast.LENGTH_SHORT).show();
            }

            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.READ_MEDIA_AUDIO}, 123);

            }
        }
        else
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this , android.Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Toast.makeText(this, "Give Permission to App From Settings", Toast.LENGTH_SHORT).show();
            }

            else
            {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 123);

            }

        }
    }

    int a = Build.VERSION.SDK_INT;
    boolean checkPermission()
    {
        if(a>32)
        {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_MEDIA_AUDIO);
            if(result== PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            else
                return false;
        }
        else {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(result== PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            else
                return false;
        }

    }

}