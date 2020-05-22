package com.totallytim.randomreminders.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R

class SettingsFragment : Fragment() {

    // Medium:
    // TODO: add settings
    // TODO: add settings tools
    // High:
    // TODO: add hours to trigger alarms

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_settings)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//
//        // https://stackoverflow.com/a/16755282
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//
////        // Not sure why I need to declare then hide, but here it is
////        FloatingActionButton fab = findViewById(R.id.fab);
////        fab.setVisibility(View.GONE);
//    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // TODO: binding for home
//        val binding: HomeBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_game_won, container, false)

//        binding.nextMatchButton.setOnClickListener { view: View ->
//            view.findNavController().navigate(
//                GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
//        }

//        val args = GameWonFragmentArgs.fromBundle(arguments!!)
//        Toast.makeText(context,
//            "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
//            Toast.LENGTH_LONG).show()
//
//        setHasOptionsMenu(true)

//        return binding.root
        return null
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val myIntent = Intent(applicationContext, MainActivity::class.java)
//        startActivityForResult(myIntent, 0)
//        return true
//    }

    //    public void clickAnyButton(View view) {
    fun onButtonClick(view: View) {
        println("Logged button click")
        when (view.id) {
            R.id.button2 -> {
                println("Logged confirm prompt")
//                confirmDialogDemo()
            }
        }
    }

//    private fun confirmDialogDemo() {
//        // Popup (Y/N)
//        val builder =
//            AlertDialog.Builder(this)
//        builder.setTitle("Confirm dialog demo!")
//        builder.setMessage("Sure you want to go back?")
//        builder.setCancelable(false)
//        builder.setPositiveButton("Yes") { dialog, which ->
//            // TODO: why won't it go back? [haven't got fragments set properly?]
//            // TODO: properly make this fragmented
////            Toast.makeText(applicationContext, "You went back!", Toast.LENGTH_SHORT).show()
//        }
//        builder.setNegativeButton("No") { dialog, which ->
//            // TODO: properly make this fragmented
////            Toast.makeText(applicationContext, "You stayed here", Toast.LENGTH_SHORT)
////                .show()
//        }
//        builder.show()
//    }
}