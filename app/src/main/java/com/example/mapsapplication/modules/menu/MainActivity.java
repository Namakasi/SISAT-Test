package com.example.mapsapplication.modules.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mapsapplication.R;
import com.example.mapsapplication.core.OnItemClickListener;
import com.example.mapsapplication.model.Coordenate;
import com.example.mapsapplication.modules.map.MapActivity;
import com.example.mapsapplication.util.AppConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private CoordenateAdapter adapter;
    private RecyclerView recyclerView;
    private AppCompatButton addButton;
    private AppCompatButton continueButton;
    private ArrayList<Coordenate> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupRecyclerView();
        setupListeners();
    }

    private void setupListeners() {
        addButton.setOnClickListener(view -> {
            items.add(new Coordenate());
            adapter.notifyDataSetChanged();
        });

        continueButton.setOnClickListener(view -> {
            if (isValid())
                startActivity(new Intent(MainActivity.this, MapActivity.class)
                        .putExtra(AppConstants.COORDENATE_LIST, items));
            else
                Toast.makeText(this, this.getString(R.string.text_values_invalid),
                        Toast.LENGTH_LONG).show();
        });
    }

    private boolean isValid() {
        boolean isValid = true;
        for (Coordenate coordenate : items)  isValid &= coordenate.isValid();
        return isValid;
    }


    private void setupViews() {
        recyclerView = findViewById(R.id.recycler_coordenates);
        addButton = findViewById(R.id.button_add_coordenates);
        continueButton = findViewById(R.id.button_continue);
    }

    private void setupRecyclerView() {
        adapter = new CoordenateAdapter(this, this);
        items.add(new Coordenate());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, int position) {
        adapter.removeItem(position);
    }
}