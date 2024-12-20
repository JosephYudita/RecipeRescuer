package com.example.reciperescuer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PantryActivity extends AppCompatActivity {
    // Button to generate the recipe
    public Button generateButton;
    private CheckBox checkLentils, checkCarrot, checkMustard, checkLemon, checkSpinach, checkGinger;
    private CheckBox checkCashew, checkMiso, checkDough, checkFeta, checkJalapeno, checkMango, checkBeans;
    private CheckBox checkTofu, checkAvocado, checkRadish, checkParsley, checkChili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        // Initialize CheckBoxes
        checkLentils = findViewById(R.id.lentils);
        checkCarrot = findViewById(R.id.carrot);
        checkMustard = findViewById(R.id.mustard);
        checkLemon = findViewById(R.id.lemon);
        checkSpinach = findViewById(R.id.spinach);
        checkGinger = findViewById(R.id.ginger);
        checkCashew = findViewById(R.id.cashew);
        checkMiso = findViewById(R.id.miso);
        checkDough = findViewById(R.id.dough);
        checkFeta = findViewById(R.id.feta);
        checkJalapeno = findViewById(R.id.jalapeno);
        checkMango = findViewById(R.id.mango);
        checkBeans = findViewById(R.id.beans);
        checkTofu = findViewById(R.id.tofu);
        checkAvocado = findViewById(R.id.avocado);
        checkRadish = findViewById(R.id.radish);
        checkParsley = findViewById(R.id.parsley);
        checkChili = findViewById(R.id.chili);

        // Initialize the Generate Button
        generateButton = findViewById(R.id.generate_button);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecipe(); // Call the method to generate the recipe
            }
        });
    }

    private void getRecipe() {
        // Create an Intent to open the recipe activity
        Intent intent = new Intent(PantryActivity.this, RecipeDetailActivity.class);
        int recipeType = 11;

        // Check for single category ingredients
        if ((checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked())
                && !(checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkDough.isChecked() || checkFeta.isChecked()
                || checkJalapeno.isChecked() || checkMango.isChecked() || checkBeans.isChecked()
                || checkTofu.isChecked() || checkAvocado.isChecked() || checkRadish.isChecked()
                || checkParsley.isChecked() || checkChili.isChecked())) {
            recipeType = 3; // Lentil Salad
        }
        else if ((checkSpinach.isChecked() || checkGinger.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkCashew.isChecked() || checkMiso.isChecked() || checkDough.isChecked()
                || checkFeta.isChecked() || checkJalapeno.isChecked() || checkMango.isChecked()
                || checkBeans.isChecked() || checkTofu.isChecked() || checkAvocado.isChecked()
                || checkRadish.isChecked() || checkParsley.isChecked() || checkChili.isChecked())) {
            recipeType = 2; // Palak Paneer
        }
        else if ((checkCashew.isChecked() || checkMiso.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkDough.isChecked()
                || checkFeta.isChecked() || checkJalapeno.isChecked() || checkMango.isChecked()
                || checkBeans.isChecked() || checkTofu.isChecked() || checkAvocado.isChecked()
                || checkRadish.isChecked() || checkParsley.isChecked() || checkChili.isChecked())) {
            recipeType = 1; // Mozzarella
        }
        else if ((checkDough.isChecked() || checkFeta.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkJalapeno.isChecked() || checkMango.isChecked()
                || checkBeans.isChecked() || checkTofu.isChecked() || checkAvocado.isChecked()
                || checkRadish.isChecked() || checkParsley.isChecked() || checkChili.isChecked())) {
            recipeType = 0; // Banitsa
        }
        else if ((checkJalapeno.isChecked() || checkMango.isChecked() || checkLemon.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkDough.isChecked() || checkFeta.isChecked()
                || checkBeans.isChecked() || checkTofu.isChecked() || checkAvocado.isChecked()
                || checkRadish.isChecked() || checkParsley.isChecked() || checkChili.isChecked())) {
            recipeType = 7; // Chutney
        }
        else if ((checkBeans.isChecked() || checkTofu.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkDough.isChecked() || checkFeta.isChecked()
                || checkJalapeno.isChecked() || checkMango.isChecked() || checkLemon.isChecked()
                || checkAvocado.isChecked() || checkRadish.isChecked() || checkParsley.isChecked()
                || checkChili.isChecked())) {
            recipeType = 4; // Buns
        }
        else if ((checkAvocado.isChecked() || checkRadish.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkDough.isChecked() || checkFeta.isChecked()
                || checkJalapeno.isChecked() || checkMango.isChecked() || checkBeans.isChecked()
                || checkTofu.isChecked() || checkParsley.isChecked() || checkChili.isChecked())) {
            recipeType = 9; // Poke
        }
        else if ((checkParsley.isChecked() || checkChili.isChecked())
                && !(checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkDough.isChecked() || checkFeta.isChecked()
                || checkJalapeno.isChecked() || checkMango.isChecked() || checkBeans.isChecked()
                || checkTofu.isChecked() || checkAvocado.isChecked() || checkRadish.isChecked())) {
            recipeType = 6; // Chimi
        }
        else if (!(checkParsley.isChecked() || checkChili.isChecked() || checkLentils.isChecked() || checkCarrot.isChecked() || checkMustard.isChecked()
                || checkSpinach.isChecked() || checkGinger.isChecked() || checkCashew.isChecked()
                || checkMiso.isChecked() || checkDough.isChecked() || checkFeta.isChecked()
                || checkJalapeno.isChecked() || checkMango.isChecked() || checkBeans.isChecked()
                || checkTofu.isChecked() || checkAvocado.isChecked() || checkRadish.isChecked())) {
            recipeType = 11; // Nothing selected
        }
        else {
            recipeType = 10; // No possible recipe
        }

        // If recipeType is set to 10, no valid recipe was found
        if (recipeType == 0) {
            Toast.makeText(PantryActivity.this, "Please select ingredients for a recipe!", Toast.LENGTH_SHORT).show();
        } else {
            // Check which recipe was selected
            Toast.makeText(PantryActivity.this, "Recipe: " + recipeType, Toast.LENGTH_SHORT).show();

            // Pass the recipe type since it's needed for the next activity
            intent.putExtra("RECIPE_TYPE", recipeType);
            String username = getIntent().getStringExtra("username");
            intent.putExtra("username", username);
            startActivity(intent);
        }

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



}