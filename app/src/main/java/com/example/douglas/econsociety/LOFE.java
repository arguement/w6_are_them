package com.example.douglas.econsociety;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

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
                                // Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
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


                  /*  notebookRef.document(i.getId()).set(i)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });*/
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
    public  void test(){

    }



}
