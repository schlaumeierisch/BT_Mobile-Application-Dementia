package at.fhv.mme.bt_dementia_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.fhv.mme.bt_dementia_app.databinding.FragmentMedicationBinding

class MedicationFragment : Fragment() {

    private var _binding: FragmentMedicationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: init

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}