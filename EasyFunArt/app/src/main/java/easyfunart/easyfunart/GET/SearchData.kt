package easyfunart.easyfunart.GET

/**
 * Created by minha on 2018-01-11.
 */
data class SearchData (
        var ex_id : Int,
        var ex_image : String,
        var ex_author_name : String,
        var ex_title : String,
        var ex_start_date : String,
        var ex_end_date : String,
        var gallery_name : String,
        var ex_average_grade : Float,
        var likeFlag : Int
)
