package easyfunart.easyfunart.Adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Activity.PlaybackScreen
import easyfunart.easyfunart.GET.TrackData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.FloorViewHolder
import easyfunart.easyfunart.ViewHolder.TrackListViewHolder

class TrackListAdapter(var myActivity : Activity, var trackData: ArrayList<TrackData>, requestManager: RequestManager, var exId : Int, var  exImage : String, var exTitle : String, var docentTitle : Int, var trackSize : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_FLOOR: Int = 0
    val TYPE_ITEM: Int = 1

    private var onItemClick: View.OnClickListener? = null


    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_FLOOR) {
            val v = LayoutInflater.from(parent!!.context).inflate(R.layout.floor_list, parent, false)
            return FloorViewHolder(v)
        }
        else{
            val v = LayoutInflater.from(parent!!.context).inflate(R.layout.tracklist_item, parent, false)
            v.setOnClickListener(onItemClick)
            return TrackListViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is FloorViewHolder) {
            holder.Floor.text = trackData.get(position).docent_floor + "F"

        }else if (holder is TrackListViewHolder) {
//            holder.TrackList_number.text = trackData.get(position).docent_floor
            holder.TrackList_item.setOnClickListener{
                //                holder.TrackList_Image.setImageResource(R.drawable.docent_playing_on)

                val intent = Intent(myActivity, PlaybackScreen::class.java)
                intent.putExtra("docentTrack", trackData.get(position).docent_track)
                intent.putExtra("exId", exId)
                intent.putExtra("exImage", exImage)
                intent.putExtra("exTitle", exTitle)
                intent.putExtra("docentTitle", trackData.get(position).docent_title)
                intent.putExtra("trackSize", trackSize)
                myActivity.startActivity(intent)
                myActivity.finish()

            }
            holder.TrackList_title.text = trackData.get(position).docent_title
            holder.TrackList_track.text = "0" + trackData.get(position).docent_track
        }
    }

    override fun getItemViewType(position: Int): Int {
//            Log.d("인덱스사이즈","접근횟수")

        if (trackData.get(position).docent_floor.equals("1")) {
            return TYPE_FLOOR
        } else if (trackData.get(position).docent_floor.equals("2")) {
            return TYPE_FLOOR
        } else if (trackData.get(position).docent_floor.equals("3")) {
            return TYPE_FLOOR
        } else {
            Log.d("아이템접근","횟수")
            return TYPE_ITEM
        }

    }

    override fun getItemCount(): Int{
//        Log.d("인덱스사이즈",(splitList1.size.toString()))
        return trackData.size
    }
}