package dgsw.hs.kr.gatchigachi

import dgsw.hs.kr.gatchigachi.model.Member
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2
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
            User2("Gun",""),
            User2("SungHwa",""),
            User2("TaeHyung",""),
            User2("Jin Hyuk","")
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
            User2("KimGun","Front"),
            User2("SungHwa","Front"),
            User2("Taehyung","Back"),
            User2("JinHyuk","Back"),
            User2("KimGun","Front"),
            User2("SungHwa","Front"),
            User2("Taehyung","Back"),
            User2("JinHyuk","Back")
    )

    val BestTrusterData = arrayListOf(
            BestTruster("KimGun",111),
            BestTruster("SungHwa",222),
            BestTruster("TaeHyung",333),
            BestTruster("JinHyuk",444),
            BestTruster("KimGun",111),
            BestTruster("SungHwa",222),
            BestTruster("TaeHyung",333),
            BestTruster("JinHyuk",444)
    )
}