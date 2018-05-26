package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2

class UserGridAdapter (val context: Context, val UserData: ArrayList<User2>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val UserView : View = LayoutInflater.from(context).inflate(R.layout.user_list_item, null)

        val UserName : TextView = UserView.findViewById(R.id.user_name)

        val user = UserData[position]

        UserName.setText(user.name)

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