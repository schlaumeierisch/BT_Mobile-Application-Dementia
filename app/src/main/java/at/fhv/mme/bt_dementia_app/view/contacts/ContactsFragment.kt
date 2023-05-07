package at.fhv.mme.bt_dementia_app.view.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.fhv.mme.bt_dementia_app.R
import at.fhv.mme.bt_dementia_app.databinding.FragmentContactsBinding
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

        // set page title
        binding.header.tvTitle.setText(R.string.title_contacts)

        // initialize RecyclerView
        initRv()

        // observe the contact list from the ViewModel
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            // update contact list in adapter
            contactListAdapter.submitList(contacts)
        }
    }

    private fun initRv() {
        contactListAdapter = ContactListAdapter()
        binding.rvContactList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvContactList.adapter = contactListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}