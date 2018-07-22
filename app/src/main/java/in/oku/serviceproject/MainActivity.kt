package `in`.oku.serviceproject

import `in`.oku.serviceproject.ForeGroundService.ForeGroundService
import `in`.oku.serviceproject.ForeGroundService.MyForeGroundService
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val mIntentServiceClass = Intent(this@MainActivity,
//                IntentServiceClass::class.java)
//        startService(mIntentServiceClass)
//
//        val mServiceClass = Intent(this@MainActivity,
//                ServiceClass::class.java)
//        startService(mServiceClass)

//        val intent = Intent(this@MainActivity,
//                MyForeGroundService::class.java)
//        intent.action = MyForeGroundService.ACTION_START_FOREGROUND_SERVICE
//        startService(intent)

        val intent = Intent(this@MainActivity,
                ForeGroundService::class.java)
        intent.action = ForeGroundService.START_MUSIC_PLAYER
        startService(intent)

    }

}
