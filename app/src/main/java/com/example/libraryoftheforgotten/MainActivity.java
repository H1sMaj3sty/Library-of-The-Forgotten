package com.example.libraryoftheforgotten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private ArrayList<ItemData> values;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_query);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);
        values = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, values);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookSearch();
            }
        });
    }

    private void bookSearch() {
        String queryString = editText.getText().toString();
        if(!isInternetOn()) {
            Toast.makeText(this, "Not connected to the internet", Toast.LENGTH_LONG).show();
            return;
        }
        new FetchBook(this, values, itemAdapter, recyclerView).execute(queryString);
    }

    private boolean isInternetOn() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}