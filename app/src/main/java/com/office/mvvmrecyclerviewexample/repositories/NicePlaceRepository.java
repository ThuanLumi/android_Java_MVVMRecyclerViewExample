package com.office.mvvmrecyclerviewexample.repositories;

import androidx.lifecycle.MutableLiveData;

import com.office.mvvmrecyclerviewexample.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlaceRepository {
   private static NicePlaceRepository INSTANCE;
   private ArrayList<NicePlace> dataSet = new ArrayList<>();

   public static NicePlaceRepository getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new NicePlaceRepository();
      }
      return INSTANCE;
   }

   // Mimic getting data from webservice
   public MutableLiveData<List<NicePlace>> getNicePlaces() {
      setNicePlaces();

      MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
      data.setValue(dataSet);

      return data;
   }

   private void setNicePlaces() {
      dataSet.add(
              new NicePlace("https://i.redd.it/i8o77dqn80g01.jpg",
                      "Havasu Falls")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/tpsnoz5bzo501.jpg",
                      "Trondheim")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/qn7f9oqu7o501.jpg",
                      "Portugal")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/j6myfqglup501.jpg",
                      "Rocky Mountain National Park")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/0h2gm1ix6p501.jpg",
                      "Tulum")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/k98uzl68eh501.jpg",
                      "Mahahul")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/y1dnwnsf8ab81.jpg",
                      "Frozen Lake")
      );
      dataSet.add(
              new NicePlace("https://i.redd.it/obx4zydshg601.jpg",
                      "Australia")
      );
   }
}
