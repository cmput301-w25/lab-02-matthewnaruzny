package com.mnaruzny.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Scroll List
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addButton;
    Button deleteButton;
    Button submitButton;
    EditText cityNameInput;

    View lastClickedCity;
    int lastClickedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Scroll Setup
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String [] cities = {"Edmonton", "Calgary", "Vancouver", "Moscow", "Sydney", "Toronto", "Warsaw", "Barcelona", "Prague", "Paris", "Nice"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton = findViewById(R.id.add_city_button);
        deleteButton = findViewById(R.id.delete_city_button);
        submitButton = findViewById(R.id.submit_button);
        cityNameInput = findViewById(R.id.city_name_input);
        lastClickedCity = null;
        lastClickedPosition = -1;

        // Start with new city input invisible
        submitButton.setVisibility(View.GONE);
        cityNameInput.setVisibility(View.GONE);

        // Event Listener Setup
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // City Click
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastClickedCity != null){
                    lastClickedCity.setBackgroundColor(getResources().getColor(R.color.city_unselected, null));
                }
                view.setBackgroundColor(getResources().getColor(R.color.city_selected, null)); // Set color of selected city

                lastClickedCity = view;
                lastClickedPosition = position;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() { // Delete Button
            @Override
            public void onClick(View v) {
                if (lastClickedPosition >= 0){
                    dataList.remove(lastClickedPosition);
                    cityAdapter.notifyDataSetChanged();
                    lastClickedCity.setBackgroundColor(getResources().getColor(R.color.city_unselected, null));

                    lastClickedCity = null;
                    lastClickedPosition = -1;
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() { // Add Button
            @Override
            public void onClick(View v) {
                submitButton.setVisibility(View.VISIBLE);
                cityNameInput.setVisibility(View.VISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() { // Submit Button
            @Override
            public void onClick(View v) {
                String newCity = cityNameInput.getText().toString();
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();

                submitButton.setVisibility(View.GONE);
                cityNameInput.setVisibility(View.GONE);
            }
        });



//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}