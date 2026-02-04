package com.example.mynfcemulatorapp

import android.content.SharedPreferences
import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import androidx.preference.PreferenceManager


class MyHceService : HostApduService() {

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null) return hexStringToByteArray("6F00") // Hiba kód

        val hexCommand = commandApdu.joinToString("") { "%02X".format(it) }
        val sharedPreferences: SharedPreferences? =
            PreferenceManager.getDefaultSharedPreferences(this)
        val cardId = sharedPreferences!!.getString("cardid", "")

        // Ha az olvasó a "SELECT AID" parancsot küldi
        return if (hexCommand.startsWith("00A40400") && !cardId!!.isEmpty()) {
            println("Olvasó csatlakozva, azonosító küldése...")

            // Válasz: Siker (9000) + az egyedi adatod (például: 123456)
            // Formátum: [Adat] + [Státusz szavak: 90 00]
            hexStringToByteArray(cardId + "9000")
        } else {
            hexStringToByteArray("6F00") // Ismeretlen parancs
        }
    }

    override fun onDeactivated(reason: Int) {
        // Kapcsolat megszakadt (pl. elvették a telefont az olvasótól)
    }

    private fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        for (i in 0 until len step 2) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
        }
        return data
    }
}