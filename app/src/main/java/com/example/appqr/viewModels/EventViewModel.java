package com.example.appqr.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appqr.models.EventModels;
import com.example.appqr.models.skModels;
import com.example.appqr.repository.EventRepository;
import com.example.appqr.repository.skRepository;

public class EventViewModel extends ViewModel {
    private skRepository p;

    public EventViewModel(){
        p =  new skRepository();
    }

    public MutableLiveData<skModels> skModelsMutableLiveData(int idcate){
        return p.getSk(idcate);
    }
}
