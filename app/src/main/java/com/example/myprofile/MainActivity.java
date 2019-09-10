package com.example.myprofile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final int REQ_CODE = 100 ;
    public static final String USER = "user" ;



    UserProfile user;
    ImageView imageButton_profileImage ;
    Button save_button ;
    EditText editText_firstName , editText_lastName;
    RadioGroup radioGroup_gender ;
    RadioButton radioButton_gender,radioButton_male , radioButton_female ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        save_button = findViewById(R.id.button_Edit);
        editText_firstName = findViewById(R.id.editText2);
        editText_lastName = findViewById(R.id.editText3);
        radioGroup_gender = findViewById(R.id.radioGroup_Gender);
        imageButton_profileImage =findViewById(R.id.imageButton_genderImage);
        save_button = findViewById(R.id.button_Edit);
        radioButton_male  = findViewById(R.id.radioButton_male);
        radioButton_female  = findViewById(R.id.radioButton_female);



        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                radioButton_gender = findViewById(i);
                Log.d("checked is " ,radioButton_gender.getText().toString() );

                if(radioButton_gender.getText().toString() .equals("Male")){
                    imageButton_profileImage.setImageResource(R.drawable.male);

                }

                else   if(radioButton_gender.getText().toString() .equals("Female")){
                    imageButton_profileImage.setImageResource(R.drawable.female);

                }

                radioButton_male.setError(null);
            }
        });





        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = editText_firstName.getText().toString();
                String lastName = editText_lastName.getText().toString();


                //validations

                if(firstName==null || firstName.equals("") ||firstName.isEmpty()
                        ||lastName==null || lastName.equals("") ||lastName.isEmpty()
                        || (!radioButton_male.isChecked()  && !radioButton_female.isChecked())

                )
                {
                    if(firstName==null || firstName.equals("") ||firstName.isEmpty() )
                    {
                        editText_firstName.setError("Enter a valid firstName");

                    }

                    if(lastName==null || lastName.equals("") ||lastName.isEmpty() )
                    {
                        editText_lastName.setError("Enter a valid lastName");

                    }

                    if(!radioButton_male.isChecked()  && !radioButton_female.isChecked())
                    { radioButton_male.setError("check one gender");
                        Toast.makeText(getApplicationContext() , "Choose a gender!" , Toast.LENGTH_LONG).show();

                    }
                    return;
                }

                String gender =   radioButton_gender .getText().toString();
                user = new UserProfile(firstName , lastName , gender);
                Log.d("user", user+"");

                Intent displayProfileIntent = new Intent(MainActivity.this , DisplayProfile.class);
                displayProfileIntent.putExtra(USER , user);
                startActivityForResult (displayProfileIntent , REQ_CODE  );

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK){
                Log.d("START_ACTIVITY_RESULT" , "Success");
                UserProfile returnedUser = (UserProfile)data.getExtras().getSerializable(USER);
                Log.d("START_ACTIVITY_RESULT" , user+ "");
                Log.d("START_ACTIVITY_RESULT" , returnedUser+"");
                if(returnedUser.equals(user)) {
                    Log.d("Value received is " , returnedUser+"" );
                    Toast.makeText(getApplicationContext() , "Success!" , Toast.LENGTH_LONG).show();
                }
            }
            else if(resultCode == RESULT_CANCELED){
                Log.d("START_ACTIVITY_RESULT" , "Cancelled");
            }
        }
    }
}
