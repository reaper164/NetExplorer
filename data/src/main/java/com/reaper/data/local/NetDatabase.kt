package com.reaper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reaper.data.local.entity.RouterEntity

@Database(
    entities = [RouterEntity::class],
    version = 1
)
abstract class NetDatabase : RoomDatabase() {

    abstract val routerDao : RouterDao
}