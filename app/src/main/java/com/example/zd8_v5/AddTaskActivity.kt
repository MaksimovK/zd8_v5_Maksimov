package com.example.zd8_v5

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class AddTaskActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var etDescription: EditText
    private var dateFormatter: SimpleDateFormat? = null
    private lateinit var timeFormatter: SimpleDateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        etTitle = findViewById(R.id.etTitle)
        etDate = findViewById(R.id.etDate)
        etTime = findViewById(R.id.etTime)
        etDescription = findViewById(R.id.etDescription)

        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    }

    fun onSaveTaskClicked(view: View) {
        val title = etTitle.text.toString()
        val date = etDate.text.toString()
        val time = etTime.text.toString()
        val description = etDescription.text.toString()

        val task = Task(title, date, time, description)

        val resultIntent = Intent()
        resultIntent.putExtra("TASK", task)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()

        setResult(RESULT_OK)
        finish()
    }

    fun DateClick(view: View) {
        val newCalendar: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                val selectedDate: Calendar = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val date: String = dateFormatter!!.format(selectedDate.getTime())
                etDate.setText(date)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    fun TimeClick(view: View) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                selectedTime.set(Calendar.MINUTE, selectedMinute)
                val time = timeFormatter.format(selectedTime.time)
                etTime.setText(time)
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
    }

    fun BackButton(view: View) {
        val intent = Intent(this, GeneralActivity::class.java)
        startActivity(intent)
    }
}