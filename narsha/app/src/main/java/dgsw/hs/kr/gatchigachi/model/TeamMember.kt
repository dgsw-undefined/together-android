package dgsw.hs.kr.gatchigachi.model

data class TeamMember(
        val id : Long,
        val teamId : Long,
        val userIdx : Long,
        val field : String,
        val inviterId : Long,
        val isLeader : Int
)