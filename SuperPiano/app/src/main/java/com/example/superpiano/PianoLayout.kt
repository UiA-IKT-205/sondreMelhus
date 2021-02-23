package com.example.superpiano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class PianoLayout : Fragment() {

    private var _binding:FragmentPianoBinding? = null
    private val binding get() = _binding!!

    private val naturalKeys = listOf("C","D","E","F","G","A","B")

    //override fun onCreate(savedInstanceState: Bundle?) {...}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPianoBinding.inflate(layoutInflater)
        val view = binding.root

        val fm = childFragmentManager
        val ft = fm.beginTransaction()

        naturalKeys.forEach {
            val naturalPianoKey = naturalKeysFragment.newInstance(it)

            naturalPianoKey.onKeyDown = {
                println("Piano key up $it")
            }

            naturalPianoKey.onKeyUp = {
                println("Piano key up $it")
            }

            ft.add(view.pianoKeys.id,naturalPianoKey,"note_$it")
        }
        return inflater.inflate(R.layout.fragment_piano, container, false)
    }

}