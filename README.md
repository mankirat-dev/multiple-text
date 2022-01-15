# multiple-text

#In project level gradle

allprojects {
    repositories {
        //multiple-text
        maven { url 'https://jitpack.io' }
    }
}


#In app level gradle

dependencies {
    //multiple-text
    implementation 'com.github.mankirat-dev:stylish-text:1.0.4'
}
