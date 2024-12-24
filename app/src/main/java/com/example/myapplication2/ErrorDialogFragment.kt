import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.myapplication2.DialogListener
import com.example.myapplication2.R
class ErrorDialogFragment(private val errorMessage: String) : AppCompatDialogFragment() {

        private var listener: DialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DialogListener){
            listener = context
        }
    }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.error)


            val titleTextView: TextView = dialog.findViewById(R.id.error_title)
            val understoodButton: Button = dialog.findViewById(R.id.button_understood)
            dialog.window?.setBackgroundDrawableResource(R.drawable.background_dialog)

            titleTextView.text = errorMessage

            understoodButton.setOnClickListener {
                dismiss()
                listener?.onDialogDismissed()
            }

            return dialog
        }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    }