import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menuorderingsystem.Item
import com.example.menuorderingsystem.R

class ItemsAdapter (private val menuItems: List<Item>): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    lateinit var context: Context
    lateinit var orders :ArrayList<Item>

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemNameTextView = itemView.findViewById<TextView>(R.id.tvMenuItem)
        val orderButton = itemView.findViewById<Button>(R.id.bttnAddToOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_style, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        val item: Item = menuItems.get(position)

        val textView = holder.itemNameTextView
        textView.setText(item.name)
        //button to be implemeneted
        val button = holder.orderButton
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

}