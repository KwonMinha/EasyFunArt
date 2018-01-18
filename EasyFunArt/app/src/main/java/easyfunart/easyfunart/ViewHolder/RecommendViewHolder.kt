package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-05.
 */
class RecommendViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var RecomendImage : ImageView = itemView!!.findViewById(R.id.recomend_recycler_image) as ImageView
    var RecomendTitle : TextView = itemView!!.findViewById(R.id.recomend_recycler_title) as TextView
    var RecomendDate : TextView = itemView!!.findViewById(R.id.recomend_recycler_date) as TextView
    var RecomendPlace : TextView = itemView!!.findViewById(R.id.recomend_recycler_place) as TextView
    var RecomendHeart : Button = itemView!!.findViewById(R.id.recomend_recycler_heart) as Button
    var RecomendDocent : Button = itemView!!.findViewById(R.id.recomend_recycler_docent) as Button
    var RecomendScore : TextView = itemView!!.findViewById(R.id.recomend_recyler_score) as TextView
    var RecomendStar : ImageView = itemView!!.findViewById(R.id.recomend_recycler_star) as ImageView
}