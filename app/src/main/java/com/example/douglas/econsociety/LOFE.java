package com.example.douglas.econsociety;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LOFE {
    private static ArrayList<Member> list = new ArrayList<Member>();
    private static  FirebaseFirestore db = FirebaseFirestore.getInstance();



    private static CollectionReference notebookRef ;
    private  static  Context ctx;

    public static void add(String name,String idNum,String progress){
        Member toAdd = new Member(name,idNum,null,progress,null,null);

        if(isFound(toAdd,list)){
            whatFound(toAdd,list);
        }
        else{
        toAdd.storeStart(); // store start times #req 3 time keepingg
        list.add(toAdd);}
    }

    public static ArrayList<Member> showList(){
        return list;
    }

    public static void createDatabase(){
        notebookRef = db.collection("Test");
    }

    public static void addRecord(final Context context){
        ctx = context;




        if (showList().size() != 0) {


            for (final Member i: showList() ) {
                if (i.getTimeScan() == 1) {

                    // Req #4 share data
                    notebookRef.document(""+i.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                String text =documentSnapshot.get("attendance").toString();
                                Toast.makeText(context,text,Toast.LENGTH_LONG).show();
                                //int attendance = Integer.parseInt(text);
                                double val = Double.parseDouble(text);
                                int jor = (int)val;
                                //Map<String,Object> obj = new HashMap<>();
                                //obj.put("attendance",2);
                                notebookRef.document(""+i.getId()).update("attendance",jor+1);
                                notebookRef.document(""+i.getId()).update("progress",(((jor+1)/12)*100)+"%");

                            }
                            else {

                                i.storeEnd(); //req 3 time keeping time keeping
                                notebookRef.document(i.getId()).set(i)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });


                }//end of if
            }

        }
    }


    public static CollectionReference getNotebookRef() {
        return notebookRef;
    }


    public static boolean isFound(Member member, ArrayList<Member> memberList){
        for (Member i: memberList) {
            if (i.getId().equals(member.getId())){
                return true;
            }
        }
        return false;
    }

    public static void whatFound(Member member, ArrayList<Member> memberList){
        for (Member i: memberList) {
            if (i.getId().equals(member.getId())){
                i.incrementTimeScan();
            }
        }

    }
    public static void sendMeeting(Meeting meeting){
        Log.d("jordan", "inside send meetinh" );
        CollectionReference hold = notebookRef;
        Log.d("jordan", "doesnt reach" );
        Log.d("jordan", meeting.toString() );
        Log.d("jordan",""+ db.collection("Meeting") );
        Map<String, String> test = new HashMap<String, String>();
        test.put("testkey","testvalue");

        db.collection("Meeting")
                .add(meeting)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("jordan", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("jordan", "Error adding document", e);
                    }
                });

    }

    // all methods below are not yet implemented

    /**
     * stub method for sorting elements from db
     */
    public void sort(){

    }

    public Member search(){
        return new Member(null,null,null);
    }

    public String generateReport(){
        return  "";
    }
    public String getReport(){
        return  "";
    }
    public void manageRecord(){

    }




}
