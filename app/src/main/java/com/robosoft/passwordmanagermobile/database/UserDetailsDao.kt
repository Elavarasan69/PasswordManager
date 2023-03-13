package com.robosoft.passwordmanagermobile.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robosoft.passwordmanagermobile.entity.SiteDetails
import com.robosoft.passwordmanagermobile.entity.UserDetails


@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): List<UserDetails>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userData: UserDetails)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSite(siteDetails: SiteDetails)

    @Query("SELECT * FROM site_table")
    fun getAllSite(): LiveData<List<SiteDetails>>

}