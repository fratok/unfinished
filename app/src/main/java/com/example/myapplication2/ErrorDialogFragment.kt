import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.myapplication2.DialogListener
import com.example.myapplication2.R
class ErrorDialogFragment(private val errorMessage: String) : AppCompatDialogFragment() {

    private var listener: DialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DialogListener) {
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.error)

            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val errorText = findViewById<TextView>(R.id.error_title)
            errorText.text = errorMessage

            findViewById<Button>(R.id.button_understood).setOnClickListener {
                dismiss()
            }
            return dialog
        }
    }
}

