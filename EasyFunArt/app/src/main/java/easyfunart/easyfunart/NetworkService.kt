package easyfunart.easyfunart

import easyfunart.easyfunart.POST.*
import easyfunart.easyfunart.PUT.MyPageNickModiPut
import easyfunart.easyfunart.PUT.MyPagePrefModiPut
import easyfunart.easyfunart.Response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by minha on 2018-01-05.
 */
interface NetworkService {
    // 홈 액티비티
    @GET("api/home")
    fun getHomeList(
            @Header("user_token") user_token : String
    ): Call<HomeResponse>

    // 홈 액티비티 좋아요
    @GET("/api/home/like")
    fun getLikeList(
            @Header("user_token") user_token: String,
            @Query("exId") exId : Int
    ) : Call<LikeResponse>

    // 맞춤 추천 액티비티
    @GET("api/recommend")
    fun getRecoList(
            @Header("user_token") user_token: String
    ): Call<RecoResponse>

    // 전시정보 가져오기
    @GET("api/exhibition/{ex_id}/info/")
    fun getExhibitionList(
            @Header("user_token") user_token : String,
            @Path("ex_id") ex_id : Int
    ) : Call<ExhibitionResponse>

    // 리뷰 불러오기
    @GET("api/exhibition/{ex_id}/review")
    fun getReviewList(
            @Path("ex_id") ex_id: Int
    ) : Call<ReviewResponse>

    // 별점 받아오기
    @GET("api/home/callgrade")
    fun getStarList(
            @Header("user_token") user_token: String,
            @Query("exId") exId: Int
    ) : Call<StarResponse>

    // 별점 등록
    @POST("api/home/scoregrade/")
    fun postStar(
            @Header("user_token") user_token: String,
            @Query("exId") exId: Int,
            @Body StarPost : StarPost
    ) : Call<StarPostResponse>

    // 검색
    @GET("api/search")
    fun getSearchList(
            @Header("user_token") user_token: String,
            @Query("qString") qString : String,
            @Query("period") period : Int,
            @Query("order") order : Int
    ) : Call<SearchResponse>

    // 리뷰 등록하기
    @Multipart
    @POST("api/review")
    fun postReview(
            @Header("user_token") user_token: String,
            @Part("exId") exId: RequestBody,
            @Part("reviewContent") reviewContent : RequestBody,
            @Part("reviewGrade") reviewGrade : RequestBody,
            @Part("reviewWatchDate") reviewWatchDate : RequestBody,
            @Part image: MultipartBody.Part?
            ) : Call<PostReviewResponse>

    // 갤러리 정보 불러오기
    @GET("api/gallery/{galleryId}/info")
    fun getGalleryList(
            @Path("galleryId") galleryId : Int
    ) : Call<GalleryResponse>

    ///////////////// 종완////////////////
    //주변 갤러리 받기
    @GET("/api/playlist/site")
    fun getSurroundTab(
            @Query("latitude")latitude : Double?,
            @Query("longitude")longitude : Double?
    ):Call<Gallery2Response>

    //찜한 전시회 받기
    @GET("/api/playlist/favor")
    fun getBookMarkTab(
            @Header("user_token")user_token : String
    ):Call<Gallery2Response>

    //트랙리스트 받기
    @GET("/api/playlist/guide")
    fun getTrackList(
            @Header("user_token") user_token : String,
            @Query("exId") exId : Int
    ):Call<TrackListResponse>

    //재생화면 데이터 받기
    @GET("/api/docent")
    fun getInitData(
            @Query("exId") exId : Int,
            @Query("docentTrack") docentTrack : Int
    ) : Call<RecordResponse>

    //    //이전 음성 받기
    @GET("api/docent/before")
    fun getPrevRecord(
            @Query("exId") exId : Int,
            @Query("docentTrack") docentTrack : Int
    ): Call<RecordResponse>

    //다음 음성 받기
    @GET("api/docent/next")
    fun getNextRecord(
            @Query("exId") exId : Int,
            @Query("docentTrack") docentTrack : Int
    ): Call<RecordResponse>

    ///////////////// 지윤////////////////
    /*1. 로그인*/
    @POST("/api/login/")
    fun sendFacebook(@Header("user_token") user_token: String,
                     @Body sendFacebook : FacebookPost) : Call<SendResponse>

    /*닉네임 중복체크*/

    @GET("/api/login/check")
    fun getNickname(
            @Query("userNickname") userNickname : String
    ) : Call<NicknameResponse>

    /*유저 정보 추가*/
    @POST("/api/preference/users")
    fun senduserInfo(
            @Header("user_token") user_token : String,
            @Body userInfoPost: UserInfoPost) : Call<UserInfoResponse>


    /*유저 선호항목 추가*/
    @POST("/api/preference/")
    fun sendpreference(@Header("user_token") user_token: String,
                       @Body titlePost: TitlePost) : Call<TitleResponse>


    //예지

    @GET("api/mypage")
    fun getMyPage(
            @Header("user_token") user_token : String
    ) : Call<MyPageResponse>

    @PUT("api/mypage/preModi")
    fun putMyPagePrefModi(
            @Header("user_token") user_token : String,
            @Body myPagePrefModiPut: MyPagePrefModiPut
    ) : Call<MyPagePrefModiResponse>


    @Multipart
    @POST("api/mypage/profileModi")
    fun putMyPageProfileModi(
            @Header("user_token") user_token : String,
            @Part image : MultipartBody.Part
    ) : Call<MyPageProfileModiResponse>

    @PUT("api/mypage/nickModi")
    fun putMyPageNickMode(
            @Header("user_token") user_token : String,
            @Body myPageNickModiPut: MyPageNickModiPut
    ) : Call<MyPageNickModiResponse>


    // 일련번호 불러오기
    @GET("api/home/serial")
    fun getSerialList(
            @Query("serial_num") serial_num : String
    ) : Call<SerialResponse>

//    // 검색
//    @GET("api/search")
//    fun getSearchList(
//            @Header("user_token") user_token: String,
//            @Query("qString") qString : String,
//            @Query("period") period : Int,
//            @Query("order") order : Int
//    ) : Call<SearchResponse>

}