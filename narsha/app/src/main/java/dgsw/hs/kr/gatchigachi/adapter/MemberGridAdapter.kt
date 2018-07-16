package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.TeamMember
import dgsw.hs.kr.gatchigachi.network.Network

class MemberGridAdapter (val context: Context, private val teamMembers: ArrayList<TeamMember>,val myDb:DBHelper) : BaseAdapter() {

    val network = Network()
    var code = 100
    val nextIntent = Intent(context,MainActivity::class.java)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val memberView : View = LayoutInflater.from(context).inflate(R.layout.member_list_item, null)

        val memberName : TextView = memberView.findViewById(R.id.member_name)

        val memberPosition : TextView = memberView.findViewById(R.id.member_position)

        val date : TextView = memberView.findViewById(R.id.member_sign_date)

        val member = teamMembers[position]

        memberName.text = member.name

        memberPosition.text = member.field

        date.text = member.enroll_date

        memberView.setOnClickListener {
            nextIntent.putExtra("userIdx", member.user_id!!.toInt())
            context.startActivity(nextIntent)
        }

        return memberView
    }

    override fun getItem(position: Int): Any {
        return teamMembers[position]
    }

    override fun getItemId(position: Int): Long {
        return teamMembers[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return teamMembers.size
    }
}