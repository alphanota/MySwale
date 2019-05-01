package com.alphanota.myswale.db.dao;

import com.alphanota.myswale.model.Swale;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SwaleDao {
    @Insert
    void insert(Swale swale);

    @Query("DELETE FROM swales")
    void deleteAll();

    @Query("SELECT * from swales")
    LiveData<List<Swale>> getAllWords();

}
