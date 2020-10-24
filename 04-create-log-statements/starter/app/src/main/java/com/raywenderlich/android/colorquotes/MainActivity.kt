/*
 *
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.raywenderlich.android.colorquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private var quoteAuthor:String? = null
  private var quoteText:String? = null
  private var color:Int? = null
  private val TAG = this.javaClass.simpleName

  companion object{
    private const val QUOTE_AUTHOR = "QUOTE_AUTHOR"
    private const val QUOTE_TEXT = "QUOTE_TEXT"
    private const val QUOTE_COLOR = "QUOTE_COLOR"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    randomQuote(mainLayout)
    if (savedInstanceState != null) {
      quoteAuthor = savedInstanceState.getString(QUOTE_AUTHOR)!!
      quoteText = savedInstanceState.getString(QUOTE_TEXT)!!
      color = savedInstanceState.getInt(QUOTE_COLOR)
      updateUI()
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString(QUOTE_AUTHOR, quoteAuthor)
    outState.putString(QUOTE_TEXT, quoteText)
    color?.let {
      outState.putInt(QUOTE_COLOR, it)
    }
  }

  fun randomQuote(view: View) {
    val quote = QuoteGenerator.getQuote()
    quoteAuthor = quote.author
    quoteText = quote.text
    color = getRandomColor()
    updateUI()
  }

  private fun updateUI(){
    quoteTextView.text = quoteText
    authorTextView.text = quoteAuthor
    color?.let {
      mainLayout.setBackgroundColor(it)
      newQuoteButton.setBackgroundColor(it)
    }
  }

  private fun getRandomColor(): Int{
    val rainbow = resources.getIntArray(R.array.rainbow)
    return rainbow.random()
  }
}