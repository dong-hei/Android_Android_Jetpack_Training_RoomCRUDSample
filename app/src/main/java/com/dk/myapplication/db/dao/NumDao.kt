package com.dk.myapplication.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dk.myapplication.db.entity.NumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun create(numEntity: NumEntity)

    @Query("SELECT * FROM num_table")
    fun read() : Flow<List<NumEntity>>

    @Update
    fun update(numEntity: NumEntity)

    @Delete
    fun delete(numEntity: NumEntity)

}