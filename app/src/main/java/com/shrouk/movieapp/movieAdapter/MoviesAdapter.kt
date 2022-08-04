package com.shrouk.movieapp.movieAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shrouk.movieapp.R
import com.shrouk.movieapp.interfaces.ProductsOnClick
import com.shrouk.movieapp.movieModel.Data


class MoviesAdapter(
    private var movielist: ArrayList<Data>,
    private var context: Context,
    productsOnClick: ProductsOnClick,
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var onclicklistner: ProductsOnClick = productsOnClick



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = movielist[position]
        holder.name.text = movies.name
        holder.price.text = "Price: ${movies.price}$"
        holder.quantity.text = "Quantity:${movies.quantity}"
        holder.resturantid.text = "Res_Id:${movies.restaurant_id}"

        Glide.with(View(context)).load(movies.image).into(holder.image)
        holder.id.text = movies.id.toString()
        holder.itemView.setOnClickListener { onclicklistner.productClick(productId = movielist[position].id) }


    }

    override fun getItemCount(): Int {
        return movielist.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.myname)
        var price: TextView = itemView.findViewById(R.id.myprice)
        var quantity: TextView = itemView.findViewById(R.id.myquantity)
        var resturantid: TextView = itemView.findViewById(R.id.myrestaurantId)
        var image: ImageView = itemView.findViewById(R.id.my_image)
        var id: TextView = itemView.findViewById(R.id.myid)


    }


}

