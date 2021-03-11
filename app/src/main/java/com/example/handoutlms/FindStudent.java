package com.example.handoutlms;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindStudent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindStudent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindStudent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner location, school, department, course;
    ImageView back;
    Button find;

    private OnFragmentInteractionListener mListener;

    public FindStudent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindStudent.
     */
    // TODO: Rename and change types and number of parameters
    public static FindStudent newInstance(String param1, String param2) {
        FindStudent fragment = new FindStudent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_find_student, container, false);


        find = v.findViewById(R.id.find);
        location = v.findViewById(R.id.location);
        school = v.findViewById(R.id.school);
        department = v.findViewById(R.id.department);
        course = v.findViewById(R.id.course);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to first fragment
                getActivity().onBackPressed();
            }
        });

        String[] items_location = new String[]{"Please select location...","","Abia", "Adamawa", "Akwa-Ibom", "Anambra", "Bauchi", "Bayelsa",  "Benue", "Borno", "CrossRiver", "Delta", "Edo"};
        String[] items_school = new String[]{"Please select university/school...","","University of Lagos", "University of Abuja", "Federal University of Tech. Akure", "Federal Univeristy of Tech. Minna"};
        String[] items_course = new String[]{"Please select course of study...","","Chemistry", "Physics", "Computer Science", "Medical science", "Business Admin", "Public Admin", "History"};
        String[] items_faculty = new String[]{"Please select faculty...","","Science", "Education", "Art", "Medicine", "Business", "Entrepreneurship"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,  items_location);
        location.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,  items_school);
        school.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,  items_faculty);
        department.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,  items_course);
        course.setAdapter(adapter4);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load discovered fragment
                loadFragment(new DiscoveredStudents());
            }
        });

        return v;
    }

    private void loadFragment(Fragment fragment) {
        //create fragment manager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the LinearLayout with new Fragment
        fragmentTransaction.replace(R.id.framelayoutfindstudent, fragment);
        fragmentTransaction.addToBackStack("find student");
        fragmentTransaction.commit(); // save the changes
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
