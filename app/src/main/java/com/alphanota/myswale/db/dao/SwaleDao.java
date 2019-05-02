package com.alphanota.myswale.db.dao;

import com.alphanota.myswale.model.Swale;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SwaleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Swale swale);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Swale... swales);

    @Query("SELECT * from swales")
    LiveData<List<Swale>> getAllSwales();

    @Query("SELECT asset_id from swales")
    LiveData<List<Long>> getAllSwaleIds();

    @Query("DELETE FROM swales")
    void deleteAll();
}
