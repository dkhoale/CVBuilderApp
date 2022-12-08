package edu.miu.cvbuilderapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import edu.miu.cvbuilderapp.databinding.DialogAddWorkExperienceBinding
import edu.miu.cvbuilderapp.model.CompanyMapping
import edu.miu.cvbuilderapp.model.WorkExperience
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddWorkExperienceDialog : DialogFragment() {
    private lateinit var binding : DialogAddWorkExperienceBinding
    private val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddWorkExperienceBinding.inflate(layoutInflater)

        val locationAdapter  = this.context?.let { ArrayAdapter<String>(it,android.R.layout.select_dialog_item, resources.getStringArray(R.array.company_locations)) }
        binding.wdlLocationActv.threshold = 2
        binding.wdlLocationActv.setAdapter(locationAdapter)

        val companyAdapter = this.context?.let {ArrayAdapter<String>(it,android.R.layout.select_dialog_item,CompanyMapping.values().map {i -> i.name }.toTypedArray())}
        binding.wdlCompanyActv.threshold = 2
        binding.wdlCompanyActv.setAdapter(companyAdapter)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        val bundle = Bundle()
                        val workExp = WorkExperience(binding.wdlPositionEtv.text.toString(),
                        CompanyMapping.valueOf(binding.wdlCompanyActv.text.toString()),
                        LocalDate.parse(binding.wdlFromEtv.text.toString(), dateFormatter),
                        LocalDate.parse(binding.wdlToEtv.text.toString(), dateFormatter),
                        binding.wdlLocationActv.text.toString(),
                        binding.wdlDescriptionEtv.text.toString())
                        bundle.putSerializable("workExperience",workExp)
                        parentFragmentManager.setFragmentResult("OK", bundle)
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        this.dialog?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}