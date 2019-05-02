package com.alphanota.myswale.viewmodel;

import android.app.Application;

import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.Resource;
import com.alphanota.myswale.repo.SwaleRepo;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MapViewModel extends AndroidViewModel {

    SwaleRepo repo;

    public MapViewModel(Application application) {
        super(application);

        repo = new SwaleRepo(application);
    }

    public LiveData<Resource<List<Swale>>> getAllSwales(){
        return repo.getSwales();
    }
}
