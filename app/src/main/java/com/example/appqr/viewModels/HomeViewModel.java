package com.example.appqr.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appqr.models.EventModels;
import com.example.appqr.models.skModels;
import com.example.appqr.repository.EventRepository;
import com.example.appqr.repository.skRepository;

public class HomeViewModel extends ViewModel {
    private EventRepository eventRepository;
    private skRepository skRepository;
    public HomeViewModel(){
        eventRepository = new EventRepository();
        skRepository =  new skRepository();
    }
    public MutableLiveData<EventModels> eventModelsMutableLiveData(){
        return eventRepository.getEvent();
    }

    public MutableLiveData<skModels> skModelsMutableLiveData(int idcate){
        return skRepository.getSk(idcate);
    }
}
