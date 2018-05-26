package dgsw.hs.kr.gatchigachi

import dgsw.hs.kr.gatchigachi.model.*
import java.util.*

object DataService {
    val teamData = arrayListOf(
            Team("undefined"),
            Team("naver"),
            Team("google"),
            Team("name"),
            Team("회덮밥"),
            Team("해적왕원준갓"),
            Team("레알마드리드"),
            Team("바르셀로나"),
            Team("아 누구야")
    )

    val UserData = arrayListOf(
            User("Gun"),
            User("SungHwa"),
            User("TaeHyung"),
            User("Jin Hyuk")
    )

    val MemberData = arrayListOf(
            Member("KimGun","Front","2015-05-03"),
            Member("SungHwa","Front","2015-05-03"),
            Member("Taehyung","Back","2015-05-03"),
            Member("JinHyuk","Back","2015-05-03")
    )

    val SearchTeamData = arrayListOf(
            TeamSearch("Undefined", "2015-05-03"),
            TeamSearch("아.. 누구야", "2015_05-03"),
            TeamSearch("바르셀로나", "2015-05-03"),
            TeamSearch("Google", "2015-05-03"),
            TeamSearch("레알마드리드", "2015-05-03"),
            TeamSearch("Undefined", "2015-05-03"),
            TeamSearch("아.. 누구야", "2015_05-03"),
            TeamSearch("바르셀로나", "2015-05-03"),
            TeamSearch("돈까스", "2015-05-03")
    )

    val SearchUserData = arrayListOf(
            UserSearch("KimGun","Front"),
            UserSearch("SungHwa","Front"),
            UserSearch("Taehyung","Back"),
            UserSearch("JinHyuk","Back"),
            UserSearch("KimGun","Front"),
            UserSearch("SungHwa","Front"),
            UserSearch("Taehyung","Back"),
            UserSearch("JinHyuk","Back")
    )
}