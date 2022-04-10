package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText msgEt;
    ImageButton sendBtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference parentRef;
    DatabaseReference chatRef;

    MessageSender messageSender;
    MessageReceiver messageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgEt = findViewById(R.id.msgEt);
        sendBtn = findViewById(R.id.sendBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        parentRef = firebaseDatabase.getReference();
        chatRef = parentRef.child(ProjectConstants.CHILD_REF);

        messageSender = new MessageSender(chatRef);
        messageReceiver = new MessageReceiver(chatRef);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message(
                        msgEt.getText().toString(),
                        ProjectConstants.SENDER_UID,
                        ProjectConstants.RECEIVER_UID);
                messageSender.sendMsg(message);

                messageReceiver.receiveMsg();
            }
        });


    }
}