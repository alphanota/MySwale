package com.alphanota.myswale.repo;

import android.app.Application;

import com.alphanota.myswale.SwaleRoomDatabase;
import com.alphanota.myswale.db.dao.SwaleDao;
import com.alphanota.myswale.network.AppExecutors;
import com.alphanota.myswale.network.api.SwaleApi;
import com.alphanota.myswale.network.api.SwaleApiService;

public class Repo {
    protected final SwaleDao swaleDao;
    protected final SwaleApi swaleService;
    protected final AppExecutors appExecutors;

    public Repo(Application application) {
        SwaleRoomDatabase db = SwaleRoomDatabase.getDatabase(application);
        swaleDao = db.swaleDao();
        appExecutors = new AppExecutors();
        this.swaleService = new SwaleApiService(application);
    }
}
