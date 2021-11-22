
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menuorderingsystem.Item
import com.example.menuorderingsystem.R

class ItemsAdapter(private val orderItems: List<Item>): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    interface OnLongClickListener {
        fun onLongClick(itemView: View, position: Int)
    }

    lateinit var textView: TextView
    private lateinit var listener: OnLongClickListener

    fun setOnLongClickListener(listener: OnLongClickListener){
        this.listener = listener
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnLongClickListener{

        val itemNameTextView = itemView.findViewById<TextView>(R.id.tvOrderItem)

        init {
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    listener.onLongClick(itemView, position)
                }
                return@setOnLongClickListener true
            }
        }

        override fun onLongClick(view: View?): Boolean {
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_style, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        val item: Item = orderItems.get(position)
        val textView = holder.itemNameTextView
        textView.setText(item.name)
    }

    override fun getItemCount(): Int {
        return orderItems.size
    }

}

