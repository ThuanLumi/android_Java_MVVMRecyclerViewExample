package com.office.mvvmrecyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.office.mvvmrecyclerviewexample.adapters.RecyclerAdapter;
import com.office.mvvmrecyclerviewexample.models.NicePlace;
import com.office.mvvmrecyclerviewexample.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private FloatingActionButton mFab;
   private RecyclerView mRecyclerView;
   private RecyclerAdapter mAdapter;
   private ProgressBar mProgressBar;

   private MainActivityViewModel mMainActivityViewModel;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mFab = findViewById(R.id.fab);
      mRecyclerView = findViewById(R.id.recycler_view);
      mProgressBar = findViewById(R.id.progress_bar);

      mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

      mMainActivityViewModel.init();

      mMainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
         @Override
         public void onChanged(List<NicePlace> nicePlaces) {
            mAdapter.notifyDataSetChanged();
         }
      });

      mMainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
         @Override
         public void onChanged(Boolean aBoolean) {
            if (aBoolean) {
               showProgressBar();
            } else {
               hideProgressBar();
               mRecyclerView.smoothScrollToPosition(mMainActivityViewModel.getNicePlaces().getValue().size() - 1);
            }
         }
      });

      mFab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            mMainActivityViewModel.addNewValue(
                    new NicePlace("https://i.redd.it/bjtod7r474q41.jpg", "Mount Fuji")
            );
         }
      });

      initRecyclerView();
   }

   private void initRecyclerView() {
      mAdapter = new RecyclerAdapter(this, mMainActivityViewModel.getNicePlaces().getValue());
      RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
      mRecyclerView.setLayoutManager(linearLayoutManager);
      mRecyclerView.setAdapter(mAdapter);
   }

   private void showProgressBar() {
      mProgressBar.setVisibility(View.VISIBLE);
   }

   private void hideProgressBar() {
      mProgressBar.setVisibility(View.GONE);
   }
}