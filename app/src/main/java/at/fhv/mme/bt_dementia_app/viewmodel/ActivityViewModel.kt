package at.fhv.mme.bt_dementia_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import at.fhv.mme.bt_dementia_app.model.Activity
import at.fhv.mme.bt_dementia_app.repository.ActivityRepository
import at.fhv.mme.bt_dementia_app.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val medicationRepository: MedicationRepository,
    application: Application
) : AndroidViewModel(application) {

    init {
        viewModelScope.launch {
            createActivitiesFromMedication()
        }
    }

    val date = MutableStateFlow(LocalDate.now())

    @OptIn(ExperimentalCoroutinesApi::class)
    val activities: Flow<List<Activity>> = date.flatMapLatest { date ->
        activityRepository.getAllActivitiesByDate(date)
    }

    val addActivityResult = MutableLiveData<AddActivityResult>()
    val updateActivityResult = MutableLiveData<UpdateActivityResult>()
    val deleteActivityResult = MutableLiveData<DeleteActivityResult>()

    suspend fun createActivitiesFromMedication() {
        // create activities from medication for the next 5 days
        val numOfDays = 3
        for (i in 0 until numOfDays) {
            val date = LocalDate.now().plusDays(i.toLong())
            val todaysMedication = medicationRepository.getAllMedicationByDay(date.dayOfWeek).first()

            for (medication in todaysMedication) {
                // check if medication as activity already exists
                val activity = Activity(
                    name = medication.name,
                    date = date,
                    time = medication.time,
                    reminderTime = 0,
                    reminderAudioPath = "",
                    additionalInfo = "",
                    amount = medication.amount,
                    unit = medication.unit
                )
                activityRepository.addActivity(activity)
            }
        }
    }

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