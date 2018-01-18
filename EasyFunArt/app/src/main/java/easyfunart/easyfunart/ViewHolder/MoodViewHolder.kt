package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-12.
 */
class MoodViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var mood_image : ImageView = itemView!!.findViewById(R.id.mood_image_view)

}