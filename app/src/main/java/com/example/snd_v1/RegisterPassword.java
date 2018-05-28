package com.example.snd_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
Name: Jenny /hua
Date:
Title: Parent Profile Registration
Description:
 */
public class RegisterPassword extends AppCompatActivity {

    public String password;
    public EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordText = (EditText) findViewById(R.id.passwordText);

        configureNextButton();
    }

    private void configureNextButton() {
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(passwordCheck(passwordText.getText().toString())&&passwordText.getText().toString().length()>=8){
                    setPassword(passwordText.getText().toString());
                    startActivity(new Intent(RegisterPassword.this, RegisterBirthday.class));
                }
            }
        });
    }
    public boolean passwordCheck (String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }

            else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        passwordText.setError("Invalid password");
        return false;
    }
    private void setPassword(String p){ password = p;}

}