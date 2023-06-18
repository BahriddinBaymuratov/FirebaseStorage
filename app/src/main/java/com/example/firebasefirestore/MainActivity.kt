package com.example.firebasefirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebasefirestore.databinding.ActivityMainBinding
import com.example.firebasefirestore.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var firestore: FirebaseFirestore
    private val TAG = "@@@@@@"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        firestore = Firebase.firestore  // firestoredan obyekt olindi

        binding.btnAdd.setOnClickListener {
            // Create a new user with a first and last name
            val user = hashMapOf(
                "first" to "Ada",
                "last" to "Lovelace",
                "born" to 1815
            )
            val users = User("Android",7,"77")

            // Add a new document with a generated ID
            firestore.collection("users")
                .add(users)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        binding.readBtn.setOnClickListener {
//            // Create a new user with a first, middle, and last name
//            val user1 = hashMapOf(
//                "first" to "Alan",
//                "middle" to "Mathison",
//                "last" to "Turing",
//                "born" to 1912
//            )
//
//            // Add a new document with a generated ID
//            firestore.collection("users")
//                .add(user1)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }

            firestore.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} =>${document.data}")
                        val user = result.toObjects(User::class.java)
                        binding.tv.text = user.toString()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }

            binding.btnDelete.setOnClickListener {
                firestore.collection("users").document("ndQJtY2X6cBviPv6d23Q")
                    .delete()
                Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}