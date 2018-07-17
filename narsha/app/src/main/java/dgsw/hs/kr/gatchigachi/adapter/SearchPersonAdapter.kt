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
import dgsw.hs.kr.gatchigachi.DataService.SearchUserData
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2

class SearchPersonAdapter (val context: Context, private val userList : ArrayList<User>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val resultView : View = LayoutInflater.from(context).inflate(R.layout.search_result_person, null)

        val userName : TextView = resultView.findViewById(R.id.user_name_search)
        val userPosition : TextView = resultView.findViewById(R.id.user_position_search)
        val userProfile : ImageView = resultView.findViewById(R.id.user_profile_search)
        val user = userList[position]


        Glide.with(resultView)
                .load(user.profile)
                .apply(RequestOptions.circleCropTransform())
                .into(userProfile)

        resultView.setOnClickListener {
            val nextIntent = Intent(context, MainActivity::class.java)
            nextIntent.putExtra("userIdx",user.idx!!.toInt())
            context.startActivity(nextIntent)
        }

        userName.text = user.name
        userPosition.text = user.position


        return resultView
    }

    override fun getCount(): Int {
        return userList.size
    }

    override fun getItem(position: Int): Any {
        return userList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return userList.get(position).hashCode().toLong()
    }
}