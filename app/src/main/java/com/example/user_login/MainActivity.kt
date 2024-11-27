package com.example.user_login

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var edittextname : EditText
    lateinit var sendbutton : Button
    lateinit var textviewname : TextView

    val Database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val refrence : DatabaseReference = Database.reference.child("users")
    val reference2 : DatabaseReference = Database.reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittextname = findViewById(R.id.edittextname)
        sendbutton = findViewById(R.id.sendbutton)
        textviewname = findViewById(R.id.textviewname)

        reference2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val realname : String = snapshot.child("users").child("username").value as String
                textviewname.text = realname
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        sendbutton.setOnClickListener{
            val username : String = edittextname.text.toString()

            refrence.child("username").setValue(username)
        }
    }
}