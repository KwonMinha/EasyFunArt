package easyfunart.easyfunart.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import de.hdodenhof.circleimageview.CircleImageView
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.ExhibitionResponse
import kotlinx.android.synthetic.main.fragment_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minha on 2018-01-10.
 */
class DisplayFragment : Fragment() {
    val TAG : String = " LOG::DisplayFragment"

    private var networkService: NetworkService? = null
    private var requestManager : RequestManager? = null
    private var imageView : CircleImageView? =null
    private var preFerImg : ArrayList<String>? = null
    private var unpreFerImg : ArrayList<String>? = null
    private var ex_id : Int? = null
    private var imgIndex = 0
    private var imgIndex2 = 0
    private var imgArray : ArrayList<ImageView> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_display, container, false)
        Log.v(TAG, "onCreateView")
        // createHash()
        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        imageView = v!!.findViewById(R.id.profile)

        getExhibitionList()

        return v
    }

    fun getExhibitionList() {
        val getExhibitionList = networkService!!.getExhibitionList(CommonData.user_token!!, arguments.getInt("ex_id"))
        getExhibitionList.enqueue(object : Callback<ExhibitionResponse> {
            override fun onFailure(call: Call<ExhibitionResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }

            override fun onResponse(call: Call<ExhibitionResponse>?, response: Response<ExhibitionResponse>?) {
                if (response!!. isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")){
                        Log.d(TAG, "성공2")
                        var i : Int = 0
                        var context1 = response.body().data.exhibition.content

                        if (context1 == null)
                            intro_context.text = ""
                        else{
                            var context2 = context1.replace(" ", "\u00a0")
                            intro_context.text = context2
                        }
                        createHash()
                        if (response.body().data.authorResult.author_image == null)
                            requestManager!!.load(R.drawable.pic1).into(profile)
                        else
                            requestManager!!.load(response.body().data.authorResult.author_image).into(profile)

                        var author_context1 = response.body().data.authorResult.author_content
                        if ( author_context1 == null)
                            author_context.text = ""
                        else {
                            var author_context2 = author_context1.replace(" ", "\u00a0")
                            author_context.text = response.body().data.authorResult.author_content
                        }


                        Log.d(TAG, response.body().data.selectedHashtag.toString())
                        Log.d(TAG, response.body().data.unSelectedHashtag.toString())

                        preFerImg = response.body().data.selectedHashtag

                        unpreFerImg = response.body().data.unSelectedHashtag



                        for(i in 0..(preFerImg!!.size-1)){
                            if(preFerImg!![i]=="적막감"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")

                            } else if(preFerImg!![i]=="환상적인"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(preFerImg!![i]=="세련된"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(preFerImg!![i]=="편안한") {
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(preFerImg!![i]=="강렬한"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(preFerImg!![i]=="따뜻한"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_on_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="슬픈"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="유유자적한"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="우아한"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="시원한"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_10)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="사실적인"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_love_on_11)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="모험"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="코믹"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="범죄"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="판타지"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="픽션"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="공포/스릴러"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="미스터리"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="철학"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="정치"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="사랑"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_10)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="풍자"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_11)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="과학"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_subject_on_12)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(preFerImg!![i]=="서촌"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="강남"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="홍대/합정"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="인사동"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="이태원"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="충무로"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="혜화/대학로"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="삼청동/북촌"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="기타"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_place_on_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }
                            else if(preFerImg!![i]=="동양화"){
                                imgArray[imgIndex].setBackgroundResource(R.drawable.mypage_preference_genre_on_1)
                                imgIndex+= 1
                            }else if(preFerImg!![i]=="서양화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="도예"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="금속"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="일러스트"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="목공"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="현대미술"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="팝아트"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="풍경화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="카툰"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_10)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="인물화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_11)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(preFerImg!![i]=="사진전"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_12)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }
                        }
                        for(j in 0..unpreFerImg!!.size-1){
                            if(unpreFerImg!![j]=="적막감"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(unpreFerImg!![j]=="환상적인"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")

                            } else if(unpreFerImg!![j]=="세련된"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(unpreFerImg!![j]=="편안한") {
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(unpreFerImg!![j]=="강렬한"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(unpreFerImg!![j]=="따뜻한"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="슬픈"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="유유자적한"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="우아한"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="시원한"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_10)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="사실적인"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_love_off_11)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="모험"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="코믹"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="범죄"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="판타지"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="픽션"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="공포/스릴러"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="미스터리"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="철학"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="정치"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="사랑"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_10)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="풍자"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_11)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="과학"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_12)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            } else if(unpreFerImg!![j]=="서촌"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="강남"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="홍대/합정"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="인사동"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="이태원"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="충무로"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="혜화/대학로"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="삼청동/북촌"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="기타"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_place_off_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="동양화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_1)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="서양화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_2)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="도예"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_3)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="금속"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_4)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="일러스트"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_5)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="목공"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_6)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="현대미술"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_7)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="팝아트"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_8)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="풍경화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_9)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="카툰"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_10)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="인물화"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_11)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }else if(unpreFerImg!![j]=="사진전"){
                                imgArray[imgIndex]!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_12)
                                imgIndex+=1
                                Log.d(TAG, "해시태그 성공")
                            }
                        }

                    }
                }
            }
        })
    }



    fun createHash(){
        imgArray.add(hash1)
        imgArray.add(hash2)
        imgArray.add(hash3)
        imgArray.add(hash4)
        imgArray.add(hash5)
        imgArray.add(hash6)
        imgArray.add(hash7)
        imgArray.add(hash8)
    }
}