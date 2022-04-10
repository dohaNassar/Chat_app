package com.example.chatapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MessageReceiver {

    DatabaseReference childRef;

    public MessageReceiver(DatabaseReference childRef) {
        this.childRef = childRef;
    }

    public void receiveMsg(){
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Message msg  = snapshot1.getValue(Message.class);

                    Log.d("ttt",msg.getText());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
