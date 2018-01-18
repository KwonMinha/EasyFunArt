package easyfunart.easyfunart.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.GET.Gallery2Data
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.BookMarkViewHolder

/**
 * Created by minha on 2018-01-12.
 */
class BookMarkAdapter(var bookmarkList : ArrayList<Gallery2Data>, var requestManager : RequestManager) : RecyclerView.Adapter<BookMarkViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookMarkViewHolder {

        Log.d("온크리에이트","생성")
        val mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.book_mark_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return BookMarkViewHolder(mainView)
    }

    override fun onBindViewHolder(holder:BookMarkViewHolder?, position: Int) {
        Log.d("바인드뷰","생성")

        requestManager.load(bookmarkList.get(position).ex_image).into(holder!!.gallery_image)
//        holder!!.gallery_image.setImageResource(bookmarkList.get(position).Gallery_image)
        holder!!.gallery_text.text = bookmarkList.get(position).ex_title
        holder!!.gallery_location.text = bookmarkList.get(position).gallery_name

    }

    override fun getItemCount(): Int  = bookmarkList.size
}