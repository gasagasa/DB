package jp.ac.dendai.c.jtp.db

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Created by yuuki on 2017/05/24.
 */

class Game(private val activity: Activity): View.OnClickListener{
    val attack = activity.findViewById(R.id.attack) as Button
    val guard = activity.findViewById(R.id.guard) as Button
    val charge = activity.findViewById(R.id.charge) as Button

    val myCharge = activity.findViewById(R.id.myCharge) as TextView
    val enemyCharge = activity.findViewById(R.id.enemyCharge) as TextView

    val myChoice = activity.findViewById(R.id.myChoice) as TextView
    val enemyChoice = activity.findViewById(R.id.enemyChoice) as TextView

    val result = activity.findViewById(R.id.result) as TextView

    var myNum = 0
    var enemyNum = 0

    var mySelect = 0 //0がチャージ、1が攻撃、2が防御
    var enemySelect = 0

    init{
        attack.setOnClickListener(this)
        guard.setOnClickListener(this)
        charge.setOnClickListener(this)
        attack.setEnabled(false)
    }

    fun enemyturn(){
        if(myNum == 1 && enemyNum == 0){ //最初の時とか
            enemySelect = 0
            enemyNum += 1
            enemyCharge.setText("チャージ数:" + enemyNum)
        }
        else if(enemyNum == 0){ //こちらにチャージがあるが相手にチャージが無い場合
            enemySelect = (((Math.random() * 2).toInt())) + 1
            when(enemySelect){
                0 -> {
                    enemyNum += 1
                    enemyCharge.setText("チャージ数:" + enemyNum)
                }
                1 -> {
                    enemySelect = 2
                }
            }
        }
        else if(myNum == 1){ //こちらにチャージが無いが相手にチャージがある場合
            enemySelect = (((Math.random() * 2).toInt())) + 1
            when(enemySelect){
                0 -> {
                    enemyNum += 1
                    enemyCharge.setText("チャージ数:" + enemyNum)
                }
                1 -> {
                    enemyNum -= 1
                    enemyCharge.setText("チャージ数:" + enemyNum)
                }
            }
        }
        else{
            enemySelect = ((Math.random() * 3).toInt())
            when(enemySelect){
                0 -> {
                    enemyNum += 1
                    enemyCharge.setText("チャージ数:" + enemyNum)
                }
                1 -> {
                    enemyNum -= 1
                    enemyCharge.setText("チャージ数:" + enemyNum)
                }
            }
        }
        f5(enemySelect, enemyChoice)
    }

    fun judge(){
        if(mySelect == 1 && enemySelect == 0){
            //result.setText("あなたの勝ち！")
            finish(0)
        }
        else if(mySelect == 0 && enemySelect == 1){
            //result.setText("敵の勝ち！")
            finish(1)
        }
        else if(mySelect == 1 && enemySelect ==1){
            result.setText("相殺！")
        }
        else{
            result.setText("見合って見合って……")
        }
    }

    fun f5(select: Int, choice: TextView){
        when(select){
            0 -> {
                choice.setText("溜め！")
            }
            1 -> {
                choice.setText("攻撃！")
            }
            2 -> {
                choice.setText("防御！")
            }
        }
    }

    fun syokika(){
        myNum = 0
        enemyNum = 0
        myCharge.setText("チャージ数:" + myNum)
        enemyCharge.setText("チャージ数:" + enemyNum)
        myChoice.setText(R.string.default_my_choice)
        enemyChoice.setText(R.string.default_enemy_choice)
        result.setText(R.string.default_result)
        attack.setEnabled(false)
    }

    fun finish(result: Int){
        val ad = AlertDialog.Builder(activity)
        ad.setTitle("決着！")
        ad.setMessage(when(result){
            0 -> "あなたの勝ち！"
            1 -> "敵の勝ち！"
            else -> "これエラーだよ"
        })
        ad.setPositiveButton("OK", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                syokika()
            }
            })
        ad.show()
    }

    override fun onClick(v: View){
        when(v.getId()){
            R.id.charge -> {
                myNum += 1
                mySelect = 0
                myCharge.setText("チャージ数:" + myNum)
            }
            R.id.attack -> {
                myNum -= 1
                mySelect = 1
                myCharge.setText("チャージ数:" + myNum)
            }
            R.id.guard -> {
                mySelect = 2
            }
        }
        if(myNum == 0){
            attack.setEnabled(false)
        }
        else{
            attack.setEnabled(true)
        }
        enemyturn()
        f5(mySelect, myChoice)
        judge()
    }

}
