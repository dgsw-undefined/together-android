package dgsw.hs.kr.gatchigachi

import dgsw.hs.kr.gatchigachi.model.Member
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2
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
            User2("Gun"),
            User2("SungHwa"),
            User2("TaeHyung"),
            User2("Jin Hyuk")
    )

    val MemberData = arrayListOf(
            Member("KimGun","Front","2015-05-03"),
            Member("SungHwa","Front","2015-05-03"),
            Member("Taehyung","Back","2015-05-03"),
            Member("JinHyuk","Back","2015-05-03")
    )
}