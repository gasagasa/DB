package jp.ac.dendai.c.jtp.db

import android.widget.TextView

/**
 * Created by yuuki on 2017/05/27.
 */
class Renew {
    fun choice(select: Int, choice: TextView){
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

    fun syokika(resId: ResId){
        resId.myCharge.setText("チャージ数:0")
        resId.enemyCharge.setText("チャージ数:0")
        resId.myChoice.setText(R.string.default_my_choice)
        resId.enemyChoice.setText(R.string.default_enemy_choice)
        resId.result.setText(R.string.default_result)
        resId.attack.setEnabled(false)
    }
}