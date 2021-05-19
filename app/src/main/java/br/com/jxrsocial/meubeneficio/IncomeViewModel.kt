package br.com.jxrsocial.meubeneficio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val MINIMUM_WAGE = 1045.0

class IncomeViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val age = MutableLiveData<Int>()
    val familySize = MutableLiveData<Int>()
    val income = MutableLiveData<Double>()
    val hasCadUnico = MutableLiveData<Boolean>()
    val isEmployed = MutableLiveData<Boolean>()

    private fun incomePerCapta(): Double = (income.value!! / familySize.value!!) // Don't try this at home

    fun checkBolsaFamilia(): Boolean {
        return incomePerCapta() <= 178
    }

    fun checkAuxilioEmergencial(): Boolean {
        val ageCheck = age.value?.let { it >= 18 } ?: true
        val employmentCheck = isEmployed.value == false
        val incomeCheck = incomePerCapta() <= MINIMUM_WAGE / 2
        return ageCheck && incomeCheck && employmentCheck
    }

    fun checkBpc(): Boolean {
        val ageCheck = age.value?.let { it >= 65 } ?: true
        val incomeCheck = incomePerCapta() <= MINIMUM_WAGE / 2
        return ageCheck && incomeCheck
    }
}
