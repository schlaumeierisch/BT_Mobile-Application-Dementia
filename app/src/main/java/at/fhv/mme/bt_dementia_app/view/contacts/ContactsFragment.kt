package at.fhv.mme.bt_dementia_app.view.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.fhv.mme.bt_dementia_app.R
import at.fhv.mme.bt_dementia_app.databinding.FragmentContactsBinding
import at.fhv.mme.bt_dementia_app.utils.DialogUtils
import at.fhv.mme.bt_dementia_app.view.adapter.ContactListAdapter
import at.fhv.mme.bt_dementia_app.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactListAdapter: ContactListAdapter

    private val viewModel: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        // observe the contact list from the ViewModel
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            // update contact list in adapter
            contactListAdapter.submitList(contacts)
        }
    }

    private fun initViews() {
        // set page title
        binding.header.tvTitle.setText(R.string.title_contacts)

        // initialize adapter & RecyclerView
        contactListAdapter = ContactListAdapter { contact ->
            DialogUtils.showConfirmationDialog(
                requireContext(),
                getString(R.string.label_confirmation_delete_title),
                getString(R.string.label_confirmation_delete_contact_text)
            ) { viewModel.deleteContact(contact) }
        }

        binding.rvContactList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = contactListAdapter
        }

        // initialize add contact button
        binding.btnAddContact.setOnClickListener {
            findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}