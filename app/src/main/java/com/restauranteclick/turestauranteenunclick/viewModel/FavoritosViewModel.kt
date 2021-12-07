package com.restauranteclick.turestauranteenunclick.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldValue
import com.restauranteclick.turestauranteenunclick.App
import com.restauranteclick.turestauranteenunclick.ListaRestaurante
import com.restauranteclick.turestauranteenunclick.entities.Restaurante
import com.restauranteclick.turestauranteenunclick.entities.utils.Constantes

class FavoritosViewModel {
    private val firestore = App.getFirestore()
    private val auth = App.getAuth()

    fun addFav(restaurante: Restaurante) {
        firestore.collection(Constantes.USUARIOS).document(auth.currentUser.uid)
                .update(Constantes.FAV, FieldValue.arrayUnion(restaurante.id))
    }

    fun delFav(restaurante: Restaurante) {
        auth.currentUser?.let {
            firestore.collection(Constantes.USUARIOS).document(it.uid)
                .update(Constantes.FAV, FieldValue.arrayRemove(restaurante.id))
        }
    }

    fun findAllFavs(): LiveData<Restaurante?> {
        val liveData = MutableLiveData<Restaurante?>()
        App.getAuth().currentUser?.let {
            firestore.collection(Constantes.USUARIOS).document(it.uid)
                .get().addOnSuccessListener { usuario ->
                        val favs = usuario.data?.get(Constantes.FAV)
                        if (favs != null) {


                        }
                    }
        }


        return liveData
    }
}