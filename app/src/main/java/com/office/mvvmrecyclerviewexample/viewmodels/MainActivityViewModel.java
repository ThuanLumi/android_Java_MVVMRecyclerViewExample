package com.office.mvvmrecyclerviewexample.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.office.mvvmrecyclerviewexample.models.NicePlace;
import com.office.mvvmrecyclerviewexample.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
   private MutableLiveData<List<NicePlace>> mNicePlaces;
   private NicePlaceRepository mRepo;
   private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

   public void init() {
      if (mNicePlaces != null) {
         return;
      }
      mRepo = NicePlaceRepository.getInstance();
      mNicePlaces = mRepo.getNicePlaces();
   }

   public void addNewValue(final NicePlace nicePlace) {
      mIsUpdating.setValue(true);

      new AsyncTask<Void, Void, Void>() {

         @Override
         protected Void doInBackground(Void... voids) {
            try {
               Thread.sleep(3000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            return null;
         }

         @Override
         protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            List<NicePlace> currentPlaces = mNicePlaces.getValue();
            if (currentPlaces != null) {
               currentPlaces.add(nicePlace);
            }

            mNicePlaces.postValue(currentPlaces);
            mIsUpdating.postValue(false);
         }
      }.execute();
   }

   public LiveData<List<NicePlace>> getNicePlaces() {
      return mNicePlaces;
   }

   public LiveData<Boolean> getIsUpdating() {
      return mIsUpdating;
   }
}
