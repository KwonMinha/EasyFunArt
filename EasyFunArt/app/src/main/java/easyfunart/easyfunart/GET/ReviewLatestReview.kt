package easyfunart.easyfunart.GET

/**
 * Created by minha on 2018-01-10.
 */
data class ReviewLatestReview (
        var review_id : Int,
        var review_date : String,
        var review_grade : Float,
        var user_id : Int,
        var review_content : String,
        var review_image : String,
        var user_nickname : String,
        var user_profile : String
)