package uz.gita.a2048.utils

import uz.gita.a2048.R

class BackgroundUtil {
    private val backgroundMap = HashMap<Int, Int>()
    private val backgroundText = HashMap<Int, Int>()

    init {
        loadMap()
    }

    private fun loadMap() {
        backgroundMap[0] = R.drawable.empty_corner
        backgroundMap[2] = R.drawable.bg_corner2
        backgroundMap[4] = R.drawable.bg_corner4
        backgroundMap[8] = R.drawable.bg_corner8
        backgroundMap[16] = R.drawable.bg_corner16
        backgroundMap[32] = R.drawable.bg_corner32
        backgroundMap[64] = R.drawable.bg_corner64
        backgroundMap[128] = R.drawable.bg_corner128
        backgroundMap[256] = R.drawable.bg_corner256
        backgroundMap[512] = R.drawable.bg_corner512
        backgroundMap[1024] = R.drawable.bg_corner1024
        backgroundMap[2048] = R.drawable.bg_corner2048
        backgroundMap[4096] = R.drawable.bg_corner4096
        backgroundMap[8192] = R.drawable.bg_corner8192
        backgroundMap[16384] = R.drawable.bg_corner16384

        backgroundText[0] = R.color.white
        backgroundText[8] = R.color.white
        backgroundText[16] = R.color.white
        backgroundText[32] = R.color.white
        backgroundText[64] = R.color.white
        backgroundText[128] = R.color.white
        backgroundText[256] = R.color.white
        backgroundText[512] = R.color.white
        backgroundText[1024] = R.color.white
        backgroundText[2048] = R.color.white
        backgroundText[4096] = R.color.white
        backgroundText[8192] = R.color.white
        backgroundText[16384] = R.color.white
        backgroundText[0] = R.color.white
        backgroundText[2] = R.color.qoraroq
        backgroundText[4] = R.color.qoraroq
    }

    fun colorByCount(amount: Int): Int {
        return backgroundMap.getOrDefault(amount, R.drawable.empty_corner)
    }

    fun textColorByCount(i: Int): Int {
        return backgroundText.getOrDefault(i, R.color.white)
    }
}