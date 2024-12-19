package com.example.reciperescuer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Reference the ImageView for the logo
        ImageView imageView = findViewById(R.id.animatedImage);

        // Handler to apply the initial delay and then start the animation
        new Handler().postDelayed(() -> {
            // Load and start the upward animation
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_up_and_fade);

            // Set an AnimationListener to hide the ImageView once the animation finishes
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // You can do something when the animation starts (optional)
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // Hide the ImageView after the animation ends
                    imageView.setVisibility(ImageView.GONE);

                    // Transition to the next activity after the animation
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(MainActivity.this, Login2Activity.class);
                        startActivity(intent);
                        finish();
                    }, 200); // Slight delay to ensure the transition happens after the image is hidden
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // You can do something if the animation repeats (optional)
                }
            });

            // Start the animation
            imageView.startAnimation(animation);

        }, 2000); // Initial delay before starting the animation
    }
}
