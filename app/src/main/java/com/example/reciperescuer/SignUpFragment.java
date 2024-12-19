package com.example.reciperescuer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class SignUpFragment extends Fragment {

    private EditText nameInput, passwordInput, locationInput, cuisineInput;
    private Button signUpButton;
    private TextView displayText;

    private static final String PREFS_NAME = "UserPrefs";

    //private static final String FILE_NAME = "user_data.txt";

    public SignUpFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        locationInput = view.findViewById(R.id.location);
        cuisineInput = view.findViewById(R.id.cuisine);
        nameInput = view.findViewById(R.id.SignUpusername);
        passwordInput = view.findViewById(R.id.password);
        signUpButton = view.findViewById(R.id.signUp_button);
        displayText = view.findViewById(R.id.displayText);

        signUpButton.setOnClickListener(v -> {
            String username = nameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String location = locationInput.getText().toString().trim();
            String cuisine = cuisineInput.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()|| location.isEmpty() || cuisine.isEmpty()) {
                displayText.setText("Enter all field.");
                return;
            }
//            try (FileOutputStream fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_APPEND)) {
//                String userData = username + "," + password + "," + location + "," + cuisine + "\n";
//                fos.write(userData.getBytes());
//                nameInput.setText("");
//                passwordInput.setText("");
//                locationInput.setText("");
//                cuisineInput.setText("");
//                displayText.setText("User registered successfully!");
//                Toast.makeText(getActivity(), "Sign-up successful", Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                displayText.setText("Error saving file: " + e.getMessage());
//            }

            // Save user data in SharedPreferences
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("location", location);
            editor.putString("cuisine", cuisine);
            editor.apply();

// Log saved values to check if they are stored correctly
            String savedUsername = sharedPreferences.getString("username", "Not Found");
            String savedPassword = sharedPreferences.getString("password", "Not Found");

            displayText.setText("User registered successfully!");
            Toast.makeText(getActivity(), "Sign-up successful", Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //NavOptions navOptions = new NavOptions.Builder().setEnterAnim(R.anim.slide).build();

        Button firstFragButton = getView().findViewById(R.id.login_button);

        firstFragButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }
}