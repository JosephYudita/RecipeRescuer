package com.example.reciperescuer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recipeDetail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView recipeTextView = findViewById(R.id.textViewRecipe);
        ImageView imageView = findViewById(R.id.detailImageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        int recipeType = getIntent().getIntExtra("RECIPE_TYPE", 10);

        // Get the image resource ID passed from the adapter
        int imageResId = getIntent().getIntExtra("imageResId", 0);
        imageView.setImageResource(imageResId);

        String title = getIntent().getStringExtra("title");
        imageView.setImageResource(imageResId);
        if (title != null) {
            titleTextView.setText(title);
        }
        // Get the text resource ID passed from the adapter
        int textResId = getIntent().getIntExtra("textResId", 0);

        if (textResId != 0) {
            String recipeText = readTextFile(textResId);
            recipeTextView.setText(formatText(recipeText));
        } else {
            recipeTextView.setText("Recipe loading...");
        }

        //floating button
        fab.setOnClickListener(view -> {
            ScrollView scrollView = findViewById(R.id.scrollView);
            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
            Toast.makeText(RecipeDetailActivity.this, "Scrolled to top", Toast.LENGTH_SHORT).show();
        });

        if (recipeType == 10) {
            recipeTextView.setText("Seems like it is time to go grocery shopping");
            titleTextView.setText("No recipe matches the selected ingredients.");
            imageView.setImageResource(R.drawable.shop);
        } else if (recipeType == 3) {
            titleTextView.setText("Lentil Salad");
            String recipeText = readTextFile(R.raw.lentil_salad);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.lentil_salad);
        } else if (recipeType == 2) {
            titleTextView.setText("Palak Paneer");
            String recipeText = readTextFile(R.raw.palek_paneer);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.palak_panner);
        } else if (recipeType == 1) {
            titleTextView.setText("Mozzarella");
            String recipeText = readTextFile(R.raw.vegan_mozzarella);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.mozarella);
        } else if (recipeType == 0) {
            titleTextView.setText("Banitsa");
            String recipeText = readTextFile(R.raw.banitsa);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.banitsa);
        } else if (recipeType == 7) {
            titleTextView.setText("Chutney");
            String recipeText = readTextFile(R.raw.chutney);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.chutney);
        } else if (recipeType == 4) {
            titleTextView.setText("Buns");
            String recipeText = readTextFile(R.raw.buns);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.buns);
        } else if (recipeType == 9) {
            titleTextView.setText("Poke");
            String recipeText = readTextFile(R.raw.poke);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.poke);
        } else if (recipeType == 6) {
            titleTextView.setText("Chimi");
            String recipeText = readTextFile(R.raw.chimichurri);
            recipeTextView.setText(formatText(recipeText));
            imageView.setImageResource(R.drawable.chimi);
        } else {
            // Handle unexpected case
            Toast.makeText(RecipeDetailActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    //option menu
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
            Intent intent = new Intent(this, ProfileActivity.class);
            String username = getIntent().getStringExtra("username");
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

    //reads txt file in raw folder
    private String readTextFile(int resourceId) {
        InputStream inputStream = getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //format text as in txt file
    private SpannableString formatText(String text) {
        // Replace headings marked by ### **...** with bold and remove the symbols
        text = text.replaceAll("(?m)^###\\s\\*\\*(.*?)\\*\\*", "\n$1\n");

        // Replace list markers "-" with bullet points "•"
        text = text.replaceAll("(?m)^-\\s", "• ");
        // Remove the ** symbols after applying bold style
        text = text.replaceAll("\\*\\*(.*?)\\*\\*", "$1");
        SpannableString spannable = new SpannableString(text);

        // Apply bold styling to text between **...**
        Pattern boldPattern = Pattern.compile("\\*\\*(.*?)\\*\\*");
        Matcher boldMatcher = boldPattern.matcher(spannable);

        while (boldMatcher.find()) {
            spannable.setSpan(
                    new StyleSpan(Typeface.BOLD),
                    boldMatcher.start(1),
                    boldMatcher.end(1),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }



        return spannable;
    }

}