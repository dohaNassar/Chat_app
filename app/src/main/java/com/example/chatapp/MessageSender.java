package com.example.chatapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class MessageSender {
    DatabaseReference childRef;

    public MessageSender(DatabaseReference childRef) {
        this.childRef = childRef;
    }

    public boolean sendMsg(Message message){
        boolean state = false;

        Task task = childRef.push().setValue(message);
        if (task.isSuccessful()){
            state = true;
        }
        return state;
    }
}
