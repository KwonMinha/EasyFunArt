package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.SerialData

/**
 * Created by minha on 2018-01-13.
 */
data class SerialResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : SerialData
)