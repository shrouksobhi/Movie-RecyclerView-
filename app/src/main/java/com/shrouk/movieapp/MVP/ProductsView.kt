package com.shrouk.movieapp.MVP

import com.shrouk.movieapp.movieModel.Data

interface ProductsView {
fun setProducts(datalist:ArrayList<Data>)
fun setError(errormessage:String)

}