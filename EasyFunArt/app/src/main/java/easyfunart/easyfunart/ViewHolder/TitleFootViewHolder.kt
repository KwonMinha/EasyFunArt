package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-13.
 */
class TitleFootViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var nextButton : Button = itemView!!.findViewById(R.id.resulttitle_nextButton_btn)
    var  prevButton : Button = itemView!!.findViewById(R.id.resulttitle_prevButton_btn)
}