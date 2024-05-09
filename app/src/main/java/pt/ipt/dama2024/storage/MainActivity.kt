package pt.ipt.dama2024.storage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btL1: Button
    private lateinit var btS1: Button
    private lateinit var txt1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Shared Preference
        txt1 = findViewById(R.id.editText1)
        btS1 = findViewById(R.id.btSave1)
        btS1.setOnClickListener {
            writeSharedPrefenceText()
            hideKeyboard(it)
        }
        btL1 = findViewById(R.id.btLoad1)
        btL1.setOnClickListener { readSharedPreferenceData() }

    }


    /**
     * write some text to Shared Preferences
     */
    private fun writeSharedPrefenceText() {
        // escrita usando o sharedPreferences
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("SPREFERENCES", txt1.text.toString())
        // editor.putInt("NUMERO", 44)  // <--- Exemplo do uso de números
        editor.commit()  // poderia, tb, ser utilizado:    editor.apply()
        Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()
    }

    /**
     * read data stored at Shared Preferences
     */
    private fun readSharedPreferenceData() {
        // leitura usando o sharedPreferences
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val texto = sharedPreferences.getString("SPREFERENCES", "Sem Texto")
        // val numero = sharedPreferences.getInt("NUMERO", 0)  // <--- Exemplo do uso de números
        Toast.makeText(this, "O texto lido é: $texto", Toast.LENGTH_LONG).show()
    }

    /**
     * hide the keyboard
     */
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}