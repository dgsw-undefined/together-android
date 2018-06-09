package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.DataService.SearchUserData
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.User2

class SearchPersonAdapter (val context: Context, val SearchUser : ArrayList<User2>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ResultView : View = LayoutInflater.from(context).inflate(R.layout.search_result_person, null)

        val UserName : TextView = ResultView.findViewById(R.id.user_name_search)
        val Userposition : TextView = ResultView.findViewById(R.id.user_position_search)

        val SearchUser = SearchUserData[position]


        UserName.setText(SearchUser.name)
        Userposition.setText(SearchUser.position)


        return ResultView
    }

    override fun getCount(): Int {
        return SearchUserData.size
    }

    override fun getItem(position: Int): Any {
        return SearchUserData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return SearchUserData.get(position).hashCode().toLong()
    }
}