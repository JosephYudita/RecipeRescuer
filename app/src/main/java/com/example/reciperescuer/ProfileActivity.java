package com.example.reciperescuer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, locationTextView, cuisineTextView;
    private EditText groceryItems;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String GROCERY_ITEMS_KEY = "groceryItems";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameTextView = findViewById(R.id.name);
        locationTextView = findViewById(R.id.location);
        cuisineTextView = findViewById(R.id.cuisine);
        groceryItems = findViewById(R.id.groceryItems);

        // Retrieve user data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Username");
        String location = sharedPreferences.getString("location", "Location");
        String cuisine = sharedPreferences.getString("cuisine", "Cuisine");


        String savedGroceryItems = sharedPreferences.getString(GROCERY_ITEMS_KEY, "");

        // Set the data to the TextViews
        nameTextView.setText(username);
        locationTextView.setText(location);
        cuisineTextView.setText(cuisine);

        groceryItems.setText(savedGroceryItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id== R.id.homeOption) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.profileOption) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.pantryOption) {
            Intent intent = new Intent(this, PantryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the grocery items to SharedPreferences when the user leaves the activity
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GROCERY_ITEMS_KEY, groceryItems.getText().toString());
        editor.apply();
    }
}