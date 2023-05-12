package at.fhv.mme.bt_dementia_app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import at.fhv.mme.bt_dementia_app.model.Activity
import java.time.LocalDate

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activities WHERE `date` = :date ORDER BY `time`")
    fun getAllActivitiesByDate(date: LocalDate): LiveData<List<Activity>>

    @Insert
    fun addActivity(activity: Activity)

    @Update
    fun updateActivity(activity: Activity)

    @Delete
    fun deleteActivity(activity: Activity)
}