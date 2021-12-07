package com.restauranteclick.turestauranteenunclick.entities

data class Restaurante(val nombre:String, val telefono:Long,val direccion:String){
    lateinit var id:String
    private var fav:Boolean=false

}
