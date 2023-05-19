package com.example.appqr.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appqr.models.SkDetailModel;
import com.example.appqr.repository.SkDetailRepo;

public class ShowDetailViewModels extends ViewModel {
    private SkDetailRepo skDetailRepo;
     public ShowDetailViewModels(){
            skDetailRepo =new SkDetailRepo();
     }
     public MutableLiveData<SkDetailModel> skDetailModelMutableLiveData(int id){
         return skDetailRepo.getSkDetail(id);
     }
}
