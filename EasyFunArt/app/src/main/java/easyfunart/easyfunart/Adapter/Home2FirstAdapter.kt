package easyfunart.easyfunart.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Activity.DisplayActivity
import easyfunart.easyfunart.Activity.TrackActivity
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Fragment.DisplayFragment
import easyfunart.easyfunart.GET.HomeTheme1
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.LikeResponse
import easyfunart.easyfunart.ViewHolder.Home2ViewHolder
import kotlinx.android.synthetic.main.home2_heart_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minha on 2018-01-08.
 */
class Home2FirstAdapter(var context: Context, var dataList: ArrayList<HomeTheme1>?, var requestManager: RequestManager) : RecyclerView.Adapter<Home2ViewHolder>() {
    private var onItemClick: View.OnClickListener? = null

    private var networkService: NetworkService? = null
    private var TAG = "Home_Recycler1_Like"

    //private var isLike: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Home2ViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.content_main2_item, parent, false)
        mainView.setOnClickListener(onItemClick)

        return Home2ViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: Home2ViewHolder?, position: Int) {
        var holder: Home2ViewHolder = holder as Home2ViewHolder

        holder.homeSecondTitle.text = dataList!!.get(position).ex_title
        holder.homeSecondImage.setImageResource(R.drawable.poster)


        if (dataList!!.get(position).ex_image == null)
            requestManager.load(R.drawable.img_poster_default).into(holder!!.homeSecondImage)
        else
            requestManager.load(dataList!!.get(position).ex_image).into(holder!!.homeSecondImage)


        holder.homeSecondStartDate.text = dataList!!.get(position).ex_start_date
        // 전시 날짜
        if (dataList!!.get(position).ex_end_date == null)
            holder.homeSecondEndDate.text = "test date"
        else
            holder.homeSecondEndDate.text = dataList!!.get(position).ex_end_date
        // 전시 장소 - 갤러리 이름
        holder.homeSecondPlace.text = dataList!!.get(position).gallery_name
        // 평점 - 소수점 자르기
        val score = dataList!!.get(position).ex_average_grade
        val formatted = String.format("%.1f", score)
        holder.homeSecondScore.text = formatted

        // 별 이미지
        if (score > 0 && score < 1)
            holder.homeSecondStar.setImageResource(R.color.colorTransparent)
        else if (score > 1 && score < 2)
            holder.homeSecondStar.setImageResource(R.drawable.detail_review_star_one)
        else if (score > 2 && score < 3)
            holder.homeSecondStar.setImageResource(R.drawable.detail_review_star_two)
        else if (score > 3 && score < 4)
            holder.homeSecondStar.setImageResource(R.drawable.detail_review_star_three)
        else if (score > 4 && score < 5)
            holder.homeSecondStar.setImageResource(R.drawable.detail_review_star_four)
        else if (score > 5)
            holder.homeSecondStar.setImageResource(R.drawable.detail_review_star_five)

        // 좋아요 버튼
        if (dataList!!.get(position).likeFlag == 0) {
            holder.homeSecondHeaert.setBackgroundResource(R.drawable.btn_main_like_off)
        } else {
            holder.homeSecondHeaert.setBackgroundResource(R.drawable.btn_main_like_on)
        }

        // 전시상세보기 클릭
        holder.homeSecondIayout.setOnClickListener {
            val intent2 = Intent(context, DisplayFragment::class.java)
            val intent = Intent(context, DisplayActivity::class.java)
            intent.putExtra("ex_image", dataList!!.get(position).ex_image)
            intent.putExtra("ex_id", dataList!!.get(position).ex_id)
            intent.putExtra("ex_title", dataList!!.get(position).ex_title)
            intent.putExtra("ex_start_date", dataList!!.get(position).ex_start_date)
            intent.putExtra("ex_end_date", dataList!!.get(position).ex_end_date)
            intent.putExtra("gallery_name", dataList!!.get(position).gallery_name)
            intent.putExtra("gallery_id", dataList!!.get(position).gallery_id)
            intent.putExtra("ex_average_grade", formatted)
            intent.putExtra("likeFlag", dataList!!.get(position).likeFlag)
            intent2.putExtra("ex_id", dataList!!.get(position).ex_id)
            context.startActivity(intent)
        }

        // 도슨트 팝업
        holder.homeSecondDocent.setOnClickListener {
            val intent = Intent(context, TrackActivity::class.java)
            intent.putExtra("exId", dataList!!.get(position).ex_id)
            intent.putExtra("exImage", dataList!!.get(position).ex_image)
            intent.putExtra("exTitle", dataList!!.get(position).ex_title)
            context.startActivity(intent)
        }

        // 좋아요 팝업
       // isLike = dataList!!.get(position).likeFlag
        holder.homeSecondHeaert.setOnClickListener {
            //            if (dataList!!.get(position).likeFlag == 0){

            networkService = ApplicationController.instance!!.networkService

            val getLikeList = networkService!!.getLikeList(CommonData.user_token!!,
                    exId = dataList!!.get(position).ex_id)
            getLikeList.enqueue(object : Callback<LikeResponse> {
                override fun onResponse(call: Call<LikeResponse>?, response: Response<LikeResponse>?) {
                    if (response!!.isSuccessful) {
                        Log.d(TAG, "성공")
                        if (response.body().status.equals("success")) {
                            Log.d(TAG, "조아용")
                            //isLike = response.body().data.likeFlag

                            if (response.body().data.likeFlag == 1) {
                                holder!!.homeSecondHeaert.setBackgroundResource(R.drawable.btn_main_like_on)
                                var builder: AlertDialog.Builder = AlertDialog.Builder(context)
                                var inflater: LayoutInflater = LayoutInflater.from(context)
                                var view: View = inflater.inflate(R.layout.home2_heart_dialog, null)
                                builder.setView(view)
                                var dialog: Dialog = builder.create()
                                view.dialog_heart_title.setText(dataList!!.get(position).ex_title)
                                dialog.show()
                                //            // dialog 크기변환
                                var dm: DisplayMetrics = view.resources.displayMetrics
                                var px_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240.0f, dm) // float형
                                var px_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 178.0f, dm)

                                val px_width_int = px_width.toInt() // int형으로 변환
                                val px_height_int = px_height.toInt()
                                dialog!!.window.setLayout(px_width_int, px_height_int)

                                dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                // 팝업창 이외 터치 / 백 버튼 금지
                                dialog.setCanceledOnTouchOutside(false)
                                dialog.setCancelable(false)
                                // 좋아요 팝업 애니메이션 효과
                                val animation = AnimationUtils.loadAnimation(context, R.anim.scale) // 안될 땐 clean project
                                view.startAnimation(animation)
                                // 좋아요 팝업 애니메이션 지속 시간
                                var handler = Handler()
                                handler.postDelayed({
                                    dialog.dismiss()
                                }, 1000)


                            } else {

                                holder!!.homeSecondHeaert.setBackgroundResource(R.drawable.btn_main_like_off)


                            }
                        } else {
                            Log.d(TAG, "실패1")
                        }
                    } else {
                        Log.d(TAG, "실패2")
                    }
                }

                override fun onFailure(call: Call<LikeResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                    Log.d(TAG, "통신 실패")
                }
            })
        }
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }
}