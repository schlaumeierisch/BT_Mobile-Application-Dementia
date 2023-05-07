package at.fhv.mme.bt_dementia_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import at.fhv.mme.bt_dementia_app.database.dao.ContactDao
import at.fhv.mme.bt_dementia_app.model.Activity
import at.fhv.mme.bt_dementia_app.model.Contact
import at.fhv.mme.bt_dementia_app.model.Medication

@Database(entities = [Activity::class, Contact::class, Medication::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}