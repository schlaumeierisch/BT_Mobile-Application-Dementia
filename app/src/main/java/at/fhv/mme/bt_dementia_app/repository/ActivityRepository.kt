package at.fhv.mme.bt_dementia_app.repository

import androidx.lifecycle.LiveData
import at.fhv.mme.bt_dementia_app.database.dao.ActivityDao
import at.fhv.mme.bt_dementia_app.model.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class ActivityRepository @Inject constructor(private val activityDao: ActivityDao) {
    fun getAllActivitiesByDate(date: LocalDate): LiveData<List<Activity>> =
        activityDao.getAllActivitiesByDate(date)

    suspend fun addActivity(activity: Activity) {
        withContext(Dispatchers.IO) {
            activityDao.addActivity(activity)
        }
    }

    suspend fun updateActivity(activity: Activity) {
        withContext(Dispatchers.IO) {
            activityDao.updateActivity(activity)
        }
    }

    suspend fun deleteActivity(activity: Activity) {
        withContext(Dispatchers.IO) {
            activityDao.deleteActivity(activity)
        }
    }
}