package dgsw.hs.kr.gatchigachi.model

data class Team(
        val id : Int?,
        val name : String?,
        val subject : String?,
        val area : String?,
        val docs : String?,
        val leader_id : Int?,
        val member_limit : Int?,
        val member_count : Int?
){
    var isMyTeam = 0
    lateinit var field :String
}



