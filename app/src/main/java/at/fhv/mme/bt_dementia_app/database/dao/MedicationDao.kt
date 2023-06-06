package at.fhv.mme.bt_dementia_app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import at.fhv.mme.bt_dementia_app.model.Medication
import java.time.DayOfWeek

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medication ORDER BY `name`")
    fun getAllMedication(): LiveData<List<Medication>>

    @Query("SELECT * FROM medication WHERE `day_of_week` = :day ORDER BY `time`")
    fun getAllMedicationByDay(day: DayOfWeek): LiveData<List<Medication>>

    @Insert
    fun addMedication(medication: Medication)

    @Delete
    fun deleteMedication(medication: Medication)
}