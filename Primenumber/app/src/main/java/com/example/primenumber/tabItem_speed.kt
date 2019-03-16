package com.example.primenumber

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_tab_item_speed.*
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import android.content.ContextWrapper


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [tabItem_speed.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [tabItem_speed.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class tabItem_speed : Fragment(), AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var readString = "1"
        try {
            var contextWrapper = android.content.ContextWrapper(context)
            val TESTSTRING = "Hello Android"
//            val fOut = contextWrapper.openFileOutput(
//                "samplefile.txt",
//                Context.MODE_PRIVATE
//            )
//            val osw = OutputStreamWriter(fOut)
//
//            osw.write(TESTSTRING)
//
//            osw.flush()
//            osw.close()

            val fIn = contextWrapper.openFileInput("samplefile.txt")
            val isr = InputStreamReader(fIn)

            val inputBuffer = CharArray(TESTSTRING.length)

            isr.read(inputBuffer)

            readString = String(inputBuffer)

            val isTheSame = TESTSTRING == readString

            Log.d("QQQ", "success = $isTheSame")
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

        val prime_number = arrayOf(readString, "3", "5").toList()
        val adapter = ArrayAdapter<String>(
            context,
            android.R.layout.simple_list_item_1, prime_number
        )
        listView.adapter = adapter

    }

    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.fragment_tab_item_speed, container, false)
        return inflater
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context

        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment tabItem_speed.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            tabItem_speed().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
