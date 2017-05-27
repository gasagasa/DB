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

    val resId = ResId(activity) //リソースID管理クラス
    val renew = Renew() //テキスト更新クラス

    var myNum = 0
    var enemyNum = 0

    var mySelect = 0 //0がチャージ、1が攻撃、2が防御
    var enemySelect = 0

    init{
        resId.attack.setOnClickListener(this)
        resId.guard.setOnClickListener(this)
        resId.charge.setOnClickListener(this)
        resId.attack.setEnabled(false)
    }

    fun enemyturn(){
        if(myNum == 1 && enemyNum == 0){ //最初の時とか
            enemySelect = 0
            enemyNum += 1
            resId.enemyCharge.setText("チャージ数:" + enemyNum)
        }
        else if(enemyNum == 0){ //こちらにチャージがあるが相手にチャージが無い場合
            enemySelect = (((Math.random() * 2).toInt())) + 1
            when(enemySelect){
                0 -> {
                    enemyNum += 1
                    resId.enemyCharge.setText("チャージ数:" + enemyNum)
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
                    resId.enemyCharge.setText("チャージ数:" + enemyNum)
                }
                1 -> {
                    enemyNum -= 1
                    resId.enemyCharge.setText("チャージ数:" + enemyNum)
                }
            }
        }
        else{
            enemySelect = ((Math.random() * 3).toInt())
            when(enemySelect){
                0 -> {
                    enemyNum += 1
                    resId.enemyCharge.setText("チャージ数:" + enemyNum)
                }
                1 -> {
                    enemyNum -= 1
                    resId.enemyCharge.setText("チャージ数:" + enemyNum)
                }
            }
        }
        renew.choice(enemySelect, resId.enemyChoice)
    }


    fun judge(){
        if(mySelect == 1 && enemySelect == 0){
            finish(0)
        }
        else if(mySelect == 0 && enemySelect == 1){
            finish(1)
        }
        else if(mySelect == 1 && enemySelect ==1){
            resId.result.setText("相殺！")
        }
        else{
            resId.result.setText("見合って見合って……")
        }
    }

    fun syokika(){
        myNum = 0
        enemyNum = 0
        renew.syokika(resId)
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
                resId.myCharge.setText("チャージ数:" + myNum)
            }
            R.id.attack -> {
                myNum -= 1
                mySelect = 1
                resId.myCharge.setText("チャージ数:" + myNum)
            }
            R.id.guard -> {
                mySelect = 2
            }
        }
        if(myNum == 0){
            resId.attack.setEnabled(false)
        }
        else{
            resId.attack.setEnabled(true)
        }
        enemyturn()
        renew.choice(mySelect, resId.myChoice)
        judge()
    }

}
