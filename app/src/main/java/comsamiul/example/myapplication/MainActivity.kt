package comsamiul.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
       val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnFetch = findViewById<Button>(R.id.btnFetch)

        btnInsert.setOnClickListener{
            intent = Intent(this,Insert_data::class.java)
            startActivity(intent)
        }
    }
}