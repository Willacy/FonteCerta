package br.edu.ifba.fontecerta

import android.content.Context
import android.database.Cursor

class DAOPecas(context: Context) {
    private val dbHelper = DBHelper(context)

//    fun inserirPeca(nome: String, consumo: Int, categoria: String): Long {
//        val db = dbHelper.writableDatabase
//        val valor = ContentValues().apply {
//            put("nome", nome)
//            put("consumo", consumo)
//            put("categoria", categoria)
//        }
//        return db.insert("pecas", null, valor)
//    }


//    fun listarPecas(): List<Pair<String, Int>> {
//        val db = dbHelper.readableDatabase
//        val cursor: Cursor = db.rawQuery("SELECT nome, consumo FROM pecas", null)
//        val pecas = mutableListOf<Pair<String, Int>>()
//
//        while (cursor.moveToNext()) {
//            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
//            val consumo = cursor.getInt(cursor.getColumnIndexOrThrow("consumo"))
//            pecas.add(Pair(nome, consumo))
//        }
//        cursor.close()
//        return pecas
//    }

    fun listarPecasPorCategoria(categoria: String): List<Pair<String, Int>> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor =
            db.rawQuery("SELECT nome, consumo FROM pecas WHERE categoria = ? ORDER BY nome", arrayOf(categoria))
        val pecas = mutableListOf<Pair<String, Int>>()

        while (cursor.moveToNext()) {
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val consumo = cursor.getInt(cursor.getColumnIndexOrThrow("consumo"))
            pecas.add(Pair(nome, consumo))
        }
        cursor.close()
        return pecas
    }


}