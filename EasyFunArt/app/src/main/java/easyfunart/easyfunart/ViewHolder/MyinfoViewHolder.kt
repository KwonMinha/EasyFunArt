package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-13.
 */
class MyinfoViewHolder(itemView : View?) : RecyclerView.ViewHolder (itemView) {
    var exhibitImage : ImageView = itemView!!.findViewById(R.id.exhibit_list_image) as ImageView
    var exhibitName : TextView = itemView!!.findViewById(R.id.exhibit_list_name) as TextView
}