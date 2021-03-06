package devpaul.business.kidmath.dashboard.profile

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmath.Constants
import devpaul.business.kidmath.MainActivity
import devpaul.business.kidmath.R
import devpaul.business.kidmath.login.LoginActivity
import java.util.*

class PerfilActivity : AppCompatActivity() {

    var TAG = "PerfilActivity"

    var btnLogout : Button? = null

    private lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    private val db = Firebase.firestore

    var textfullname : TextView? = null
    var textemail : TextView? = null
    var textpassword : TextView? = null

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    var btnSumar : Button? = null
    var btnRestar : Button? = null
    var btnMultiplicar : Button? = null
    var btnDividir : Button? = null

    var textBestPoints : TextView? = null
    var textLastTry : TextView? = null
    var textlastTimePlayed : TextView? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        progressDialog = ProgressDialog(this)

        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()

        btnSumar = findViewById(R.id.sumar_data)
        btnSumar?.setOnClickListener{
            sumData()
        }
        btnRestar = findViewById(R.id.restar_data)
        btnRestar?.setOnClickListener{
            restData()
        }
        btnMultiplicar = findViewById(R.id.multiplicar_data)
        btnMultiplicar?.setOnClickListener{
            multData()
        }

        btnDividir = findViewById(R.id.div_data)
        btnDividir?.setOnClickListener{
            divData()
        }

        textBestPoints = findViewById(R.id.text_best_points)
        textLastTry = findViewById(R.id.text_last_points)
        textlastTimePlayed = findViewById(R.id.textview_lasttime_played)

        textfullname = findViewById(R.id.text_full_name)
        textemail = findViewById(R.id.text_email)
        textpassword = findViewById(R.id.text_password)

        btnLogout = findViewById(R.id.btn_salir)
        btnLogout?.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Deseas salir?")
                .setCancelText("Cancelar")
                .setConfirmText("Si")
                .setConfirmClickListener {
                    auth.signOut()
                    Toast.makeText(this, "Gracias por visitarnos", Toast.LENGTH_LONG).show()
                    val i = Intent(this, LoginActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Eliminar el historial de pantallas
                    startActivity(i)
                    it.dismiss()
                }
                .showCancelButton(true)
                .setCancelClickListener { sDialog ->
                    sDialog.cancel()
                }.show()

        }

        getUserInformation()

    }

    @SuppressLint("SetTextI18n")
    private fun getUserInformation(){

        val uiduser = auth.currentUser?.uid

        progressDialog!!.show()
        progressDialog?.setContentView(R.layout.charge_dialog)
        Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)

        val docRef =   db.collection(Constants.PATH_USERS).document(uiduser.toString())

        docRef.get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {

                        val name = document.getString("name")
                        val lastname = document.getString("lastname")
                        val email = document.getString("email")
                        val password = document.getString("password")

                        textfullname?.text = name.toString() +"\r"+lastname.toString()
                        textemail?.text = email.toString()
                        textpassword?.text = password.toString()
                        progressDialog?.dismiss()

                    } else {
                        textBestPoints?.text = "0"
                        textLastTry?.text = "0"
                        textlastTimePlayed?.text = "0000/00/00"
                        progressDialog?.dismiss()
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.exception)
                    progressDialog?.dismiss()
                }

            }
            .addOnFailureListener{
                it.printStackTrace()
                progressDialog?.dismiss()
                Log.w(TAG, "Error: Usuario no encontrado")
            }
    }

    private fun sumData(){

        val uiduser = auth.currentUser?.uid

        val docRef = db.collection(Constants.PATH_POINTS_SUM).document(uiduser.toString())

        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {

                    val bestPoints = document.getString("bestPoints")
                    val lastTry = document.getString("lastTry")
                    val lastTimePlayed = document.getString("lastTimePlayed")

                    textBestPoints?.text = bestPoints
                    textLastTry?.text = lastTry
                    textlastTimePlayed?.text = lastTimePlayed

                } else {
                    textBestPoints?.text = "0"
                    textLastTry?.text = "0"
                    textlastTimePlayed?.text = "0000/00/00"
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }

    }

    private fun restData(){

        val uiduser = auth.currentUser?.uid

        val docRef =  db.collection(Constants.PATH_POINTS_RES).document(uiduser.toString())

        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {

                    val bestPoints = document.getString("bestPoints")
                    val lastTry = document.getString("lastTry")
                    val lastTimePlayed = document.getString("lastTimePlayed")

                    textBestPoints?.text = bestPoints
                    textLastTry?.text = lastTry
                    textlastTimePlayed?.text = lastTimePlayed

                } else {
                    textBestPoints?.text = "0"
                    textLastTry?.text = "0"
                    textlastTimePlayed?.text = "0000/00/00"
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }

    }

    private fun multData(){

        val uiduser = auth.currentUser?.uid

        val docRef = db.collection(Constants.PATH_POINTS_MUL).document(uiduser.toString())

        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {

                    val bestPoints = document.getString("bestPoints")
                    val lastTry = document.getString("lastTry")
                    val lastTimePlayed = document.getString("lastTimePlayed")

                    textBestPoints?.text = bestPoints
                    textLastTry?.text = lastTry
                    textlastTimePlayed?.text = lastTimePlayed

                } else {
                    textBestPoints?.text = "0"
                    textLastTry?.text = "0"
                    textlastTimePlayed?.text = "0000/00/00"
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }

    }

    private fun divData(){

        val uiduser = auth.currentUser?.uid

        val docRef = db.collection(Constants.PATH_POINTS_DIV).document(uiduser.toString())

        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {

                    val bestPoints = document.getString("bestPoints")
                    val lastTry = document.getString("lastTry")
                    val lastTimePlayed = document.getString("lastTimePlayed")

                    textBestPoints?.text = bestPoints
                    textLastTry?.text = lastTry
                    textlastTimePlayed?.text = lastTimePlayed

                } else {
                    textBestPoints?.text = "0"
                    textLastTry?.text = "0"
                    textlastTimePlayed?.text = "0000/00/00"
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        textBestPoints?.text = "0"
        textLastTry?.text = "0"
        textlastTimePlayed?.text = "0000/00/00"
    }
}