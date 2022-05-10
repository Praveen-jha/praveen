package com.example.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate :TextView? = null
    private var tvAgeInMinutes :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datepicker :Button = findViewById(R.id.datepicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        datepicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->

               Toast.makeText(this,
                   "Year was $year, month was ${month+1}, day of month was $dayOfMonth",
                   Toast.LENGTH_LONG).show()

               val selectedDate = "$dayOfMonth/${month+1}/$year"
               tvSelectedDate?.text = selectedDate


               val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
               val theDate = sdf.parse(selectedDate)

               theDate?.let {
                   val selectedDateInMinute = theDate.time / 60000

                   val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                   currentDate?.let {
                       val currentDateInMinutes = currentDate.time/ 60000

                       val differenceInMinutes = currentDateInMinutes - selectedDateInMinute


                       tvAgeInMinutes?.text = differenceInMinutes.toString()
                   }
               }
           },
           year,
           month,
           day
       )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

}