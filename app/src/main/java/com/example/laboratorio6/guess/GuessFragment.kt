package com.example.laboratorio6.guess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.laboratorio6.R
import com.example.laboratorio6.databinding.GuessFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class GuessFragment : Fragment() {

    private lateinit var binding: GuessFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.guess_fragment, container, false)

        return binding.root
    }

}
