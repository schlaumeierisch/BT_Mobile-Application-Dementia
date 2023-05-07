package at.fhv.mme.bt_dementia_app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import at.fhv.mme.bt_dementia_app.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY `name`")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert
    fun addContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}