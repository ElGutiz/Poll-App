@file:Suppress("DEPRECATION")

package com.example.laboratorio6.survey


import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio6.R
import com.example.laboratorio6.databinding.SurveyFragmentBinding
import com.example.laboratorio6.results.ResultsViewModel

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class SurveyFragment : Fragment() {

    // Binding
    private lateinit var binding: SurveyFragmentBinding

    // View Models
    private lateinit var surveyViewModel: SurveyViewModel
    private lateinit var resultViewModel: ResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.survey_fragment, container, false)

        // Inicializando los view models
        surveyViewModel = ViewModelProviders.of(activity!!).get(SurveyViewModel::class.java)
        resultViewModel = ViewModelProviders.of(activity!!).get(ResultsViewModel::class.java)

        // Binding
        binding.surveyViewModel = surveyViewModel
        binding.resultsViewModel = resultViewModel

        binding.lifecycleOwner = this

        binding.nextQuestion.setOnClickListener{view: View ->
            if(surveyViewModel.allQuestions.size == surveyViewModel.questionLeft){
                surveyViewModel.restartQuestions()
                view.findNavController().navigate(R.id.action_surveyFragment_to_resultsFragment)
            }else {
                updateQuestion()
            }
        }
        return binding.root
    }

    private fun updateQuestion(){
        surveyViewModel.nextQuestion()

        if(binding.ratingBar.visibility == View.VISIBLE){
            resultViewModel.add(binding.ratingBar.rating.toString())
        }else{
            resultViewModel.add(binding.inputAnswer.text.toString())
        }

        when(surveyViewModel.questionType){
            "Rating" -> {
                binding.ratingBar.visibility = View.VISIBLE
                binding.inputAnswer.visibility = View.GONE
            }
            else -> {
                binding.ratingBar.visibility = View.GONE
                binding.inputAnswer.visibility = View.VISIBLE

                // Cambiando a lo que puede ingresar el usuario
                if (surveyViewModel.questionType == "Number"){
                    binding.inputAnswer.inputType = InputType.TYPE_CLASS_NUMBER
                }else{
                    binding.inputAnswer.inputType = InputType.TYPE_CLASS_TEXT
                }

            }
        }

        binding.ratingBar.rating = 0f
        binding.inputAnswer.text.clear()
    }

}
