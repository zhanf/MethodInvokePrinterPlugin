package com.piaoyou.module.plugin

import android.view.View
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * @Author zhan
 * @Date 2022/5/15 5:42 下午
 * @Desc
 */
class ClickListener : View.OnClickListener {

    override fun onClick(v: View?) {
        LiveEventBus.get().with("eventbusKey").post("AAA")
        LiveEventBus.get().with("eventbusKey").post(Model())
    }
}