package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_cynic.R;
import com.example.e_cynic.adapter.RewardHistoryAdapter;
import com.example.e_cynic.db.UserRewardDatabase;
import com.example.e_cynic.entity.UserReward;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RewardHistoryActivity extends AppCompatActivity
{

    private ImageView backBtn;
    private RecyclerView rewardsRecyclerView;
    private RewardHistoryAdapter rewardHistoryAdapter;
    private List<UserReward> userRewardList = new ArrayList<>();
    private LinearLayout LL_rewardHistory;
    private LinearLayout LL_noRewardHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);

        setViewComponent();

        try
        {
            storeRewardDataIntoList();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        setUpLinearLayout();
        setUpAdapter();

        //TODO
        /*if (userRewardList != null)
        {
            LL_noRewardHistory.setVisibility(View.GONE);
            LL_rewardHistory.setVisibility(View.VISIBLE);
        }

        else
        {
            LL_noRewardHistory.setVisibility(View.VISIBLE);
            LL_rewardHistory.setVisibility(View.GONE);
        }*/

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
        rewardsRecyclerView = findViewById(R.id.rewardsRecyclerView);
    }

    private void storeRewardDataIntoList() throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        userRewardList = UserRewardDatabase.getUserRewardsByUsername("pjou");
    }

    private void setUpAdapter()
    {
        rewardHistoryAdapter = new RewardHistoryAdapter(getApplicationContext(),userRewardList);
        rewardsRecyclerView.setAdapter(rewardHistoryAdapter);
        rewardsRecyclerView.setLayoutManager(new LinearLayoutManager(RewardHistoryActivity.this));
    }

    private void setUpLinearLayout()
    {
        LL_rewardHistory = findViewById(R.id.LL_recycleHistory);
        LL_noRewardHistory = findViewById(R.id.LL_noRecycleHistory);
    }
}