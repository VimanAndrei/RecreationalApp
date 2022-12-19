package com.example.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.myapplication.databinding.ListItemBinding

class ActivityPrinter() : AppCompatActivity() {

    private lateinit var binding: ListItemBinding

    val urlList = arrayOf(
        "http://www.boredapi.com/api/activity?type=education",
        "http://www.boredapi.com/api/activity?type=recreational",
        "http://www.boredapi.com/api/activity?type=social",
        "http://www.boredapi.com/api/activity?type=charity",
        "http://www.boredapi.com/api/activity?type=cooking",
        "http://www.boredapi.com/api/activity?type=music",
        "http://www.boredapi.com/api/activity/"
    )

    val titleList = arrayListOf(
        "Education",
        "Recreational",
        "Social",
        "Charity",
        "Cooking",
        "Music",
        "Random"
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = this.intent
        var title = intent.getStringExtra("title")
        var listOfActivity = intent.getSerializableExtra("list") as ArrayList<OneActivity>
        setContentView(R.layout.list_item)
        binding = ListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.activity_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter.setNotifyOnChange(true)
            spinner.adapter = adapter
        }
        spinner.setSelection(titleList.indexOf(title))

       var selectBtn = findViewById<Button>(R.id.select)

        selectBtn.setOnClickListener {
            var i = titleList.indexOf(spinner.selectedItem.toString())
            var ps = ProgressDialog.show(this, "Loading", "Wait while loading...")
            val handler = Handler()
            var classApi = ApiCall(urlList[i])
            classApi.callFunction()

            handler.postDelayed(Runnable {
                ps.dismiss()
                var list = classApi.getListActivity()
                println(list.size)
                val intent = Intent(this, ActivityPrinter::class.java)
                intent.putExtra("title", titleList[i])
                intent.putExtra("list", list)
                startActivity(intent)

            }, 1500) // 3000 milliseconds delay
        }

        binding.listView.adapter = ActivityAdapter(this, listOfActivity)


        binding.listView.setOnItemLongClickListener { adapterView, view, i, l ->
            val listItem = binding.listView[i]
            val popupmenu = PopupMenu(this, listItem)
            popupmenu.menuInflater.inflate(R.menu.popup_menu, popupmenu.menu)

            popupmenu.setOnMenuItemClickListener {
                if (it.itemId == R.id.share) {
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
                    .invoke(menu, true)
            } catch (e: Exception) {
                Log.d("error", e.toString())
            } finally {
                popupmenu.show()
            }
            true
        }
    }
}