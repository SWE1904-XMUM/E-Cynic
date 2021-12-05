package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.adapter.RewardHistoryAdapter;
import com.example.e_cynic.entity.UserReward;

import java.util.List;

public class RewardHistoryActivity extends AppCompatActivity
{

    private ImageView backBtn;
    private RecyclerView rewardsRecyclerView;
    private RewardHistoryAdapter rewardHistoryAdapter;
    private List<UserReward> userRewardList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);

        setViewComponent();
        storeRewardDataIntoList();
        setUpRecyclerView();

        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RewardHistoryActivity.this,
                        ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void setViewComponent()
    {
        backBtn = findViewById(R.id.backBtn);
    }

    private void storeRewardDataIntoList()
    {

    }

    private void setUpRecyclerView()
    {
        rewardHistoryAdapter = new RewardHistoryAdapter(getApplicationContext(),userRewardList);
        rewardsRecyclerView.setAdapter(rewardHistoryAdapter);
        rewardsRecyclerView.setLayoutManager(new LinearLayoutManager(RewardHistoryActivity.this));
    }
}