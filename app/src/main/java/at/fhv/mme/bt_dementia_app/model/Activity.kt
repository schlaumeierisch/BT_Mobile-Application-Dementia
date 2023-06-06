package at.fhv.mme.bt_dementia_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "activities")
data class Activity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "date")
    val date: LocalDate,

    @ColumnInfo(name = "time")
    val time: LocalTime,

    @ColumnInfo(name = "reminder_time")
    val reminderTime: Int,

    @ColumnInfo(name = "reminder_audio_path")
    val reminderAudioPath: String,

    @ColumnInfo(name = "additional_info")
    val additionalInfo: String,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)