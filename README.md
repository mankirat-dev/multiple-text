# multiple-text

# In project level gradle

allprojects {<br />
    repositories {
    
        //multiple-text
        maven { url 'https://jitpack.io' }
        
   }
}


# In app level gradle

dependencies {

    //multiple-text
    implementation 'com.github.mankirat-dev:multiple-text:1.0.2'
}


# Usage


findViewById<TextView>(R.id.tv_forgot)<br />
    .setSpanText(<br />
            SpanModel(text = "Login\n", color = Color.RED, sizeSp = 12f, typeface = Typeface.BOLD, isUnderline = true, bgColor = Color.BLACK, click = {<br />
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()<br />
            }),<br />
            SpanModel(text = "SignUp\n", color = Color.BLUE, sizeSp = 16f, typeface = Typeface.ITALIC, isUnderline = false, bgColor = Color.GREEN, click = {
                Toast.makeText(this, "SignUp", Toast.LENGTH_SHORT).show()<br />
            }),<br />
            SpanModel(text = "Forgot Password?", color = Color.YELLOW, sizeSp = 18f, typeface = Typeface.BOLD_ITALIC, click = {
                Toast.makeText(this, "forgot", Toast.LENGTH_SHORT).show()<br />
            }),<br />
    )
