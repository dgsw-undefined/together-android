package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.id.user_profile_main
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2
import kotlinx.android.synthetic.main.user_list_item.*

class UserGridAdapter (val context: Context, val UserData: ArrayList<User2>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val UserView : View = LayoutInflater.from(context).inflate(R.layout.user_list_item, null)

        val UserName : TextView = UserView.findViewById(R.id.user_name)
        val UserPosition : TextView = UserView.findViewById(R.id.user_position)
        var UserProfileMain : ImageView = UserView.findViewById(R.id.user_profile_main)
        val user = UserData[position]

        UserName.setText(user.name)
        UserPosition.setText(user.position)

        //      이미지 똥글배이로 맹글기
        UserProfileMain.background =  ShapeDrawable(OvalShape())
        UserProfileMain.clipToOutline = true

        return UserView
    }

    override fun getItem(position: Int): Any {
        return UserData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  0
    }



    override fun getCount(): Int {
        return UserData.size
    }
}