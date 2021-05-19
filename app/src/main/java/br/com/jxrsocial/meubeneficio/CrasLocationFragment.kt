package br.com.jxrsocial.meubeneficio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import br.com.jxrsocial.meubeneficio.databinding.FragmentCrasLocationBinding

class CrasLocationFragment : Fragment() {

    private var _binding: FragmentCrasLocationBinding? = null
    private val binding get() = _binding!!
    private val cidades = listOf("Vitória", "Vila Velha", "Serra", "Cariacica")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrasLocationBinding.inflate(inflater, container, false)
        setupViews()

        return binding.root
    }

    private fun setupViews() {

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, cidades)

        (binding.citiesMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        (binding.citiesMenu.editText as? AutoCompleteTextView)?.setOnItemClickListener { _, _, position, _ ->
            displayCrasInformation(position)
        }

        binding.exitBt.setOnClickListener { activity?.finish() }
    }

    private fun displayCrasInformation(position: Int) {
        binding.crasInfo.text = when (position) {
            0 -> getString(R.string.cras_vitoria)
            1 -> getString(R.string.cras_vila_velha)
            2 -> getString(R.string.cras_serra)
            3 -> getString(R.string.cras_cariacica)
            else -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
