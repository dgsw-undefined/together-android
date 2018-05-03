package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.Member
import kotlinx.android.synthetic.main.activity_detail_team.view.*

class MemberGridAdapter (val context: Context, val MemberData: ArrayList<Member>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val memberView : View = LayoutInflater.from(context).inflate(R.layout.member_list_item, null)

        val memberName : TextView = memberView.findViewById(R.id.member_name);

        val memberPosition : TextView = memberView.findViewById(R.id.member_position)

        val date : TextView = memberView.findViewById(R.id.member_sign_date)

        val Member = MemberData[position]

        memberName.setText(Member.name)

        memberPosition.setText(Member.user_position)

        date.setText(Member.date)

        return memberView
    }

    override fun getItem(position: Int): Any {
        return MemberData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return MemberData.get(position).hashCode().toLong()
    }

    override fun getCount(): Int {
        return MemberData.size
    }
}