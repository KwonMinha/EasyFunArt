package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.StarData

/**
 * Created by minha on 2018-01-11.
 */
data class StarResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : StarData
)