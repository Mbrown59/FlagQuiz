package com.example.flagquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import kotlin.random.Random

class SlideShow : Thread
{
  private var noFlags = 0
  private var duration : Long = 0
  private var count : Int = 1
  private var imageView : ImageView? = null
  private var textView : TextView? = null
  private var rand = IntArray(10){Random(System.nanoTime()).nextInt(0,224)}.asList()
  private var files = arrayOf("Afghanistan", "Albania", "Algeria", "American_Samoa", "Andorra", "Angola", "Anguilla",
  "Antigua_and_Barbuda", "Argentina","Armenia", "Aruba","Australia", "Austria","Azerbaijan",
  "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
  "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Botswana", "Brazil", "British_Virgin_Islands",
  "Brunei", "Bulgaria", "Burkina_Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape_Verde",
  "Cayman_Islands", "Central_African_Republic", "Chad", "Chile", "China","Christmas_Island",
  "Colombia","Comoros","Cook_Islands","Costa_Rica","Croatia","Cuba","Cyprus","Cyprus_Northern",
  "Czech_Republic","Cte_dIvoire","Democratic_Republic_of_the_Congo","Denmark","Djibouti","Dominica",
  "Dominican_Republic","Ecuador","Egypt","El_Salvador","Equatorial_Guinea","Eritrea","Estonia",
  "Ethiopia","Falkland_Islands","Faroe_Islands","Fiji","Finland","France","French_Polynesia",
  "Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada",
  "Guam","Guatemala","Guinea","Guinea_Bissau","Guyana","Haiti","Honduras","Hong_Kong","Hungary",
  "Iceland", "India","Indonesia","Iran","Iraq", "Ireland","Israel","Italy","Jamaica","Japan",
  "Jordan", "Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon",
  "Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macao","Macedonia",
  "Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall_Islands","Martinique",
  "Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montserrat",
  "Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal","Netherlands","Netherlands_Antilles",
  "New_Zealand", "Nicaragua","Niger","Nigeria","Niue","Norfolk_Island","North_Korea","Norway",
  "Oman","Pakistan","Palau","Panama","Papua_New_Guinea","Paraguay","Peru","Philippines",
  "Pitcairn_Islands","Poland","Portugal","Puerto_Rico","Qatar","Republic_of_the_Congo","Romania",
  "Russian_Federation","Rwanda","Saint_Kitts_and_Nevis","Saint_Lucia","Saint_Pierre","Saint_Vicent_and_the_Grenadines",
  "Samoa","San_Marino","Sao_Tom_and_Prncipe","Saudi_Arabia","Senegal","Serbia_and_Montenegro",
  "Seychelles", "Sierra_Leone", "Singapore","Slovakia","Slovenia","Soloman_Islands","Somalia",
  "South_Africa","South_Georgia","South_Korea","Soviet_Union","Spain","Sri_Lanka","Sudan","Suriname",
  "Swaziland","Sweden","Switzerland","Syria", "Taiwan","Tajikistan","Tanzania","Thailand",
  "Tibet", "Timor_Leste", "Togo","Tonga","Trinidad_and_Tobago","Tunisia", "Turkey","Turkmenistan",
  "Turks_and_Caicos_Islands", "Tuvalu","UAE","Uganda","Ukraine","United_Kingdom","United_States_of_America",
  "Uruguay","US_Virgin_Islands","Uzbekistan","Vanuatu","Vatican_City","Venezuela","Vietnam",
  "Wallis_and_Futuna","Yemen","Zambia","Zimbabwe")

  private var countries = arrayOf("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
    "Antigua and Barbuda", "Argentina","Armenia", "Aruba","Australia", "Austria","Azerbaijan",
    "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
    "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Botswana", "Brazil", "British Virgin Islands",
    "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
    "Cayman Islands", "Central African Republic", "Chad", "Chile", "China","Christmas Island",
    "Colombia","Comoros","Cook Islands","Costa Rica","Croatia","Cuba","Cyprus","Cyprus Northern",
    "Czech Republic","Cte dIvoire","Democratic Republic of the Congo","Denmark","Djibouti","Dominica",
    "Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia",
    "Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia",
    "Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada",
    "Guam","Guatemala","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary",
    "Iceland", "India","Indonesia","Iran","Iraq", "Ireland","Israel","Italy","Jamaica","Japan",
    "Jordan", "Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon",
    "Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macao","Macedonia",
    "Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique",
    "Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montserrat",
    "Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal","Netherlands","Netherlands Antilles",
    "New Zealand", "Nicaragua","Niger","Nigeria","Niue","Norfolk Island","North Korea","Norway",
    "Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines",
    "Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Republic of the Congo","Romania",
    "Russian Federation","Rwanda","Saint Kitts and Nevis","Saint Lucia","Saint Pierre","Saint Vicent and the Grenadines",
    "Samoa","San Marino","Sao Tom and Prncipe","Saudi Arabia","Senegal","Serbia and Montenegro",
    "Seychelles", "Sierra Leone", "Singapore","Slovakia","Slovenia","Soloman Islands","Somalia",
    "South Africa","South Georgia","South Korea","Soviet Union","Spain","Sri Lanka","Sudan","Suriname",
    "Swaziland","Sweden","Switzerland","Syria", "Taiwan","Tajikistan","Tanzania","Thailand",
    "Tibet", "Timor-Leste", "Togo","Tonga","Trinidad and Tobago","Tunisia", "Turkey","Turkmenistan",
    "Turks and Caicos Islands", "Tuvalu","UAE","Uganda","Ukraine","United Kingdom","United States of America",
    "Uruguay","US Virgin Islands","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam",
    "Wallis and Futuna","Yemen","Zambia","Zimbabwe")


  constructor()
  {
    duration = 10 * 1000
    noFlags = 10
    imageView = MainActivity.getInstance().findViewById(R.id.imageView)
    textView = MainActivity.getInstance().findViewById(R.id.textView)
  }

  override public fun run()
  {
    while(true)
  {

    var count = 0
    var i = 0

    while(count < noFlags)
      {
        println(count)
        println(countries[rand[i]])

        var handler1 = QuizThread1(files[rand[i]])
        MainActivity.getInstance().runOnUiThread(handler1)

        var handler2 = QuizThread2()
        MainActivity.getInstance().runOnUiThread(handler2)
        sleep(duration / 2)

        var handler3 = QuizThread3(countries[rand[i]])
        MainActivity.getInstance().runOnUiThread(handler3)
        sleep(duration / 2)

        i++
        count++
      }
    }
  }
  class QuizThread3 : Runnable
  {
    private var text : String = ""
    constructor(text : String)
    {
      this.text = text
    }
    override fun run()
    {
      var textView = MainActivity.getInstance().findViewById<TextView>(R.id.textView)

      textView.setText(text)
    }

  }
  class QuizThread2 : Runnable
  {
    override fun run()
    {
      var textView = MainActivity.getInstance().findViewById<TextView>(R.id.textView)
      textView.setText("What Flag is This?")
    }
  }

  class QuizThread1 :Runnable
  {
    private var fn : String = ""
    constructor(fn: String)
    {
      this.fn = fn

    }
    override fun run()
    {
      fn = fn.lowercase()
      var id = MainActivity.getInstance().resources.getIdentifier(fn, "drawable", MainActivity.getInstance().packageName)
      imageView.setImageResource(id)

    }
  }
}
var imageView = MainActivity.getInstance().findViewById<ImageView>(R.id.imageView)



class MainActivity : AppCompatActivity()
{
  companion object
  {
    private var instance : MainActivity? = null
    public fun getInstance() : MainActivity
    {
      return instance!!
    }
  }

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    instance = this

    var Quiz = SlideShow()
    Quiz.start()



  }
}