package jp.ac.dendai.c.jtp.db

import android.app.Activity
import android.widget.Button
import android.widget.TextView

/**
 * Created by yuuki on 2017/05/27.
 */
class ResId(activity: Activity){
    val attack = activity.findViewById(R.id.attack) as Button
    val guard = activity.findViewById(R.id.guard) as Button
    val charge = activity.findViewById(R.id.charge) as Button

    val myCharge = activity.findViewById(R.id.myCharge) as TextView
    val enemyCharge = activity.findViewById(R.id.enemyCharge) as TextView

    val myChoice = activity.findViewById(R.id.myChoice) as TextView
    val enemyChoice = activity.findViewById(R.id.enemyChoice) as TextView

    val result = activity.findViewById(R.id.result) as TextView
}