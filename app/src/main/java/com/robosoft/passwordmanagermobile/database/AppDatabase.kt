package com.robosoft.passwordmanagermobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robosoft.passwordmanagermobile.entity.SiteDetails
import com.robosoft.passwordmanagermobile.entity.UserDetails

@Database(entities = [UserDetails ::class, SiteDetails::class],version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDetailsDao() : UserDetailsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}

