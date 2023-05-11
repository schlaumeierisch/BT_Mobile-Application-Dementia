package at.fhv.mme.bt_dementia_app.view.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.fhv.mme.bt_dementia_app.R
import at.fhv.mme.bt_dementia_app.databinding.FragmentCalendarBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private var date = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        // observe the activity list from the ViewModel
    }

    private fun initViews() {
        // set page title & date
        binding.header.tvTitle.setText(R.string.title_calendar)
        binding.tvCurrentDate.text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        // initialize adapter & RecyclerView


        // initialize header history button
        binding.header.ibtnHeader.visibility = View.VISIBLE
        binding.header.ibtnHeader.setOnClickListener {
            // TODO
        }

        // initialize previous/next day buttons
        binding.ibtnPreviousDay.setOnClickListener {
            date = date.minusDays(1)
            binding.tvCurrentDate.text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
        binding.ibtnNextDay.setOnClickListener {
            date = date.plusDays(1)
            binding.tvCurrentDate.text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }

        // initialize add activity button

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}