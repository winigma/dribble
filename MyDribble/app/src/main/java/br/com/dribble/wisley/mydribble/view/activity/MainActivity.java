package br.com.dribble.wisley.mydribble.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.dribble.wisley.mydribble.R;
import br.com.dribble.wisley.mydribble.util.ActivityUtils;
import br.com.dribble.wisley.mydribble.view.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new HomeFragment(), R.id.home_container);

    }
}
