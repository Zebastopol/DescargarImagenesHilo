package com.example.descargarimageneshilo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    lateinit var content: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        content = findViewById(R.id.content)
        var btnDescargar: Button = findViewById(R.id.btnDescargar)

        btnDescargar.setOnClickListener {

        }

        inner class Descargar(var content: LinearLayout): AsyncTask<String, Int, ArrayList<Bitmap>>(){
            override fun onPreExecute() {
                progressBar.progress = 0
                progressBar.max = 100
                Toast.makeText(applicationContext, "Se inició la descarga", Toast.LENGTH_SHORT).show()
                super.onPreExecute()
            }
            override fun doInBackground(vararg p0: String?): ArrayList<Bitmap> {
                val listaImagenes: ArrayList<Bitmap> = ArrayList()

                for( i in 0..p0.size){
                    val url = URL(p0[i])
                    val input = url.openStream()
                    val bitmap: Bitmap = BitmapFactory.decodeStream(input)
                    listaImagenes.add(bitmap)
                    publishProgress((i+1)*100/p0.size)

                }
                return listaImagenes

            }
            override fun onPogressUpdate(vararg values: Int?) {
                progressBar.progress = values[0]!!
                super.onProgressUpdate(*values)
            }

            override fun onPostExecute(result: ArrayList<Bitmap>?){
                for(imagen in result!!){
                    content.addView(agregarImagen(imagen))
                }
            }

            private fun agregarImagen(bitmap: Bitmap): ImageView {
                val params = LinearLayout.LayoutParams(LinearLayout.)

            }
        }
    }
}