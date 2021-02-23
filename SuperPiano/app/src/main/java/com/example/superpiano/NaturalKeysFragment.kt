package com.example.superpiano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup


class NaturalKeysFragment : Fragment() {

    private var _binding:FragmentNaturalKeyBinding? = null
    private val binding get() = _binding
    private lateinit var note:String


    var onKeyDown:((note:String) -> Unit)? = null
    var onKeyUp:((note:String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getString("NOTE") ?: "?"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNaturalKeyBinding.inflate(layoutInflater)
        val view = binding.root
         view.natrualToneKey.setOnTouchListener(object: View.OnTouchListener{
             override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                 when(event?.aciton){
                     MotionEvent.ACTION_DOWN -> this@NaturalKeysFragment.onKeyDown?.inoke(note)
                     MotionEvent.ACTION_UP -> this@NaturalKeysFragment.onKeyUp?.inoke(note)
                 }
                 return True
             }
         })
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(note: String) =
            NaturalKeysFragment().apply {
                arguments = Bundle().apply {
                    putString("Note", note)
                }
            }
    }

}