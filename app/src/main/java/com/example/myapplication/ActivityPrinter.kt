package com.example.myapplication

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.myapplication.databinding.ListItemBinding

class ActivityPrinter(): AppCompatActivity() {

    private lateinit var binding: ListItemBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = this.intent
        var title = intent.getStringExtra("title")
        var listOfActivity = intent.getSerializableExtra("list") as ArrayList<OneActivity>
        setContentView(R.layout.list_item)
        binding = ListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findViewById<TextView>(R.id.pageTitle).text = title

        binding.listView.adapter=ActivityAdapter(this, listOfActivity)


        binding.listView.setOnItemLongClickListener { adapterView, view, i, l ->
            val listItem = binding.listView[i]
            val popupmenu = PopupMenu(this, listItem)
            popupmenu.menuInflater.inflate(R.menu.popup_menu, popupmenu.menu)

            popupmenu.setOnMenuItemClickListener {
                if(it.itemId == R.id.share){
                    var alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setTitle("Share")
                    alertDialog.setMessage("Do you want to share this activity with a friend?")
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            listItem.setBackgroundColor(Color.BLUE)
                            true
                        })


                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            true
                        })

                    alertDialog.show()

                }
                true
            }
            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popup)
                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(menu,true)
            }
            catch (e: Exception)
            {
                Log.d("error", e.toString())
            }
            finally {
                popupmenu.show()
            }

            true
        }
    }
}