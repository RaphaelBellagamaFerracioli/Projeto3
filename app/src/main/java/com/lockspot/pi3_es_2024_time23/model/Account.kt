package com.lockspot.pi3_es_2024_time23.model

class Account(
    var uid: String?,
    val name: String?,
    val email: String?,
    val cpf: String?,
    var age: Int,
    val cellphone: String?,
    var password: String?
) {
    class Validator(private val account: Account) {

        private fun isTheAgeValid(): Boolean {
            return account.age in 14..120
        }

        private fun isCellphoneValid(): Boolean {
            // Validação simplificada de número de celular
            return account.cellphone?.let {
                it.startsWith("+55") && it.length == 14
            } ?: false
        }

        private fun wasFormFilledOut(): Boolean {
            return if (account.name != null && account.email != null && account.password != null) {
                account.name.isNotEmpty() &&
                        account.age > 0 &&
                        account.email.isNotEmpty() &&
                        account.password!!.isNotEmpty() &&
                        account.cpf != null &&
                        account.cellphone != null
            } else {
                false
            }
        }

        fun isValid(): Boolean {
            return wasFormFilledOut() && isTheAgeValid() && isCellphoneValid()
        }
    }
}
