package dgsw.hs.kr.gatchigachi.preference

import android.content.Context

class Preference(context: Context){

    val PREFERENC_NAME = "SharedPreference"
    val TOKEN = "TOKEN"

    val preference = context.getSharedPreferences(PREFERENC_NAME, Context.MODE_PRIVATE)

    fun getToken() : String{
        return preference.getString(TOKEN,"")
    }

    fun setToken(token : String){
        val editor = preference.edit()
        editor.putString(TOKEN,token)
        editor.apply()
    }


}