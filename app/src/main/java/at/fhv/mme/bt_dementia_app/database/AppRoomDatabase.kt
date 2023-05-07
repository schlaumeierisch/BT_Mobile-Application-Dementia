package at.fhv.mme.bt_dementia_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import at.fhv.mme.bt_dementia_app.database.dao.ContactDao
import at.fhv.mme.bt_dementia_app.model.Activity
import at.fhv.mme.bt_dementia_app.model.Contact
import at.fhv.mme.bt_dementia_app.model.Medication

@Database(entities = [Activity::class, Contact::class, Medication::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private const val DATABASE_NAME = "APP_DATABASE"

        @Volatile
        private var appRoomDatabaseInstance: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase? {
            if (appRoomDatabaseInstance == null) {
                synchronized(AppRoomDatabase::class.java) {
                    if (appRoomDatabaseInstance == null) {
                        appRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            AppRoomDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }

            return appRoomDatabaseInstance
        }
    }
}