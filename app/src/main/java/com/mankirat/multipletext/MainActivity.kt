package com.mankirat.multipletext

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.mankirat.multipletext.lib.SpanModel
import com.mankirat.multipletext.lib.setSpanText

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_forgot).setSpanText(
            SpanModel(text = "Login\n", color = Color.RED, sizeSp = 12f, typeface = Typeface.BOLD, isUnderline = true, bgColor = Color.BLACK, click = {
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            }),
            SpanModel(text = "SignUp\n", color = Color.BLUE, sizeSp = 16f, typeface = Typeface.ITALIC, isUnderline = false, bgColor = Color.GREEN, click = {
                Toast.makeText(this, "SignUp", Toast.LENGTH_SHORT).show()
            }),
            SpanModel(text = "Forgot Password?", color = Color.YELLOW, sizeSp = 18f, typeface = Typeface.BOLD_ITALIC, click = {
                Toast.makeText(this, "forgot", Toast.LENGTH_SHORT).show()
            }),
        )
    }
}