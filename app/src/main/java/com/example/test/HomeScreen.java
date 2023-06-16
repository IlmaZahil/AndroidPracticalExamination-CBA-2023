package com.example.test;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeScreen extends AppCompatActivity {

    String jsonResponse = "{\"res_code\":0,\"res_desc\":\"success\",\"user_data\":{\"id\":\"ID001\",\"email\":\"test@cba.lk\",\"name\":\"Test User CBA\",\"dob\":\"1971-01-01\",\"gender\":\"MALE\",\"company\":\"CBA - Dehiwala\",\"position\":\"Trainee SE\"}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button viewProfileButton = findViewById(R.id.viewProfileButton);


        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                // Creating a custom dialog layout inflater
                LayoutInflater inflater = LayoutInflater.from(HomeScreen.this);
                View dialogView = inflater.inflate(R.layout.dialog_custom_layout, null);

                // Displaying the data in TextViews
                TextView idTextView = dialogView.findViewById(R.id.idTextView);
                TextView emailTextView = dialogView.findViewById(R.id.emailTextView);
                TextView nameTextView = dialogView.findViewById(R.id.nameTextView);
                TextView dobTextView = dialogView.findViewById(R.id.dobTextView);
                TextView genderTextView = dialogView.findViewById(R.id.genderTextView);
                TextView companyTextView = dialogView.findViewById(R.id.companyTextView);
                TextView positionTextView = dialogView.findViewById(R.id.positionTextView);
                ImageView profileImageView = dialogView.findViewById(R.id.profileImageView);

                // Setting the image resource for the image view
                profileImageView.setImageResource(R.drawable.avatar);

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.show();

                try {
                    // Parsing the JSON response
                    JSONObject responseJson = new JSONObject(jsonResponse);
                    JSONObject userDataJson = responseJson.getJSONObject("user_data");

                    // Extracting the data
                    String id = userDataJson.getString("id");
                    String email = userDataJson.getString("email");
                    String name = userDataJson.getString("name");
                    String dob = userDataJson.getString("dob");
                    String gender = userDataJson.getString("gender");
                    String company = userDataJson.getString("company");
                    String position = userDataJson.getString("position");

                    //setting data to textviews
                    idTextView.setText("ID: " + id);
                    emailTextView.setText("Email: " + email);
                    nameTextView.setText("Name: " + name);
                    dobTextView.setText("Date of Birth: " + dob);
                    genderTextView.setText("Gender: " + gender);
                    companyTextView.setText("Company: " + company);
                    positionTextView.setText("Position: " + position);

                    System.out.println("Successful");
                    System.out.println("ID: " + id);
                    System.out.println("Email: " + email);
                    System.out.println("Name: " + name);
                    System.out.println("Date of Birth: " + dob);
                    System.out.println("Gender: " + gender);
                    System.out.println("Company: " + company);
                    System.out.println("Position: " + position);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
