package at.fhv.mme.bt_dementia_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import at.fhv.mme.bt_dementia_app.model.Contact
import at.fhv.mme.bt_dementia_app.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
    application: Application
) : AndroidViewModel(application) {
    val contacts = contactRepository.getAllContacts()

    val addContactResult = MutableLiveData<AddContactResult>()
    val deleteContactResult = MutableLiveData<DeleteContactResult>()

    fun addContact(name: String, relation: String, phoneNumber: String) {
        val contact = Contact(name, relation, phoneNumber)

        viewModelScope.launch {
            try {
                contactRepository.addContact(contact)
                addContactResult.postValue(AddContactResult.Success)
            } catch (e: Exception) {
                addContactResult.postValue(
                    AddContactResult.Error("Error while adding contact: ${e.message}")
                )
            }
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            try {
                contactRepository.deleteContact(contact)
                deleteContactResult.postValue(DeleteContactResult.Success)
            } catch (e: Exception) {
                deleteContactResult.postValue(
                    DeleteContactResult.Error("Error while deleting contact: ${e.message}")
                )
            }
        }
    }
}