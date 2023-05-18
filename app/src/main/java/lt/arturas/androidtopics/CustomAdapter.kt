package lt.arturas.androidtopics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import lt.arturas.androidtopics.databinding.ItemBinding

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Item>()
    var maxId: Int = list.maxBy {item -> item.id}.id
    private set

    /*
    fun add(item: Item){
        list.add(item)
        notifyDataSetChanged()
    }
     */

    fun add(vararg item: Item){
        list.addAll(item)
        notifyDataSetChanged()
    }

    fun add(items: List<Item>){
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun update(index: Int, item: Item){
        list.set(index,item)
        notifyDataSetChanged()
    }

    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }

    fun remove(vararg item: Item){
        list.removeAll(item)
        notifyDataSetChanged()
    }

    fun remove(items: List<Item>){
        list.removeAll(items)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //val view = convertView ?: inflater.inflate(R.layout.item,parent, false)
        var view = convertView
        val binding: ItemBinding
        if(view == null){
            binding = ItemBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        }else{
            binding = view.tag as ItemBinding
        }

        //view.findViewById<TextView>(R.id.idTextView).text = list[position].id.toString()
        //view.findViewById<TextView>(R.id.text01TextView).text = list[position].text01
        //view.findViewById<TextView>(R.id.text02TextView).text = list[position].text02

        binding.idTextView.text = list[position].id.toString()
        binding.text01TextView.text = list[position].text01
        binding.text02TextView.text = list[position].text02
        binding.creationDateTextView.text = list[position].creationDate.toString()
        binding.updateDateTextView.text = list[position].updateDate.toString()


        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size
}