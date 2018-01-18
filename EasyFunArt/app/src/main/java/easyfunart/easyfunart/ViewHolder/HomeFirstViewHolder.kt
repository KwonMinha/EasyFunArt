package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-05.
 */
class HomeFirstViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView){
    var homeFirstImage : ImageView = itemView!!.findViewById(R.id.home_first_image) as ImageView
    var homeFirstTitle : TextView = itemView!!.findViewById(R.id.home_first_title) as TextView
}