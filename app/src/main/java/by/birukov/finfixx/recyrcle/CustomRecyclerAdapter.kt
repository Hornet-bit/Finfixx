package by.birukov.finfixx.recyrcle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.birukov.finfixx.R
import by.birukov.finfixx.models.FanficModel

class CustomRecyclerAdapter(private val names: List<FanficModel>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
//    var onElemClick : OnElementClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = names[position].title
        holder.description?.text = names[position].description
        holder.date?.text = names[position].date
        holder.author?.text = names[position].author
        holder.fandom?.text = names[position].fandom
    }

    override fun getItemCount() = names.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var description: TextView? = null
        var date: TextView? = null
        var author: TextView? = null
        var fandom: TextView? = null
//        var onElementL :OnElementClick? = onElement

        init {
            title = itemView.findViewById(R.id.tv_title)
            description = itemView.findViewById(R.id.tv_description)
            date = itemView.findViewById(R.id.tv_date)
            author = itemView.findViewById(R.id.tv_author)
            fandom = itemView.findViewById(R.id.tv_fandom)
//            itemView.setOnClickListener(
//                this
//            )
        }

//        override fun onClick(p0: View?) {
//
//            onElementL?.onClick(adapterPosition)
//        }
    }



    interface OnElementClick{
        fun onClick(position: Int) {

        }
    }
}