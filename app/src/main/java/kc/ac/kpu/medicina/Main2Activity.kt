package kc.ac.kpu.medicina

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    val dataList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val telAdapter = Main3Activity(this, dataList)
        listview1.adapter = telAdapter


        btnAdd.setOnClickListener {
            dataList.add(edtItem.text.toString())
            telAdapter?.notifyDataSetChanged()
        }

        listview1.setOnItemClickListener { parent, view, position, id ->
            val Intent = Intent(Intent.ACTION_DIAL, Uri.parse(("tel:" + dataList.get(position) )))
            startActivity(Intent)

        }


    }


}


