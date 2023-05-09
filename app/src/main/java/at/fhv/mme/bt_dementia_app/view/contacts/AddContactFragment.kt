package at.fhv.mme.bt_dementia_app.view.contacts

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import at.fhv.mme.bt_dementia_app.R
import at.fhv.mme.bt_dementia_app.databinding.FragmentAddContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactFragment : Fragment() {

    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        // set page title
        binding.header.tvTitle.setText(R.string.title_add_contact)

        // initialize back button
        binding.header.btnBack.setOnClickListener {
            showConfirmationDialog()
        }

        // initialize upload image button
        binding.btnUploadImage.setOnClickListener {
            // TODO
        }

        // initialize next step button
        binding.btnNextStep.setOnClickListener {
            showStepSummary()
        }

        // initialize step back button
        binding.btnStepBack.setOnClickListener {
            showStepGeneral()
        }
    }

    private fun showConfirmationDialog() {
        val dialog = Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.confirmation_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        val btnConfirmationCancel = dialog.findViewById<Button>(R.id.btnConfirmationCancel)
        val btnConfirmationConfirm = dialog.findViewById<Button>(R.id.btnConfirmationConfirm)

        btnConfirmationCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnConfirmationConfirm.setOnClickListener {
            dialog.dismiss()
            findNavController().popBackStack()
        }

        dialog.show()
    }

    private fun showStepGeneral() {
        binding.stepGeneral.visibility = View.VISIBLE
        binding.stepSummary.visibility = View.GONE
    }

    private fun showStepSummary() {
        val contactName: String = binding.tietContactName.text.toString()
        val relation: String = binding.tietRelation.text.toString()
        val phoneNumber: String = binding.tietPhoneNumber.text.toString()

        if (contactName.isNotBlank() && relation.isNotBlank() && phoneNumber.isNotBlank()) {
            binding.tvNameAndRelation.text =
                getString(R.string.text_contact_name_relation, contactName, relation)
            binding.tvPhoneNumber.text = phoneNumber

            binding.stepGeneral.visibility = View.GONE
            binding.stepSummary.visibility = View.VISIBLE
        } else {
            Toast.makeText(requireContext(), "Please provide all information.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}