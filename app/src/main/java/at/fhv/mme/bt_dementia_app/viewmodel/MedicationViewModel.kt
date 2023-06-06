package at.fhv.mme.bt_dementia_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import at.fhv.mme.bt_dementia_app.model.Medication
import at.fhv.mme.bt_dementia_app.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    private val medicationRepository: MedicationRepository,
    application: Application
) : AndroidViewModel(application) {
    val medication = medicationRepository.getAllMedication()

    val date = MutableLiveData(LocalDate.now())

    val addMedicationResult = MutableLiveData<AddMedicationResult>()
    val deleteMedicationResult = MutableLiveData<DeleteMedicationResult>()

    fun addMedication(medication: Medication) {
        viewModelScope.launch {
            try {
                medicationRepository.addMedication(medication)
                addMedicationResult.postValue(AddMedicationResult.Success)
            } catch (e: Exception) {
                addMedicationResult.postValue(
                    AddMedicationResult.Error("Error while adding medication: ${e.message}")
                )
            }
        }
    }

    fun deleteMedication(medication: Medication) {
        viewModelScope.launch {
            try {
                medicationRepository.deleteMedication(medication)
                deleteMedicationResult.postValue(DeleteMedicationResult.Success)
            } catch (e: Exception) {
                deleteMedicationResult.postValue(
                    DeleteMedicationResult.Error("Error while deleting medication: ${e.message}")
                )
            }
        }
    }
}