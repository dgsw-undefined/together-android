package dgsw.hs.kr.gatchigachi.database

import android.provider.BaseColumns

object DBContract {

    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "user"

            val COLUMN_USER_ID = "userid"
            val COLUMN_NAME = "name"
        }
    }
    class TeamEntry : BaseColumns {

    }

}