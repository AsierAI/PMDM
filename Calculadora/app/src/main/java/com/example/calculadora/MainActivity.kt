package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin


class MainActivity : AppCompatActivity() , OnClickListener{

    private lateinit var binding : ActivityMainBinding
    var mem1 : String = "0"
    var mem2 : String = "0"
    var op : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mem1 = savedInstanceState?.getString("mem1") ?:"0"
        binding.texto.text=mem1


        binding.C.setOnClickListener(this)
        binding.igual.setOnClickListener(this)

        binding.cero.setOnClickListener(this)
        binding.uno.setOnClickListener(this)
        binding.dos.setOnClickListener(this)
        binding.tres.setOnClickListener(this)
        binding.cuatro.setOnClickListener(this)
        binding.cinco.setOnClickListener(this)
        binding.seis.setOnClickListener(this)
        binding.siete.setOnClickListener(this)
        binding.ocho.setOnClickListener(this)
        binding.nueve.setOnClickListener(this)

        binding.suma.setOnClickListener(this)
        binding.resta.setOnClickListener(this)
        binding.multiplica.setOnClickListener(this)
        binding.divide.setOnClickListener(this)

        binding.sin?.setOnClickListener(this)
        binding.cos?.setOnClickListener(this)
        binding.sq?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if ((
            (v?.id == binding.cero.id)
            ||(v?.id == binding.uno.id)
            ||(v?.id == binding.dos.id)
            ||(v?.id == binding.tres.id)
            ||(v?.id == binding.cuatro.id)
            ||(v?.id == binding.cinco.id)
            ||(v?.id == binding.seis.id)
            ||(v?.id == binding.siete.id)
            ||(v?.id == binding.ocho.id)
            ||(v?.id == binding.nueve.id)
            ) &&(mem1=="0")){
            mem1=(v as Button).text.toString()
            binding.texto.text = mem1
        }else if ((
            (v?.id == binding.cero.id)
            ||(v?.id == binding.uno.id)
            ||(v?.id == binding.dos.id)
            ||(v?.id == binding.tres.id)
            ||(v?.id == binding.cuatro.id)
            ||(v?.id == binding.cinco.id)
            ||(v?.id == binding.seis.id)
            ||(v?.id == binding.siete.id)
            ||(v?.id == binding.ocho.id)
            ||(v?.id == binding.nueve.id)
            ) &&(mem1!="0")){
            mem1 += (v as Button).text.toString()
        }

        if (v?.id == binding.C.id){
            mem1="0"
            mem2="0"
            op=0
        }

        if ((v?.id==binding.sq?.id)
            ||(v?.id==binding.sin?.id)
            ||(v?.id==binding.cos?.id)) {
            when (v?.id) {
                (binding.sq?.id) -> {
                    mem1 = (mem1.toDouble() * mem1.toDouble()).toString()
                }
                (binding.sin?.id) -> {
                    mem1 = sin(mem1.toDouble()).toString()
                }
                (binding.cos?.id) -> {
                    mem1 = cos(mem1.toDouble()).toString()
                }
            }
        }

        if ((v?.id==binding.suma.id)
            ||(v?.id==binding.resta.id)
            ||(v?.id==binding.divide.id)
            ||(v?.id==binding.multiplica.id)) {
            when (v?.id) {
                (binding.suma.id) -> {
                    mem2=mem1
                    mem1 = "0"
                    op=1
                }
                (binding.resta.id) -> {
                    mem2=mem1
                    mem1 = "0"
                    op=2
                }
                (binding.divide.id) -> {
                    mem2=mem1
                    mem1 = "0"
                    op=3
                }
                (binding.multiplica.id) -> {
                    mem2=mem1
                    mem1 = "0"
                    op=4
                }
            }
        }

        if (v?.id == binding.igual.id){
            when (op) {
                (1) -> {
                    var memtemp:String=mem1
                    mem1 = (mem1.toDouble() + mem2.toDouble()).toString()
                    mem2 =memtemp
                }

                (2) -> {
                    mem1 = (mem2.toDouble() - mem1.toDouble()).toString()
                }

                (3) -> {
                    mem1 = (mem2.toDouble() / mem1.toDouble()).toString()
                }

                (4) -> {
                    mem1 = (mem1.toDouble() * mem2.toDouble()).toString()
                }
            }
        }

        //mem1 = String.format("%.8f", mem1.toDouble())

        if ((mem1.toDouble()%1)==0.0){
            mem1=mem1.split(".").get(0)
        }

        binding.texto.text = mem1

        if((mem1 == "0") && mem2!="0"){
            binding.texto.text = mem2
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("mem1", mem1)

    }
}