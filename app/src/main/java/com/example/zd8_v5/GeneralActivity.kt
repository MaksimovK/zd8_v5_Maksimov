package com.example.zd8_v5
import android.app.Activity
import android.app.NativeActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class GeneralActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskListAdapter: TaskListAdapter
    private val taskList: ArrayList<Task> = ArrayList()
    private lateinit var bottomNavigationView: BottomNavigationView

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_list -> {
                    supportFragmentManager.beginTransaction()
                    val intent = Intent(this, ListActivity::class.java)
                    startActivity(intent)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_alarm -> {
                    val intent = Intent(this, AlarmAcitvity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    val intent = Intent(this, NativeActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_calendar -> {
                    val intent = Intent(this, CalendarActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        recyclerView = findViewById(R.id.rvTaskList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskListAdapter = TaskListAdapter(taskList)
        recyclerView.adapter = taskListAdapter

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Отображение домашнего фрагмента при запуске активности
    }

    fun onAddTaskClicked(view: View) {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val task = data.getParcelableExtra<Task>("TASK")
            if (task != null) {
                addTask(task)
            }
        }
    }

    private fun addTask(task: Task) {
        taskList.add(task)
        taskListAdapter.notifyDataSetChanged()
    }

    companion object {
        private const val ADD_TASK_REQUEST_CODE = 1
    }

    fun ProfileButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}