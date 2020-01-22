package kc.ac.kpu.medicina

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Main3Activity (val contexts: Context, val dataList: ArrayList<String>) : BaseAdapter() {

    override fun getView(position:  Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(contexts).inflate(R.layout.dialog,null)


        val hospitalTel= view.findViewById<TextView>(R.id.hospitalTel)
        val TeleImage = view.findViewById<ImageView>(R.id.imageview)


        hospitalTel.text = dataList.get(position)




        return view
    }



    override fun getItem(position: Int): Any {
        return  dataList[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dataList.size
    }
}

