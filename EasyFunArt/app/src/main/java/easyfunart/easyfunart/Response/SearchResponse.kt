package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.SearchStruckData

/**
 * Created by minha on 2018-01-11.
 */
data class SearchResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : SearchStruckData

)