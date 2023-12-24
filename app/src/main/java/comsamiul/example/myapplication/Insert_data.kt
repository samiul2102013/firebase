package comsamiul.example.myapplication

import android.annotation.SuppressLint
import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Insert_data : AppCompatActivity() {

    private lateinit var etName : EditText
    private lateinit var etAge : EditText
    private lateinit var etSalary : EditText
    private lateinit var btnSaveData : Button

    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etSalary = findViewById(R.id.etSalary)
        btnSaveData = findViewById(R.id.btnSaveData)

        dbRef = FirebaseDatabase.getInstance().getReference("Employee")

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }

    }
    private fun saveEmployeeData(){
        //getting values
        val epName = etName.text.toString()
        val epAge = etAge.text.toString()
        val epSalary = etSalary.text.toString()

        if(epName.isEmpty()){
            etName.error = "please enter name"
        }

        if(epAge.isEmpty()){
            etAge.error = "please enter age"
        }

        if(epSalary.isEmpty()){
            etSalary.error = "please enter salary"
        }

        val epId = dbRef.push().key!!

        val employee = EmployeeModel(epId, epName, epAge, epSalary )

        dbRef.child(epId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inseted succesfully", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etAge.text.clear()
                etSalary.text.clear()

            }.addOnFailureListener{ err->
                Toast.makeText(this, "Error ${err.message}" , Toast.LENGTH_LONG).show()
            }
    }
}