package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-12.
 */
class GenreFootViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView){

    var nextButton : Button? = itemView!!.findViewById(R.id.resultgenre_nextButton_btn)
    var  prevButton : Button? = itemView!!.findViewById(R.id.resultgenre_prevButton_btn)
}