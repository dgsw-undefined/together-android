package dgsw.hs.kr.gatchigachi

import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User

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
}