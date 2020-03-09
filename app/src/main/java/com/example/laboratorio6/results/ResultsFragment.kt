@file:Suppress("DEPRECATION")

package com.example.laboratorio6.results


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio6.R
import com.example.laboratorio6.dataBase.DataBaseSurvey
import com.example.laboratorio6.databinding.ResultsFragmentBinding
import com.example.laboratorio6.survey.SurveyViewModel

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class ResultsFragment : Fragment() {

    // Data binding
    private lateinit var binding: ResultsFragmentBinding

    // ViewModel
    private lateinit var surveyViewModel: SurveyViewModel
    private lateinit var resultViewModel: ResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.results_fragment, container, false)

        surveyViewModel = ViewModelProviders.of(activity!!).get(SurveyViewModel::class.java)
        resultViewModel = ViewModelProviders.of(activity!!).get(ResultsViewModel::class.java)

        // Binding
        binding.lifecycleOwner = this

        binding.seeResults.setOnClickListener{ view: View? ->
            resultViewModel.getAnswers()
            view!!.findNavController().navigate(R.id.action_resultsFragment_to_showResultsFragment)
        }

        binding.newSurvey.setOnClickListener { view: View ->
            resultViewModel.newSurvey()
            view.findNavController().navigate(R.id.action_resultsFragment_to_mainFragment)
        }

        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root
    }

    // Inicializando el menu para poder guardar las cosas
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.data_base_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataBase = DataBaseSurvey(context!!)
        dataBase.deleteData()
        return super.onOptionsItemSelected(item)
    }

}
