package kc.ac.kpu.medicina

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.io.FileInputStream
import java.io.FileOutputStream
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    var fname: String = ""
    var str: String = ""
    var str1: String=""
    var fname1: String=""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        call_button.setOnClickListener{
            val nextIntent = Intent(this, Main2Activity::class.java)
            startActivity(nextIntent)
        }



        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 달력 날짜가 선택되면

            save_Btn.visibility = View.VISIBLE // 저장 버튼이 Visible
            contextEditText.visibility = View.VISIBLE // EditText가 Visible
            textView2.visibility = View.INVISIBLE // 저장된 일기 textView가 Invisible
            cha_Btn.visibility = View.INVISIBLE // 수정 Button이 Invisible
            del_Btn.visibility = View.INVISIBLE // 삭제 Button이 Invisible

            contextEditText2.visibility = View.VISIBLE
            textView.visibility = View.INVISIBLE



            /*   diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
// 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.*/
            contextEditText.setText("") // EditText에 공백값 넣기
            contextEditText2.setText("")

            checkedDay(year, month, dayOfMonth) // checkedDay 메소드 호출


        }

        save_Btn.setOnClickListener { // 저장 Button이 클릭되면
            saveDiary(fname,fname1) // saveDiary 메소드 호출
            toast(fname + "를 저장했습니다")// 토스트 메세지
            str = contextEditText.getText().toString()
            str1 = contextEditText2.getText().toString()// str 변수에 edittext내용을 toString
//형으로 저장
            textView2.text = "${str}" // textView에 str 출력
            save_Btn.visibility = View.INVISIBLE
            cha_Btn.visibility = View.VISIBLE
            del_Btn.visibility = View.VISIBLE
            contextEditText.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE

            textView.text = "${str1}"
            contextEditText2.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE

        }


    }

    fun checkedDay(cYear: Int, cMonth: Int, cDay: Int) {
        fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
        fname1 =""+ cYear + "--" + (cMonth + 1) + "" + "--" + cDay + ".txt"
// 저장할 파일 이름 설정. Ex) 2019-01-20.txt
        var fis: FileInputStream? = null
        var fis1: FileInputStream? = null
        // FileStream fis 변수 설정

        try {
            fis = openFileInput(fname)
            fis1 = openFileInput(fname1)

            // fname 파일 오픈!!

            val fileData = ByteArray(fis.available())
            val fileData1 = ByteArray(fis1.available())

            // fileData에 파이트 형식
            //으로 저장
            fis.read(fileData)
            fis1.read(fileData1)

            // fileData를 읽음
            fis.close()
            fis1.close()


            str = String(fileData) // str 변수에 fileData를 저장
            str1 = String(fileData1)


            contextEditText.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE
            textView2.text = "${str}"
            // textView에 str 출력

            contextEditText2.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE

            textView.text = "${str1}"

            save_Btn.visibility = View.INVISIBLE
            cha_Btn.visibility = View.VISIBLE
            del_Btn.visibility = View.VISIBLE


            cha_Btn.setOnClickListener { // 수정 버튼을 누를 시
                contextEditText.visibility = View.VISIBLE
                textView2.visibility = View.INVISIBLE
                contextEditText.setText(str) // editText에 textView에 저장된
// 내용을 출력
                contextEditText2.visibility = View.VISIBLE
                textView.visibility = View.INVISIBLE
                contextEditText2.setText(str1)

                save_Btn.visibility = View.VISIBLE
                cha_Btn.visibility = View.INVISIBLE
                del_Btn.visibility = View.INVISIBLE
                textView2.text = "${contextEditText.getText()}"
                textView.text = "${contextEditText2.getText()}"




            }

            del_Btn.setOnClickListener {
                textView2.visibility = View.INVISIBLE
                contextEditText.setText("")
                contextEditText.visibility = View.VISIBLE

                textView.visibility = View.INVISIBLE
                contextEditText2.setText("")
                contextEditText2.visibility = View.VISIBLE

                save_Btn.visibility = View.VISIBLE
                cha_Btn.visibility = View.INVISIBLE
                del_Btn.visibility = View.INVISIBLE
                removeDiary(fname)
                toast(fname + "데이터를 삭제했습니다.")
            }

            if(textView2.getText() == ""){
                textView2.visibility = View.INVISIBLE
                contextEditText.visibility = View.VISIBLE
                textView.visibility = View.INVISIBLE
                contextEditText2.visibility = View.VISIBLE
                save_Btn.visibility = View.VISIBLE
                cha_Btn.visibility = View.INVISIBLE
                del_Btn.visibility = View.INVISIBLE


            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongConstant")
    fun saveDiary(readyDay: String,readyDay1: String) {
        var fos: FileOutputStream? = null
        var fos1: FileOutputStream? = null


        try {
            fos = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)
            fos1 = openFileOutput(readyDay1, MODE_NO_LOCALIZED_COLLATORS)
            var content: String = contextEditText.getText().toString()
            var content1: String = contextEditText2.getText().toString()

            fos.write(content.toByteArray())
            fos1.write(content1.toByteArray())

            fos.close()
            fos1.close()



        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("WrongConstant")
    fun removeDiary(readyDay: String) {
        var fos: FileOutputStream? = null


        try {
            fos = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)
            var content: String = ""
            fos.write(content.toByteArray())
            fos.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }




}