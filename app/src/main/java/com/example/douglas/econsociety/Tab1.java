package com.example.douglas.econsociety;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Tab1 extends Fragment {

    private RecyclerView mRecycleVIew;
   // private RecyclerView.Adapter mAdapter;
   private Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Test");



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1, container, false);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.constraintLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                       /* LOFE.add("Jordan Williams","620103582"," 10%");
                        LOFE.add("Delano Francis","620103582"," 8%");*/
                        mRecycleVIew = rootView.findViewById(R.id.recycleView);
                        mRecycleVIew.setHasFixedSize(true);
                        mlayoutManager = new LinearLayoutManager(getContext());
                        mAdapter = new Adapter(LOFE.showList());
                        mRecycleVIew.setLayoutManager(mlayoutManager);
                        mRecycleVIew.setAdapter(mAdapter);
                        makeAdapter();
                        mAdapter.setOnItemClickListener(new Adapter.onItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                                LOFE.showList().remove(position).setName("lanai");
                                mAdapter.notifyItemRemoved(position);



                            }
                        });
                        //test();
                        //newly added
                       // LOFE.add("csdc","scdscd","csdcd");
                        mAdapter.notifyDataSetChanged();


                    }
                },3000);
            }
        });


        ArrayList<Member> memberList = new ArrayList<>();
       /* LOFE.add("Jordan Williams","620103582"," 10%");
        LOFE.add("Delano Francis","620103582"," 8%");*/
        memberList.add(new Member("Garfield Grant","620103582"," 7%"));
        memberList.add(new Member("Horaine McCalla","620103582"," 6%"));
        memberList.add(new Member("Derwent Johnson","620103582"," 5%"));
        memberList.add(new Member("Daniel Battick","620103582"," 4%"));

        if (getArguments() != null) {
            String[] text = getArguments().getStringArray("data");
            memberList.add(new Member(text[0],text[1],text[2]));

        }







        return rootView;
    }
    public void makeAdapter(){
        LOFE.createDatabase();
        LOFE.addRecord(getContext());



    }

}
