package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.ReviewData

/**
 * Created by minha on 2018-01-10.
 */
data class ReviewResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : ReviewData
)