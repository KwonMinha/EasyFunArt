package easyfunart.easyfunart.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import easyfunart.easyfunart.R

/**
 * Created by minha on 2018-01-12.
 */
class TrackListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var TrackList_title : TextView = itemView!!.findViewById(R.id.trackList_title) as TextView
    var TrackList_track : TextView = itemView!!.findViewById(R.id.trackList_track) as TextView
    var TrackList_Image : ImageView = itemView!!.findViewById(R.id.trackList_button) as ImageView
    var TrackList_item : LinearLayout = itemView!!.findViewById(R.id.trackList_item) as LinearLayout
}