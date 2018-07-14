package dgsw.hs.kr.gatchigachi.model

data class TeamMember(
        val id: Long?,
        val team_id: Long?,
        val user_id: Long?,
        val name: String?,
        val field: String?,
        val inviter_id: Long?,
        val enroll_date: String?,
        val kickout_date: String?,
        val walkout_date:String?,
        val is_leader: Long?
)