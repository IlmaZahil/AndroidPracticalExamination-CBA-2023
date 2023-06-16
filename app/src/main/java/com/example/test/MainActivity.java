package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    String jsonResponse = "{\"res_code\":0,\"res_desc\":\"success\",\"user_data\":{\"id\":\"ID001\",\"email\":\"test@cba.lk\",\"name\":\"Test User CBA\",\"dob\":\"1971-01-01\",\"gender\":\"MALE\",\"company\":\"CBA - Dehiwala\",\"position\":\"Trainee SE\"}}";


    private TextInputEditText editTextUsername;             //userName TextField
    private TextInputEditText editTextPassword;             //Password TextField
    private TextView errorUsername;                         //errorUserName TextView
    private TextView errorPassword;                         //errorPassword TextText
    private Button buttonLogin;                             //Login Button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        errorPassword = findViewById(R.id.errorPassword);
        errorUsername = findViewById(R.id.errorUserName);
        buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject userDescJson = new JSONObject(jsonResponse);

                    // Extracting the data
                    String resDesc = userDescJson.getString("res_desc");

                    System.out.println("res_desc: "  + resDesc);

                    String username = editTextUsername.getText().toString();            //getting username from TextField
                    String password = editTextPassword.getText().toString();            //getting password from TextField
                    System.out.println("Login Successful");
                    System.out.println("ID: " + username);
                    System.out.println("ID: " + password);

                    String expectedUsername = "test";
                    String expectedPassword = "Test123";

                    //navigating to HomeScreen
                    if (username.equals(expectedUsername) && password.equals(expectedPassword) && resDesc.equals("success")) {
                        errorUsername.setVisibility(View.GONE);
                        errorPassword.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                        startActivity(intent);
                    }
                    else if (!username.equals(expectedUsername)){
                        Toast.makeText(MainActivity.this, "Incorrect Username", Toast.LENGTH_SHORT).show();
                        errorUsername.setVisibility(View.VISIBLE);
                    }

                    else {
                        Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        errorPassword.setVisibility(View.VISIBLE);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}