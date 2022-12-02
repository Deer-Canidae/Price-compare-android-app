package dev.projet_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Mandatory super
        super.onCreate(savedInstanceState);

        // ViewModel setup
        viewmodel = new ViewModelProvider(this).get(MyViewModel.class);

        // Content Setup
        setContentView(R.layout.activity_main);
        RecyclerView rc = findViewById(R.id.rc_main);
        rc.setLayoutManager(new LinearLayoutManager(null, RecyclerView.VERTICAL, false));
    }
}