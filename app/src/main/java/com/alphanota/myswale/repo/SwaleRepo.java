package com.alphanota.myswale.repo;

import android.app.Application;

import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.NetworkBoundResource;
import com.alphanota.myswale.network.Resource;
import com.alphanota.myswale.network.api.ApiResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class SwaleRepo extends  Repo {

    public SwaleRepo(Application application) { super(application);}

    public LiveData<Resource<List<Swale>>> getSwales() {
        return new NetworkBoundResource< List<Swale>, Swale[] >(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Swale[] item) {
                swaleDao.insertAll(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Swale> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Swale>> loadFromDb() {
                return swaleDao.getAllSwales();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Swale[]>> createCall() {
                return swaleService.getAllSwales();
            }

        }.asLiveData();
    }


}
