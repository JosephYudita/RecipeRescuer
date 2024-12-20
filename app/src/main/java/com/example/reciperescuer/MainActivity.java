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



        // for the logo
        ImageView imageView = findViewById(R.id.animatedImage);

        // apply delay and then start the animation
        new Handler().postDelayed(() -> {
            // start the animation
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_up_and_fade);

            //  hide the ImageView once the animation finishes
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // gave error without
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
                    }, 200); // Slight delay
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // gave error without
                }
            });

            // Start the animation
            imageView.startAnimation(animation);

        }, 2000); // delay before starting the animation
    }
}
