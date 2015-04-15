package com.elitetek.scrambleme;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FooterFragment.OnFragmentInteractionListener}
 * interface to handle interaction events.
 *
 */
public class FooterFragment extends Fragment {

	Button gallery;
	Button camera;
	
	private OnFragmentInteractionListener mListener;

	public FooterFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Typeface textFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bradhitc.TTF");
		
		gallery = (Button) getActivity().findViewById(R.id.buttonGallery);
		camera = (Button) getActivity().findViewById(R.id.buttonCamera);
		
		gallery.setTypeface(textFont);
		gallery.setTextColor(Color.BLACK);
		gallery.setTextSize(getResources().getDimension(R.dimen.button_text_size_small));
		
		camera.setTypeface(textFont);
		camera.setTextColor(Color.BLACK);
		camera.setTextSize(getResources().getDimension(R.dimen.button_text_size));
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_footer, container, false);	
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}	
	

	public interface OnFragmentInteractionListener {
		public void fromFooterFragment();
	}

}
