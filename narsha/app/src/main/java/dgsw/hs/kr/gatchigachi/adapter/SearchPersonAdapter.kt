package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dgsw.hs.kr.gatchigachi.DataService.SearchUserData
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.model.User2
import java.util.*

class SearchPersonAdapter (val context: Context, private var userList : ArrayList<User>, val type:Int, private val teamId:Int?) : BaseAdapter(),Filterable {

    var tempArrayList = ArrayList<User>()
    var tempList = ArrayList<User>()
    var valueFilter: ValueFilter? = null


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
            if(type == 1){
                nextIntent.putExtra("teamId",teamId)
            }
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

    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }


    inner class ValueFilter : Filter() {

        protected override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            if (constraint != null && constraint.length > 0) {
                val filterList = ArrayList<User>()
                for (i in 0 until userList.size) {
                    if (userList[i].name.toUpperCase()
                                    .contains(constraint.toString().toUpperCase())) {

                        val user = userList[i]
                        filterList.add(user)
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = userList.size
                results.values = userList
            }
            return results

        }

        protected override fun publishResults(constraint: CharSequence,
                                              results: FilterResults) {
            userList = results.values as ArrayList<User>
            notifyDataSetChanged()
        }

    }

}