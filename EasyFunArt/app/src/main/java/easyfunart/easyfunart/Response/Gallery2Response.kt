package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.Gallery2Data

/**
 * Created by minha on 2018-01-12.
 */
data class Gallery2Response (
        var data : ArrayList<Gallery2Data>,
        var code : Int,
        var message : String,
        var status : String

)