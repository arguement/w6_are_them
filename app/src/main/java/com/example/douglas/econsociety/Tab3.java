package com.example.douglas.econsociety;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Tab3 extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance(); // instantiate firestore
    private CollectionReference notebookRef = db.collection("Test"); // get the collection test from firestore database
    TextView tab3Display;
    Button button;
    ListenerRegistration listenerRegistration;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        tab3Display = rootView.findViewById(R.id.text_view_data);
        button = rootView.findViewById(R.id.load);
        // req 2  stand alone loads from firebase
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("I am here","garbage");
                notebookRef
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                String data = "";
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("object for",document.getData().get("name").toString());
                                        String documentId = document.getData().get("id").toString();
                                        String title = document.getData().get("name").toString();
                                        String description = document.getData().get("attendance").toString();

                                        data += "ID: " + documentId
                                                + "\nname: " + title + "\nattendance: " + description + "\n\n";
                                    }
                                    tab3Display.setText(data);
                                } else {
                                    Log.d("another", "Error getting documents: ", task.getException());
                                }
                            }
                        });

            }
        });


        
        return rootView;
    }

    public void load(View v) {
    }
}
