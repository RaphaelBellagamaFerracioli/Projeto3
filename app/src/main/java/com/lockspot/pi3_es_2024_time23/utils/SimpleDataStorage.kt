package com.lockspot.pi3_es_2024_time23.utils

import android.content.Context
import com.lockspot.pi3_es_2024_time23.model.Account

/***
 * Essa classe implementa uma série de facilidades
 * para armazenar dados localmente no Android por chave e valor.
 * No Android, desde muito cedo, existe um recurso chamado SharedPreferences
 * que serve para "guardar dados de preferências",
 * quando temos que guardar alguma informação pequena, interessante e não perigosa,
 * podemos usar esse recurso.
 * para fazer isso com maestria, criei essa classe para você compreender.
 *
 * Essa classe abstrai a complexidade e
 *
 * @author Prof. Mateus Dias - PUC-CAMPINAS
 * @since 2024
 */
class SimpleStorage(context: Context) {

    val sharedPreferences = context.getSharedPreferences("SIMPLE_STORAGE",
        Context.MODE_PRIVATE)

    /***
     * Obtendo os dados armazenados por chave e valor e montando um objeto
     * Account.
     */
    fun getUserAccountData(): Account {
        val name: String? = sharedPreferences.getString("account_name", null)
        val email: String? = sharedPreferences.getString("account_email", null)
        val cpf: String? = sharedPreferences.getString("account_cpf", null)
        val age: Int = sharedPreferences.getInt("account_age", 0)
        val cellphone: String? = sharedPreferences.getString("account_cellphone", null)
        val uid: String? = sharedPreferences.getString("account_uid", null)

        return Account(uid, name, email, cpf, age, cellphone, null)
    }

    /***
     * Desmontamos o objeto account em partes e guardamos as partes de uma vez.
     */
    fun storageUserAccount(account: Account){
        with (sharedPreferences.edit()) {
            putString("account_name", account.name)
            putString("account_email", account.email)
            putString("account_cpf", account.cpf)
            putInt("account_age", account.age)
            putString("account_cellphone", account.cellphone)
            putString("account_uid", account.uid)
            apply()
        }
    }


    fun clearUserAccount(){
        with (sharedPreferences.edit()) {
            putString("account_name", null)
            putString("account_email", null)
            putString("account_cpf", null)
            putInt("account_age", 0)
            putString("account_cellphone", null)
            putString("account_uid", null)
            apply()
        }
    }

}