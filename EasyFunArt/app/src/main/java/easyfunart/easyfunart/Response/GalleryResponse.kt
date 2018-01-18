package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.GalleryData

/**
 * Created by minha on 2018-01-12.
 */
data class GalleryResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : GalleryData
)