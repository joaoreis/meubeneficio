package br.com.jxrsocial.meubeneficio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.jxrsocial.meubeneficio.databinding.FragmentTutorialBinding

class TutorialFragment : Fragment() {

    private var _binding: FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        setupButton()
        return binding.root
    }

    private fun setupButton() {
        binding.button.setOnClickListener {
            val toIncomeFrag = TutorialFragmentDirections.actionBlankFragmentToIncomeFragment()
            findNavController().navigate(toIncomeFrag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
