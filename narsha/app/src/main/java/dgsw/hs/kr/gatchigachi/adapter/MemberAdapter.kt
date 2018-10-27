package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.TeamMember
import dgsw.hs.kr.gatchigachi.network.Network
import kotlinx.android.synthetic.main.activity_main.*

class MemberAdapter (val context: Context, private val teamMembers: ArrayList<TeamMember>, val myDb:DBHelper) : BaseAdapter() {

    val network = Network()
    var code = 100
    val nextIntent = Intent(context, MainActivity::class.java)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val MemberView : View = LayoutInflater.from(context).inflate(R.layout.member_list_item, null)

        val MemberName : TextView = MemberView.findViewById(R.id.member_name)
        val MemberPosition : TextView = MemberView.findViewById(R.id.member_position)
        val Date : TextView = MemberView.findViewById(R.id.member_sign_date)

        Alram alram = alramList.get(position);

        alram

        val Member = teamMembers[position]
        val MemberProfile : ImageView = MemberView.findViewById(R.id.user_profile_member)


        Glide.with(MemberView)
                .load(myDb.selectUserById(Member.user_id!!.toInt())!!.profile)
                .apply(RequestOptions.circleCropTransform())
                .into(MemberProfile)

        MemberProfile.setOnClickListener {
            val nextIntent = Intent(context, MainActivity::class.java)
            context.startActivity(nextIntent)

        }

        MemberName.text = Member.name

        MemberPosition.text = Member.field

        Date.text = Member.enroll_date

        TextView.setText();

        MemberView.setOnClickListener {
            nextIntent.putExtra("userIdx", Member.user_id!!.toInt())
            context.startActivity(nextIntent)
        }



        return MemberView
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