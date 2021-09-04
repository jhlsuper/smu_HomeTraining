import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mlkit_pose.R
import com.example.mlkit_pose.fragment.GuideSportsFragment

open class GuideBookRecyclerAdapter(
        val guidelist: GuideBookSportsList, // 이미 리스트가 존재해서 <> 할 필요 X
        val inflater: LayoutInflater
//    val activity: Activity
        // Intent 사용 때 필요, 그리고 얘가 있기 때문에 startActivity가 가능
) : RecyclerView.Adapter<GuideBookRecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(v: View, guidelist: GuideBookSportsList, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sportName: TextView = itemView.findViewById(R.id.person_name)

//            itemView.setOnClickListener {
//                val result = guidelist.GuideList.get(adapterPosition).name
//                Log.d("result", result)
//            }

//            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frameLayout, GuideSportsFragment(), "guideClick")
//                .addToBackStack("guideClick")
//                .commit()

        fun bind() {
            sportName.setText(guidelist.GuideList.get(position).name)

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, guidelist, pos)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.sports_items, parent, false)
        return ViewHolder(view)
        // 중요한 것은 뷰를 뷰홀더에 담아서 리턴한다는 것! 그래야 재활용 가능

    }

    override fun getItemCount(): Int {
        return guidelist.GuideList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.sportName.setText(guidelist.GuideList.get(position).name)
        holder.bind()
    }

    companion object {
        val array_name = arrayListOf<String>() // 리스트 객체 만듦
    }

}

class GuideBookSportsList() { // 운동 리스트

    val GuideList = ArrayList<GuideSports>()
    fun addSports(guideSports: GuideSports) {
        GuideList.add(guideSports)
    }
// 운동 리스트에 운동을 추가

}

class GuideSports(var name: String) { // 운동 이름 하나

}
