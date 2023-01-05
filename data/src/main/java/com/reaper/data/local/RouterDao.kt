package com.reaper.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reaper.data.local.entity.RouterEntity

@Dao
interface RouterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRouter(routers: List<RouterEntity>)

    @Query("DELETE FROM routerentity WHERE bssid IN(:routers)")
    suspend fun deleteRouter(routers: List<String>)

    @Query("SELECT * FROM routerentity")
    suspend fun getAllRouters(): List<RouterEntity>

    //@Query("SELECT * FROM routerentity WHERE bssid LIKE '%' || :bssid || '%'")
}