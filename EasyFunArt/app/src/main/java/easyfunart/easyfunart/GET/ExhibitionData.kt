package easyfunart.easyfunart.GET

/**
 * Created by JungWonhee on 2018-01-10.
 */
data class ExhibitionData (
        var userInfo : ExhibitionUserInfo,
        var exhibition : ExhibitionInfoData,
        var selectedHashtag : ArrayList<String>,
        var unSelectedHashtag : ArrayList<String>,
        var authorResult : ExhibitionAuthorInfo

)
