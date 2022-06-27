package com.piaoyou.module.plugin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus

//import com.piaoyou.module.i_user.IUserPlugin
//import com.piaoyou.module.plugin_manager.PluginManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LiveEventBus.get().with("eventbusKey").observe(this, Observer {
            findViewById<TextView>(R.id.userName).setText(it.toString())
        })
//        LiveEventBus.get().with("eventbusKey").post(Model())
//        LiveEventBus.get().with("eventbusKey").post("AAA")
        LiveEventBus.get().with("eventbusKe11y").post(Model())
        findViewById<TextView>(R.id.userName).setOnClickListener(ClickListener())
    }

}

class Model

//INVOKEVIRTUAL com/jeremyliao/liveeventbus/LiveEventBus.with (Ljava/lang/String;)Lcom/jeremyliao/liveeventbus/LiveEventBus$Observable;
//LDC "AAA"
//INVOKEINTERFACE com/jeremyliao/liveeventbus/LiveEventBus$Observable.post (Ljava/lang/Object;)V (itf)