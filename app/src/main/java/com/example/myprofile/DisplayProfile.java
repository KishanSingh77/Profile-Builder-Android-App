package com.example.myprofile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayProfile extends AppCompatActivity {

    Button edit ;
    TextView name;
    TextView textView_gender;
    ImageView imageView_profileImage ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        setTitle("Display My Profile");


        final UserProfile user  = (UserProfile) getIntent().getExtras().getSerializable(MainActivity.USER);

        name = findViewById(R.id.textView_name);
        textView_gender = findViewById(R.id.textView4_gender);
        imageView_profileImage = findViewById(R.id.imageView_profileImage);






        name.setText(user.firstName + " " +  user.lastName);
        textView_gender.setText(user.gender);

        if(user.gender .equals("Male")){
            imageView_profileImage.setImageResource(R.drawable.male);

        }

        else   if(user.gender .equals("Female")){
            imageView_profileImage.setImageResource(R.drawable.female);

        }





        edit = findViewById(R.id.button_Edit);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMyProfileIntent = new Intent();
                goToMyProfileIntent.putExtra(MainActivity.USER , user);
                setResult(RESULT_OK , goToMyProfileIntent);
                finish();
            }
        });
    }


}
