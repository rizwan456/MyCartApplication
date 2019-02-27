package com.example.mycartapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mycartapplication.databinding.MainActivityBinding;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {

    MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUp();

    }

    private void setUp() {
        getSupportActionBar().setTitle("Cart");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new CartFragment()).commit();
    }
}
