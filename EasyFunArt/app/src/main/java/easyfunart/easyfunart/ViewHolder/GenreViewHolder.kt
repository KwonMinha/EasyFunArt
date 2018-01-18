package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-12.
 */
class GenreViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var genre_image : ImageView = itemView!!.findViewById(R.id.genre_image_view)
    var genre_layout : LinearLayout = itemView!!.findViewById(R.id.genre_layout)



}