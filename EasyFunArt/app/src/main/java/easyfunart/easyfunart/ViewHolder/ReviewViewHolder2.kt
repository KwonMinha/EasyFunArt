package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-13.
 */
class ReviewViewHolder2 (itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var reviewImg : ImageView = itemView!!.findViewById(R.id.review_profile) as ImageView
    var reviewName : TextView = itemView!!.findViewById(R.id.review_name) as TextView
    var reviewContent : TextView = itemView!!.findViewById(R.id.review_content) as TextView
    var reviewStar : ImageView = itemView!!.findViewById(R.id.review_star) as ImageView
    var reviewDate : TextView = itemView!!.findViewById(R.id.review_date) as TextView
    var beforeDate : TextView = itemView!!.findViewById(R.id.before_date) as TextView
    var reviewTitle : TextView = itemView!!.findViewById(R.id.review_title) as TextView

}
