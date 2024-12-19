package com.example.reciperescuer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    public Button logInButton;
    public EditText nameInput;
    public EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Login button to mainActivity2
        logInButton= findViewById(R.id.login_button);

        // Initialize the EditText fields
        nameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Check if both fields are filled in
                getTypedInfo();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getTypedInfo() {
        // Retrieve the text from the TextInputEditText fields
        String name = nameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check for empty fields to avoid NullPointerExceptions
        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Fill in all fields to login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Welcome back "+ name + "!", Toast.LENGTH_SHORT).show();
            // Create an Intent to start the MainActivity2
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }


}