package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-12.
 */
class BookMarkViewHolder (itemView : View?): RecyclerView.ViewHolder(itemView) {

    var gallery_image : ImageView = itemView!!.findViewById(R.id.bookmark_gallery_image) as ImageView
    var gallery_text : TextView = itemView!!.findViewById(R.id.bookmark_gallery_text) as TextView
    var gallery_location : TextView = itemView!!.findViewById(R.id.bookmark_gallery_location) as TextView

}