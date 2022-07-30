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
import com.shrouk.movieapp.movieModel.Movies


class MoviesAdapter(private var movielist: Movies, private var context: Context) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var onclicklistner:OnItemClickListner

    interface OnItemClickListner {fun onClick(position:Int)}

    fun setOnitemClick(listner:OnItemClickListner){
        onclicklistner=listner

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_items, parent, false)
        return ViewHolder(view,onclicklistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = movielist.data[position]
        holder.name.text = movies.name
        holder.price.text = "Price: ${movies.price}"
        holder.quantity.text = "Quantity:${movies.quantity}"
        holder.resturantid.text = "Res_Id:${movies.restaurant_id}"

        Glide.with(View(context)).load(movies.image).into(holder.image)
        holder.id.text= movies.id.toString()



    }

    override fun getItemCount(): Int {
        return movielist.data.size
    }


    class ViewHolder(itemView: View,listner: OnItemClickListner ) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.myname)
        var price: TextView = itemView.findViewById(R.id.myprice)
        var quantity: TextView = itemView.findViewById(R.id.myquantity)
        var resturantid: TextView = itemView.findViewById(R.id.myrestaurantId)
        var image: ImageView = itemView.findViewById(R.id.my_image)
         var id:TextView = itemView.findViewById(R.id.myid)

        init{
            itemView.setOnClickListener {
                listner.onClick(adapterPosition)
            }

        }

    }


}

