package com.example.icecreamdisplay.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.icecreamdisplay.R
import com.example.icecreamdisplay.ui.Flavor
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.ArrayList

class MainFragment : Fragment() {

    var icecreamFlavors: ArrayList<Flavor> = ArrayList()
    var icecreamFlavorButtons: ArrayList<Button> = ArrayList()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var infoDialog: CustomAlertDialogFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val button1 = findViewById(R.id.button1) as Button
//        button1.setOnClickListener {
//            onFlavorSelected()
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        icecreamFlavors.add(Flavor.newInstance(R.id.button1, "Chocholate"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button2, "Vanilla"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button3, "Chocholate Peanut Butter"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button4, "Rocky Road"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button5, "Strawberry"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button6, "Caramel"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button7, "Cookie Dough"))
        icecreamFlavors.add(Flavor.newInstance(R.id.button8, "Mint Chocholate Chip"))

        for (flavor in icecreamFlavors) {
            var button = view.findViewById(flavor.id) as Button
            button.text = flavor.name
            button.setOnClickListener {
                onFlavorSelected(flavor)
            }
            icecreamFlavorButtons.add(button)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun onFlavorSelected(flavor: Flavor) {
        infoDialog?.setDialogMessage(flavor.description).run {
            infoDialog = CustomAlertDialogFragment(
                message = flavor.description,
                title = flavor.name
            )
        }

        activity?.supportFragmentManager?.let {
            if (infoDialog?.isAdded == false) {
                infoDialog?.show(it, "INFO_DIALOG")
            }
        }
    }

}
