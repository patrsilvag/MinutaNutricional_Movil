package com.example.minutanutricional.data

import com.example.minutanutricional.model.Receta
import com.google.firebase.database.*

object RecetaRepository {

    private val database = FirebaseDatabase.getInstance()
    private val recetasRef = database.getReference("recetas")

    fun escucharRecetas(
        onSuccess: (List<Receta>) -> Unit,
        onError: () -> Unit = {}
    ) {
        recetasRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = snapshot.children.mapNotNull {
                    it.getValue(Receta::class.java)
                }
                onSuccess(lista)
            }

            override fun onCancelled(error: DatabaseError) {
                onError()
            }
        })
    }
}