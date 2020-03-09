@file:Suppress("DEPRECATION")

package com.example.laboratorio6


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio6.databinding.MainFragmentBinding
import com.example.laboratorio6.results.ResultsViewModel
import com.example.laboratorio6.survey.SurveyViewModel

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    // Binding
    private lateinit var binding: MainFragmentBinding

    // View Models
    private lateinit var surveyViewModel: SurveyViewModel
    private lateinit var resultViewModel: ResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inicializando el binding
        binding = DataBindingUtil.inflate(inflater,
            R.layout.main_fragment, container, false)

        // Inicializando los view models
        surveyViewModel = ViewModelProviders.of(activity!!).get(SurveyViewModel::class.java)
        resultViewModel = ViewModelProviders.of(activity!!).get(ResultsViewModel::class.java)

        binding.lifecycleOwner = this

        // Cambiando a la encuesta
        binding.nextButt.setOnClickListener { view: View->
            surveyViewModel.restartQuestions()
            view.findNavController().navigate(R.id.action_mainFragment_to_surveyFragment)
        }

        // Cambiando a agregar
        binding.addQuestion.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drawable_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view!!.findNavController().navigate(R.id.action_mainFragment_to_guessFragment)
        return super.onOptionsItemSelected(item)
    }
}
