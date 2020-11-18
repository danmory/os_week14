package com.example.danilamoriakov_todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    protected ListView mTaskListView;
    protected ArrayList<String> taskList = new ArrayList<>();
    protected ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTaskListView = (ListView) findViewById (R.id.list_todo);
        mAdapter = new ArrayAdapter<String>(this, R.layout.todo_item, R.id.task_title,
                taskList);
        mTaskListView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu) ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_task) {
            Log.d("MainClass", "Add a new task");

            final EditText taskEditText = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Add a new task")
                    .setMessage("What do you want to do next?")
                    .setView(taskEditText)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String task = String.valueOf(taskEditText.getText());
                            Log.d("onOptionsItemSelected", "Task to add: " + task);
                            addItem(task);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void addItem(String itemText){
        taskList.add(itemText);
        mAdapter.notifyDataSetChanged();
    }
    private void removeItem(int index) {
        taskList.remove(index);
        mAdapter.notifyDataSetChanged() ;
    }

    public void deleteTask(View view) {
        int index = mTaskListView.indexOfChild((View) view.getParent());
        Log.d("deleteTask", String.valueOf(index));
        removeItem(index);
    }


}