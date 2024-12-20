package com.example.reciperescuer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, locationTextView, cuisineTextView, levelTextView;
    private EditText groceryItems;

    private static final String PREFS_NAME = "UserPrefs";
    private static final String GROCERY_ITEMS_KEY = "groceryItems";

    // Calender
    CalendarView calendarView;
    EditText eventInput;
    Button saveButton;
    String selectedDate;

    private static final String FILE_NAME = "user_data.txt";

    private ImageView circularImage;
    private Button changeProfilePicButton;
    private GridView profilePicsGrid;
    private int[] profileImages = {
            R.drawable.profile_pic1, // Add your drawable resources here
            R.drawable.profile_pic2,
            R.drawable.profile_pic3
    };

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

        circularImage = findViewById(R.id.circular_image);
        changeProfilePicButton = findViewById(R.id.changeProfilePicButton);
        profilePicsGrid = findViewById(R.id.profilePicsGrid);

        // For calender
        calendarView = findViewById(R.id.calenderView);
        eventInput = findViewById(R.id.eventInput);
        saveButton = findViewById(R.id.saveButton);

        // Set default date
        selectedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Listener for date changes
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth; // Format the selected date
        });

        // Handle Save Button
        saveButton.setOnClickListener(v -> saveEventToDatabase());


        // Set profile pic adapter for GridView
        profilePicsGrid.setAdapter(new ProfilePicAdapter(this, profileImages));

        // Show GridView when button is clicked
        changeProfilePicButton.setOnClickListener(v -> {
            if (profilePicsGrid.getVisibility() == View.GONE) {
                profilePicsGrid.setVisibility(View.VISIBLE);
            } else {
                profilePicsGrid.setVisibility(View.GONE);
            }
        });

        // Handle profile picture selection
        profilePicsGrid.setOnItemClickListener((parent, view, position, id) -> {
            int selectedProfilePic = profileImages[position]; // Get the selected image resource
            circularImage.setImageResource(selectedProfilePic); // Set the profile picture

            // Save the selected profile picture in SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("profile_pic", selectedProfilePic); // Save the resource ID
            editor.apply();

            // Hide the GridView after selection
            profilePicsGrid.setVisibility(View.GONE);
        });

        // Retrieve user data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        String username = sharedPreferences.getString("username", "Username");
//        String location = sharedPreferences.getString("location", "Location");
//        String cuisine = sharedPreferences.getString("cuisine", "Cuisine");

        int profilePicResource = sharedPreferences.getInt("profile_pic", R.drawable.profile_ex);

        String savedGroceryItems = sharedPreferences.getString(GROCERY_ITEMS_KEY, "");

        groceryItems = findViewById(R.id.groceryItem1);
        groceryItems.setText(savedGroceryItems);

        circularImage.setImageResource(profilePicResource);

        nameTextView = findViewById(R.id.name);
        locationTextView = findViewById(R.id.location);
        cuisineTextView = findViewById(R.id.cuisine);
        levelTextView = findViewById(R.id.level);

        String enteredUsername = getIntent().getStringExtra("username");

        // reads user data
        File file = new File(getFilesDir(), FILE_NAME);

        if (!file.exists()) {
            Toast.makeText(this, "No user found. Please sign up first.", Toast.LENGTH_SHORT).show();
            return;
        }

        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            boolean userFound = false;

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");

                if (userData.length >= 5) {
                    String savedUsername = userData[0].trim();
                    String savedLocation = userData[2].trim();
                    String savedCuisine = userData[3].trim();
                    String savedLevel = userData[4].trim();

                    // Check if the username matches
                    if (savedUsername.equals(enteredUsername)) {
                        nameTextView.setText(savedUsername);
                        locationTextView.setText(savedLocation);
                        cuisineTextView.setText(savedCuisine);
                        levelTextView.setText(savedLevel);
                        userFound = true;
                        break;
                    }
                }
            }

            // If no matching user is found
            if (!userFound) {
                Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error reading file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Set the data to the TextViews
//        nameTextView.setText(username);
//        locationTextView.setText(location);
//        cuisineTextView.setText(cuisine);


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
            String username = getIntent().getStringExtra("username");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        } else if (id == R.id.profileOption) {
            String username = getIntent().getStringExtra("username");
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        } else if (id == R.id.pantryOption) {
            Intent intent = new Intent(this, PantryActivity.class);
            String username = getIntent().getStringExtra("username");
            intent.putExtra("username", username);
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

    private void saveEventToDatabase() {
        String eventText = eventInput.getText().toString().trim();

        if (eventText.isEmpty()) {
            // Call retrieveEvent function when the input is empty
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.retrieveEvent(selectedDate);
            return;
        }

        // Pass the data to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        boolean isInserted = dbHelper.insertEvent(selectedDate, eventText);

        if (isInserted) {
            Toast.makeText(this, "Recipe Saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to Save Recipe", Toast.LENGTH_SHORT).show();
        }
    }

}