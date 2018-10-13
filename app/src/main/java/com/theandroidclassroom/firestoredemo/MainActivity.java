package com.theandroidclassroom.firestoredemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mNameEdit;
    @BindView(R.id.age)
    TextView mAgeEdit;
    @BindView(R.id.gender)
    TextView mGenderEdit;
    @BindView(R.id.submit)
    Button mSubmitBtn;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;


    private MyRecycleAdapter adapter;
    private List<MembersPojo> mList = new ArrayList<>();
    private DatabaseReference mMembersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMembersRef = FirebaseDatabase.getInstance().getReference().child("Members");
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MembersPojo pojo = new MembersPojo();
                pojo.setName(mNameEdit.getText().toString());
                pojo.setAge(Integer.parseInt(mAgeEdit.getText().toString()));
                pojo.setGender(mGenderEdit.getText().toString());

                mMembersRef.push().setValue(pojo);

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecycleAdapter(mList,this);

        mMembersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MembersPojo pojo = dataSnapshot.getValue(MembersPojo.class);
                adapter.notifyDataSetChanged();
                if (pojo.getAge()>=18&& pojo.getGender().equals("male")){
                    mList.add(pojo);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }


}