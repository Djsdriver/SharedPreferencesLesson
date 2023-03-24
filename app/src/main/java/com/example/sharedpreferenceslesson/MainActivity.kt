package com.example.sharedpreferenceslesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial

const val PRACTICUM_EXAMPLE_PREFERENCES = "practicum_example_preferences"
const val EDIT_TEXT_KEY = "key_for_edit_text"
const val SWITCH_KEY = "key_for_switch_key"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPrefs = getSharedPreferences(PRACTICUM_EXAMPLE_PREFERENCES, MODE_PRIVATE)

        val editText = findViewById<EditText>(R.id.editText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val restoreButton = findViewById<Button>(R.id.restoreButton)
        val themeSwitcher = findViewById<SwitchMaterial>(R.id.themeSwitcher)

        val isTurn=sharedPrefs.getBoolean(SWITCH_KEY,true)
        themeSwitcher.isChecked=isTurn
        fun switchTheme(darkThemeEnabled: Boolean) {
            AppCompatDelegate.setDefaultNightMode(
                if (darkThemeEnabled) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }

        themeSwitcher.setOnCheckedChangeListener { switcher, checked ->
            if (checked){
                sharedPrefs.edit().putBoolean(SWITCH_KEY,checked).apply()
                switchTheme(checked)
            }
            else{
                sharedPrefs.edit().putBoolean(SWITCH_KEY,checked).apply()
                switchTheme(checked)
            }
        }


        editText.setText(sharedPrefs.getString(EDIT_TEXT_KEY, ""))

        saveButton.setOnClickListener {
            sharedPrefs.edit()
                .putString(EDIT_TEXT_KEY, editText.editableText.toString())
                .apply()

            Toast.makeText(this, "Сохранили значение ${editText.editableText}", Toast.LENGTH_SHORT)
                .show()
        }

        restoreButton.setOnClickListener {

            editText.setText(sharedPrefs.getString(EDIT_TEXT_KEY, ""))
        }
    }
}