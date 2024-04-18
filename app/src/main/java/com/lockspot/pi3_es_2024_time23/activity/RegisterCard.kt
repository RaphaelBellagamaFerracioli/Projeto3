package com.lockspot.pi3_es_2024_time23.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.lockspot.pi3_es_2024_time23.R

class RegisterCard : AppCompatActivity() {

    private lateinit var cardHolderName: EditText
    private lateinit var cardNumber: EditText
    private lateinit var cardExpiryDate: EditText
    private lateinit var cardCvv: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_card)

        cardHolderName = findViewById(R.id.editTextCardHolderName)
        cardNumber = findViewById(R.id.editTextCardNumber)
        cardExpiryDate = findViewById(R.id.editTextCardExpiry)
        cardCvv = findViewById(R.id.editTextCvv)
        submitButton = findViewById(R.id.buttonAddCard)

        submitButton.setOnClickListener { submitCardDetails() }
    }

    private fun submitCardDetails() {
        if (validateCardDetails()) {
            addCardToFirestore()
        } else {
            Snackbar.make(submitButton, "Please enter valid card details", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun addCardToFirestore() {
        val cardData = hashMapOf(
            "cardHolderName" to cardHolderName.text.toString().trim(),
            "cardNumber" to cardNumber.text.toString().trim(),
            "cardExpiryDate" to cardExpiryDate.text.toString().trim(),
            "cardCvv" to cardCvv.text.toString().trim()
        )

        val db = FirebaseFirestore.getInstance()
        db.collection("cards").add(cardData)
            .addOnSuccessListener { documentReference ->
                Snackbar.make(submitButton, "Card added with ID: ${documentReference.id}", Snackbar.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Snackbar.make(submitButton, "Error adding card: ${e.message}", Snackbar.LENGTH_LONG).show()
            }
    }

    private fun validateCardDetails(): Boolean {
        // Implement proper validation here
        return cardHolderName.text.isNotEmpty() &&
                cardNumber.text.length == 16 &&
                cardExpiryDate.text.length == 5 &&
                cardCvv.text.length == 3
    }
}
