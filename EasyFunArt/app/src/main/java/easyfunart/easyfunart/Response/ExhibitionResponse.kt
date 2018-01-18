package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.ExhibitionData

/**
 * Created by JungWonhee on 2018-01-10.
 */
data class ExhibitionResponse (
    var status : String,
    var code : Int,
    var message : String,
    var data : ExhibitionData

)
