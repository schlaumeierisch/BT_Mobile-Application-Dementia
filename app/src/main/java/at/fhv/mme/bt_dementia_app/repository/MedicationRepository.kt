package at.fhv.mme.bt_dementia_app.repository

import androidx.lifecycle.LiveData
import at.fhv.mme.bt_dementia_app.database.dao.MedicationDao
import at.fhv.mme.bt_dementia_app.model.Medication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import javax.inject.Inject

class MedicationRepository @Inject constructor(private val medicationDao: MedicationDao) {
    fun getAllMedication(): LiveData<List<Medication>> = medicationDao.getAllMedication()

    fun getAllMedicationByDay(day: DayOfWeek): LiveData<List<Medication>> =
        medicationDao.getAllMedicationByDay(day)

    suspend fun addMedication(medication: Medication) {
        withContext(Dispatchers.IO) {
            medicationDao.addMedication(medication)
        }
    }

    suspend fun deleteMedication(medication: Medication) {
        withContext(Dispatchers.IO) {
            medicationDao.deleteMedication(medication)
        }
    }
}