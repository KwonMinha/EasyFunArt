package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.LikeData

/**
 * Created by minha on 2018-01-08.
 */
data class LikeResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : LikeData
)