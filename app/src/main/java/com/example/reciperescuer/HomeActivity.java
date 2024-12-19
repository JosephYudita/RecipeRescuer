package com.example.reciperescuer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_recycler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Create a dataset of image resources
        //int[] dataSet = {R.drawable.banitsa, R.drawable.vegan_mozzarella, R.drawable.palak_panner, R.drawable.lentil_salad, R.drawable.buns, R.drawable.carrot_salad, R.drawable.chimi, R.drawable.chutney, R.drawable.mozarella, R.drawable.poke};

        // Create a dataset of Recipe objects
        List<Recipe> dataSet = new ArrayList<>();
        dataSet.add(new Recipe(R.drawable.banitsa, "Banitsa"));
        dataSet.add(new Recipe(R.drawable.vegan_mozzarella, "Vegan Mozzarella"));
        dataSet.add(new Recipe(R.drawable.palak_panner, "Palak Paneer"));
        dataSet.add(new Recipe(R.drawable.lentil_salad, "Lentil Salad"));
        dataSet.add(new Recipe(R.drawable.buns, "Buns"));
        dataSet.add(new Recipe(R.drawable.carrot_salad, "Carrot Salad"));
        dataSet.add(new Recipe(R.drawable.chimi, "Chimi"));
        dataSet.add(new Recipe(R.drawable.chutney, "Chutney"));
        dataSet.add(new Recipe(R.drawable.mozarella, "Mozzarella"));
        dataSet.add(new Recipe(R.drawable.poke, "Poke"));

        // Create an adapter and set it to the RecyclerView
        RecipeAdapter customAdapter = new RecipeAdapter(this, dataSet);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(customAdapter);
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
}
