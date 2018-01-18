package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-12.
 */
class FloorViewHolder(itemView : View?): RecyclerView.ViewHolder(itemView) {
    var Floor : TextView = itemView!!.findViewById(R.id.floor_text) as TextView
}