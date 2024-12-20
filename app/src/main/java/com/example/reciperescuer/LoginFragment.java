package com.example.reciperescuer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoginFragment extends Fragment {
    private EditText nameInput, passwordInput;
    private Button logInButton, signUp_button;
    private TextView displayText;

//    private static final String PREFS_NAME = "UserPrefs";
//    private static final String USERNAME_KEY = "username";
//    private static final String PASSWORD_KEY = "password";

    private static final String FILE_NAME = "user_data.txt";

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        nameInput = view.findViewById(R.id.username);
        logInButton = view.findViewById(R.id.login_button);
        displayText = view.findViewById(R.id.displayText);
        passwordInput = view.findViewById(R.id.password);
        signUp_button = view.findViewById(R.id.signUp_button);

        signUp_button.setVisibility(View.GONE);

        logInButton.setOnClickListener(v -> {
            String enteredUsername = nameInput.getText().toString().trim();  // Trim to remove spaces
            String enteredPassword = passwordInput.getText().toString().trim();

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                displayText.setText("Please enter both username and password");
                return;
            }

//            // Get SharedPreferences instance
//            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
//            String savedUsername = sharedPreferences.getString(USERNAME_KEY, null);
//            String savedPassword = sharedPreferences.getString(PASSWORD_KEY, null);

//            if (savedUsername != null && savedPassword != null && savedUsername.equals(enteredUsername) && savedPassword.equals(enteredPassword)) {
//                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent);
//            } else if (savedUsername.equals(enteredUsername) && !savedPassword.equals(enteredPassword)){
//                    displayText.setText("Invalid password");
//            } else {
//                displayText.setText("Username does not exist. Please sign up to create an account.");
//                signUp_button.setVisibility(View.VISIBLE); // Show the Sign Up button
//            }


//            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
//                displayText.setText("Please enter both username and password");
//                return;
//            }
//
            File file = new File(getActivity().getFilesDir(), FILE_NAME);
            if (!file.exists()) {
                Toast.makeText(getActivity(), "No user found. Please sign up first.", Toast.LENGTH_SHORT).show();
                return;
            }


            try (FileInputStream fis = getActivity().openFileInput(FILE_NAME);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader br = new BufferedReader(isr)) {

                String line;
                boolean loginSuccessful = false;

                // Check each line for username and password match
                while ((line = br.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length >= 2) {
                        String savedUsername = userData[0].trim();
                        String savedPassword = userData[1].trim();

                        if (savedUsername.equals(enteredUsername) && savedPassword.equals(enteredPassword)) {
                            loginSuccessful = true;
                            displayText.setText(" ");
                            break;
                        } else if (!savedUsername.equals(enteredUsername)) {
                            displayText.setText("Username does not exist. Create an account.");
                            signUp_button.setVisibility(View.VISIBLE);
                        } else if (savedUsername.equals(enteredUsername) && !savedPassword.equals(enteredPassword)) {
                            displayText.setText("Incorrect password");
                        }
                    }
                }

                if (loginSuccessful) {
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.putExtra("username", enteredUsername);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error reading file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //NavOptions navOptions = new NavOptions.Builder().setEnterAnim(R.anim.slide).build();

        signUp_button.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_SecondFragment_to_FirstFragment);
        });
    }

    private void getTypedInfo() {
        String name = nameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Fill in all fields to login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Welcome back "+ name + "!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    }
}