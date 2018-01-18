package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.HomeData

/**
 * Created by minha on 2018-01-06.
 */
data class HomeResponse(
        var status : String,
        var code : Int,
        var message : String,
        var data : HomeData
)