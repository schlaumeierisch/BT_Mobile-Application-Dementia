package at.fhv.mme.bt_dementia_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import at.fhv.mme.bt_dementia_app.model.Activity
import at.fhv.mme.bt_dementia_app.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    application: Application
) : AndroidViewModel(application) {
    val date = MutableLiveData(LocalDate.now())
    val activities: LiveData<List<Activity>> = Transformations.switchMap(date) { date ->
        activityRepository.getAllActivitiesByDate(date)
    }

    val addActivityResult = MutableLiveData<AddActivityResult>()
    val updateActivityResult = MutableLiveData<UpdateActivityResult>()
    val deleteActivityResult = MutableLiveData<DeleteActivityResult>()

    fun setNewDate(newDate: LocalDate) {
        activityRepository.getAllActivitiesByDate(newDate)
        date.value = newDate
    }

    fun addActivity(activity: Activity) {
        viewModelScope.launch {
            try {
                activityRepository.addActivity(activity)
                addActivityResult.postValue(AddActivityResult.Success)
            } catch (e: Exception) {
                addActivityResult.postValue(
                    AddActivityResult.Error("Error while adding activity: ${e.message}")
                )
            }
        }
    }

    fun deleteActivity(activity: Activity) {
        viewModelScope.launch {
            try {
                activityRepository.deleteActivity(activity)
                deleteActivityResult.postValue(DeleteActivityResult.Success)
            } catch (e: Exception) {
                deleteActivityResult.postValue(
                    DeleteActivityResult.Error("Error while deleting activity: ${e.message}")
                )
            }
        }
    }

    fun setActivityDone(activity: Activity) {
        viewModelScope.launch {
            try {
                val updatedActivity = activity.copy(isDone = true)
                activityRepository.updateActivity(updatedActivity)
                updateActivityResult.postValue(UpdateActivityResult.Success)
            } catch (e: Exception) {
                updateActivityResult.postValue(
                    UpdateActivityResult.Error("Error while updating activity: ${e.message}")
                )
            }
        }
    }
}