package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-08.
 */
class Home2ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var homeSecondImage : ImageView = itemView!!.findViewById(R.id.home2_recycler_image) as ImageView
    var homeSecondTitle : TextView = itemView!!.findViewById(R.id.home2_recycler_title) as TextView
    var homeSecondStartDate : TextView = itemView!!.findViewById(R.id.home2_recycler_start_date) as TextView
    var homeSecondEndDate : TextView = itemView!!.findViewById(R.id.home_recycler2_end_date) as TextView
    var homeSecondStar : ImageView = itemView!!.findViewById(R.id.home2_recycler_star) as ImageView
    var homeSecondPlace : TextView = itemView!!.findViewById(R.id.home2_recycler_place) as TextView
    var homeSecondDocent : Button = itemView!!.findViewById(R.id.home2_recycler_docent) as Button
    var homeSecondHeaert : Button = itemView!!.findViewById(R.id.home2_recycler_heart) as Button
    var homeSecondIayout : RelativeLayout = itemView!!.findViewById(R.id.home2_layout) as RelativeLayout
    var homeSecondScore : TextView = itemView!!.findViewById(R.id.home2_recyler_score) as TextView
}