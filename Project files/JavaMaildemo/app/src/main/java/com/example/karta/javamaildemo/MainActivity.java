package com.example.karta.javamaildemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    EditText feedbackEditText;
    String message;
    String SENDING_MAIL_TO;
    String SENDING_MAIL_FROM;
    String PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Update with your string vlaues
        SENDING_MAIL_FROM = "YOUR MAIL ID";
        SENDING_MAIL_TO = "YOUR MAIL ID";
        PASSWORD= "YOUR PASSWORD";

        feedbackEditText = (EditText) findViewById(R.id.feedbackEditText);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = feedbackEditText.getText().toString();

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender(SENDING_MAIL_FROM,PASSWORD);

                            //sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/image.jpg");

                            sender.sendMail("JAVAMAIL demo",message,
                                    SENDING_MAIL_FROM,SENDING_MAIL_TO);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                }).start();
            }
        });
    }
}
