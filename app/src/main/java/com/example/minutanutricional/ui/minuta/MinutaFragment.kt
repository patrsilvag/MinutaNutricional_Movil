package com.example.minutanutricional.ui.minuta

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.minutanutricional.R

class MinutaFragment : Fragment(R.layout.fragment_minuta) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVerRecetas = view.findViewById<Button>(R.id.btnVerRecetas)

        btnVerRecetas.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Evento desde Fragment clásico",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
