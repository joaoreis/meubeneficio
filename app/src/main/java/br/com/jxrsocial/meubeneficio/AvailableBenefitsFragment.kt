package br.com.jxrsocial.meubeneficio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.style.TextAlign
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.jxrsocial.meubeneficio.databinding.FragmentAvailableBenefitsBinding

class AvailableBenefitsFragment : Fragment() {

    private var _binding: FragmentAvailableBenefitsBinding? = null
    private val binding get() = _binding!!

    private val args: AvailableBenefitsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvailableBenefitsBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        val benefits = (if (args.bpc) "BPC - Benefício de Prestação Continuada\n" else "") +
            (if (args.auxilio) "Auxílio Emergencial\n" else "") +
            (if (args.bolsaFamilia) "Bolsa Família\n" else "")

        if (benefits.isNotBlank()) {
            binding.tvBenefits.text = getString(R.string.good_news, args.nome, benefits)
            displayCadUnicoInfo()
        } else {
            binding.btCras.isVisible = false
            binding.tvBenefits.text = "No momento você não está elegível a nenhum benefício. Gostaria de fazer outra consulta?"
            binding.btNo.isVisible = true
            binding.btNo.setOnClickListener { activity?.finish() }
            binding.btYes.isVisible = true
            binding.btYes.setOnClickListener {
                findNavController().navigate(AvailableBenefitsFragmentDirections.actionAvailableBenefitsFragmentToIncomeFragment())
            }
        }
    }

    private fun displayCadUnicoInfo() {
        if (!args.cadUnico) {
            binding.informCadUnico.text =
                "Vimos que você ainda não se inscreveu no CadÚnico.\n\nPara receber os benefícios é preciso realizar a inscrição no CRAS mais próximo.\n\nClique no botão abaixo para ser direcionado ao CRAS"
            binding.btCras.setOnClickListener {
                findNavController().navigate(AvailableBenefitsFragmentDirections.actionAvailableBenefitsFragmentToCrasLocationFragment())
            }
        } else {
            binding.informCadUnico.text = "Entre em contato com o CRAS em que já está cadastrado para mais informações!"
            binding.btCras.text = "Sair"
            binding.btCras.setOnClickListener {
                activity?.finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
