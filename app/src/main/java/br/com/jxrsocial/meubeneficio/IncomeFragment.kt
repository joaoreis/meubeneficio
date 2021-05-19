package br.com.jxrsocial.meubeneficio

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.jxrsocial.meubeneficio.databinding.FragmentIncomeBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class IncomeFragment : Fragment() {

    private val viewModel: IncomeViewModel by viewModel()
    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.seeBenefitsButton.setOnClickListener {
            val hasError = parseAndCheckFields()
            if (!hasError) navigateToAvailableBenefits()
        }
    }

    private fun navigateToAvailableBenefits() {
        val nome = viewModel.name.value?.split(" ")?.get(0) ?: ""
        val cadUnico = viewModel.hasCadUnico.value ?: false
        val bpc = viewModel.checkBpc()
        val bolsaFamilia = viewModel.checkBolsaFamilia()
        val auxilio = viewModel.checkAuxilioEmergencial()

        val toAvailableBenefitsFragment =
            IncomeFragmentDirections.actionIncomeFragmentToAvailableBenefitsFragment(bpc, bolsaFamilia, auxilio, nome, cadUnico)
        findNavController().navigate(toAvailableBenefitsFragment)
    }

    private fun parseAndCheckFields(): Boolean {
        try {
            viewModel.name.value = binding.etName.text.toString()
            viewModel.age.value = Integer.parseInt(binding.etAge.text.toString())
            viewModel.familySize.value = Integer.parseInt(binding.etFamilySize.text.toString())
            viewModel.income.value = binding.etMonthlyIncome.text.toString().toDouble()
            viewModel.hasCadUnico.value = binding.cadUnicoCheckBox.isChecked
            viewModel.isEmployed.value = binding.employedCheckBox.isChecked
        } catch (e: Exception) {
            showErrorDialog()
            return true
        }
        return false
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(context)
            .setTitle("Algo deu errado!")
            .setMessage("Verifique se todos os campos estão preenchidos e tente novamente!")
            .setNeutralButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
