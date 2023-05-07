package at.fhv.mme.bt_dementia_app.repository

import androidx.lifecycle.LiveData
import at.fhv.mme.bt_dementia_app.database.dao.ContactDao
import at.fhv.mme.bt_dementia_app.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {
    fun getAllContacts(): LiveData<List<Contact>> = contactDao.getAllContacts()

    suspend fun addContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.addContact(contact)
        }
    }

    suspend fun deleteContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.deleteContact(contact)
        }
    }
}