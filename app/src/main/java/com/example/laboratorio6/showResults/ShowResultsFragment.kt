@file:Suppress("DEPRECATION")

package com.example.laboratorio6.showResults


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.laboratorio6.R
import com.example.laboratorio6.databinding.ShowResultsBinding
import com.example.laboratorio6.results.ResultsViewModel

/**
 * A simple [Fragment] subclass.
 */
class ShowResultsFragment : Fragment() {

    private lateinit var binding: ShowResultsBinding
    private lateinit var resultViewModel: ResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.show_results, container, false)

        resultViewModel = ViewModelProviders.of(activity!!).get(ResultsViewModel::class.java)

        binding.lifecycleOwner = this

        return binding.root
    }

}
