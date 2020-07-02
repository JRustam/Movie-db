package jrustam.projects.movie.fragments.exit

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import jrustam.projects.movie.R
import kotlin.system.exitProcess

class AlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertBuilder = AlertDialog.Builder(activity)
            .setIcon(R.drawable.warning)
            .setTitle("Click to buttons.")
            .setMessage("Are you sure to want exit?")
            .setPositiveButton("Yes") { _, _ -> exitProcess(0) }
            .setNegativeButton("No") { _, _ -> dialog!!.dismiss()}
            .setCancelable(true)

        return alertBuilder.create()
    }
}