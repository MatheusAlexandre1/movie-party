package com.bluedot.core.base

import com.bluedot.core.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.orhanobut.hawk.Hawk

object FirebaseUtils {
    fun getMaintenanceStatus() {
        val firebaseReference = FirebaseDatabase.getInstance().reference.child("maintenance")
        firebaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val inMaintenance = snapshot.child("value").value.toString()
                Hawk.put(Constants.IN_MAINTENANCE, inMaintenance.toBoolean())
            }

            override fun onCancelled(error: DatabaseError) {
                Hawk.put(Constants.FIREBASE_ERROR, true)
            }
        })
    }
}