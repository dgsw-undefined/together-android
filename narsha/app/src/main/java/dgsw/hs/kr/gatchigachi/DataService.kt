package dgsw.hs.kr.gatchigachi

import dgsw.hs.kr.gatchigachi.model.Member
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2
import dgsw.hs.kr.gatchigachi.model.*
import java.util.*

object DataService {
    val teamData = arrayListOf(
            Team2("undefined"),
            Team2("naver"),
            Team2("google"),
            Team2("name"),
            Team2("회덮밥"),
            Team2("해적왕원준갓"),
            Team2("레알마드리드"),
            Team2("바르셀로나"),
            Team2("아 누구야")
    )

    val UserData = arrayListOf(
            User2("Gun","android-client"),
            User2("SungHwa","android-client"),
            User2("TaeHyung","android-server"),
            User2("Jin Hyuk","android-server"),
            User2("Gun","android-client"),
            User2("SungHwa","android-client"),
            User2("TaeHyung","android-server"),
            User2("Jin Hyuk","android-server"),
            User2("Gun","android-client"),
            User2("SungHwa","android-client"),
            User2("TaeHyung","android-server"),
            User2("Jin Hyuk","android-server")
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

    val AlarmData = arrayListOf(
            Alarm(1, "서진혁님이 나를 Trust 했습니다"),
            Alarm(1,"박태형님이 Undefined 팀을 떠났습니다"),
            Alarm(1,"박태형님이 Undefined 팀과 함께하게 되었습니다."),
            Alarm(1,"Undefined 팀 가입 신청이 거절되었습니다"),
            Alarm(2, "Undefined 팀에 초대되었습니다."),
            Alarm(2, "김건님이 Undefined 팀과 함께하고 싶어합니다."),
            Alarm(2, "서진혁님이 Undefined 팀과 함께하고 싶어합니다."),
            Alarm(2, "정성화님이 Undefined 팀과 함께하고 싶어합니다."),
            Alarm(2, "박태형님이 Undefined 팀과 함께하고 싶어합니다.")

    )
}