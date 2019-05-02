package com.alphanota.myswale;
import android.content.Context;

import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.AppExecutors;
import com.alphanota.myswale.network.api.ApiResponse;
import com.alphanota.myswale.network.api.route.Route;
import com.alphanota.myswale.network.api.volley.CallBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class NetworkTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();


        CallBuilder builder = new CallBuilder(appContext, BuildConfig.API_URL);
        Route.setBuilder(builder);
        LiveData<ApiResponse<Swale[]>> login = Route.Swale.getByGeom(40.7039923,-73.8269613, 100);

        AppExecutors executors = new AppExecutors();

        executors.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                login.observeForever(new Observer<ApiResponse<Swale[]>>() {
                    @Override
                    public void onChanged(ApiResponse<Swale[]> swales) {
                        System.out.println(swales.isSuccessful());
                    }
                });
            }
        });

        assertEquals("com.alphanota.myswale", appContext.getPackageName());
    }
}
