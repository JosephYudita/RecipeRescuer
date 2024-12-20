package com.example.reciperescuer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private String username;
    private List<Recipe> recipes;
    private Context context;

    // Constructor for the Adapter
    public RecipeAdapter(Context context, List<Recipe> dataSet, String username) {
        this.context = context;
        this.recipes = dataSet;
        this.username = username;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView titleText;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.recyclerImageView);
            titleText = view.findViewById(R.id.recyclertitleText);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTitleText() {
            return titleText;
        }
    }

    // Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_image_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Bind data to the view
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Recipe recipe = recipes.get(position);
        viewHolder.imageView.setImageResource(recipe.getImageResId());
        viewHolder.titleText.setText(recipe.getTitle());

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("imageResId", recipe.getImageResId());
            intent.putExtra("title", recipe.getTitle());

            // Pass the text file resource based on the recipe title
            int textResId = getTextFileForRecipe(recipe.getTitle());
            intent.putExtra("textResId", textResId);
            intent.putExtra("RECIPE_TYPE", position );

            intent.putExtra("username", username);

            context.startActivity(intent);
        });
    }

    private int getTextFileForRecipe(String title) {
        switch (title) {
            case "Banitsa":
                return R.raw.banitsa;
            case "Vegan Mozzarella":
                return R.raw.vegan_mozzarella;
            case "Palak Paneer":
                return R.raw.palek_paneer;
            case "Lentil Salad":
                return R.raw.lentil_salad;
            case "Buns":
                return R.raw.buns;
            case "Carrot Salad":
                return 0;
            case "Chimi":
                return R.raw.chimichurri;
            case "Chutney":
                return R.raw.chutney;
            case "Mozzarella":
                return 0;
            case "Poke":
                return R.raw.poke;
            default:
                return 0;
        }
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }

}
