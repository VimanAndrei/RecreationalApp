package com.example.myapplication

import android.app.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import android.widget.ImageButton
import android.widget.TextView

class ActivityAdapter(private val context : Activity,
                      private val arrayList : ArrayList<OneActivity>) :
    ArrayAdapter<OneActivity>(context, R.layout.list_item,arrayList) {


    val list = arrayListOf<Int>(0,0,0,0,0,0,0,0,0,0)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.one_item,null)

        val activity : TextView = view.findViewById(R.id.activity)
        val type : TextView = view.findViewById(R.id.type)
        val participants : TextView = view.findViewById(R.id.participants)
        val price : TextView = view.findViewById(R.id.price)
        val link : TextView = view.findViewById(R.id.link)
        val key : TextView =  view.findViewById(R.id.key)
        val accessibility : TextView = view.findViewById(R.id.accessibility)


        activity.text = arrayList[position].activity
        type .text =arrayList[position].type
        participants .text =arrayList[position].participants
        price .text =arrayList[position].price
        link .text =arrayList[position].link
        key.text =arrayList[position].key
        accessibility.text =arrayList[position].accessibility

        val buttonoff: ImageButton = view.findViewById(R.id.off)


        buttonoff.setOnClickListener {
            if(list[position] == 0){
                buttonoff.setImageResource(R.drawable.btn_star_big_on)
                list[position] = 1

            }
            else{
                buttonoff.setImageResource(R.drawable.btn_star_big_off)
                list[position] = 0
            }


        }



        return view
    }
}