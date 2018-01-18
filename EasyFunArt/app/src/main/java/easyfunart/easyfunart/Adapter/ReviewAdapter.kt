package easyfunart.easyfunart.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.GET.ReviewLatestReview
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.ReviewViewHolder

/**
 * Created by minha on 2018-01-10.
 */
class ReviewAdapter (var context: Context, var dataList : ArrayList<ReviewLatestReview>?, var requestManager: RequestManager) : RecyclerView.Adapter<ReviewViewHolder>(){
    private var onItemClick : View.OnClickListener? = null

    private var networkService: NetworkService? = null
    private var TAG = "LOG::ReviewAdapter"

    override fun onBindViewHolder(holder: ReviewViewHolder?, position: Int) {
        var holder : ReviewViewHolder = holder as ReviewViewHolder

        Log.d(TAG, "리뷰 어댑터 바인드 뷰홀더 들어옴")
        // 리뷰 이미지
        if(dataList!!.get(position).review_image == null)
            holder.reviewContentImg.visibility = View.GONE
        else {
            requestManager.load(dataList!!.get(position).review_image).into(holder.reviewContentImg)
            holder.reviewContentImg.visibility = View.VISIBLE
        }
        // 작성자 프로필 이미지
        if(dataList!!.get(position).user_profile == null)
            requestManager.load(R.drawable.pic2).into(holder.reviewProfile)
        else
            requestManager.load(dataList!!.get(position).user_profile).into(holder.reviewProfile)
        // 작성자 이름
        holder.reviewName.text = dataList!!.get(position).user_nickname
        // 리뷰 내용
        holder.reviewContent.text = dataList!!.get(position).review_content
        // 전시 관람 날짜 - 아직 없음
        // 리뷰 작성 날짜
        holder.reviewDate2.text = dataList!!.get(position).review_date
        // 리뷰 별점 - 아직 안함
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReviewViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.fragment_review_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return ReviewViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }
}