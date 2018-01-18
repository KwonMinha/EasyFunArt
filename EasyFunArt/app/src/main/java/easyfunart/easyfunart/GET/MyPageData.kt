package easyfunart.easyfunart.GET

/**
 * Created by minha on 2018-01-13.
 */
data class MyPageData (
        var user_data : MyPageUserData,
        var user_Like_Count : Int,
        var user_Review_Count : Int,
        var user_Grade_Count : Int,
        var user_Like_List : ArrayList<MyPageUserLikeData>,
        var user_Review_List : ArrayList<MyPageUserReviewData>
)