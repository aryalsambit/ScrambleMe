package com.elitetek.scrambleme;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link ScrambleFragment.OnFragmentInteractionListener}
 * interface to handle interaction events.
 *
 */
public class ScrambleFragment extends Fragment implements View.OnClickListener {

	private OnFragmentInteractionListener mListener;
	Button scrambleMe;
	ImageView pictureToScramble;
	Bitmap img;
	String pathToFile;

	public ScrambleFragment(String path) {
		// Required empty public constructor
		pathToFile = path;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_scramble, container, false);
	}	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		scrambleMe = (Button) getActivity().findViewById(R.id.buttonScramble);
		pictureToScramble = (ImageView) getActivity().findViewById(R.id.imageViewPic);
		
		scrambleMe.setOnClickListener(this);	
		
		img = setPic(pathToFile);
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
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
			case R.id.buttonScramble:
				scrambleImage(img);
		}
	}
	
	public interface OnFragmentInteractionListener {
		public void fromScrambleFragment();
	}
	
	private void scrambleImage(Bitmap image) {
		int rows = 32;
		int cols = 32;
		int chunkWidth = image.getWidth() / cols;
		int chunkHeight = image.getHeight() / rows;
		int count = 0;
		Bitmap[] imgs = new Bitmap[chunkWidth * chunkHeight];
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				imgs[count] = Bitmap.createBitmap(image, x * chunkWidth, y
						* chunkHeight, chunkWidth, chunkHeight);
				count++;
			}
		}
		ArrayList<Bitmap> imgList = new ArrayList<Bitmap>();
		for (int i = 0; i < imgs.length; i++) {
			if(imgs[i] != null){
				imgList.add(imgs[i]);
			}
		}
		Collections.shuffle(imgList);
		
		Bitmap bm = Bitmap.createBitmap(chunkWidth * rows,
				chunkHeight * cols, Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas(bm);
		int num = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				canvas.drawBitmap(imgList.get(num), chunkWidth * i,
						chunkHeight * j, null);
				num++;
			}
		}
		int nh = (int) ( bm.getHeight() * (512.0 / bm.getWidth()) );
		Bitmap scaled = Bitmap.createScaledBitmap(bm, 512, nh, true);
		pictureToScramble.setImageBitmap(scaled);
	}

	private Bitmap setPic(String mCurrentPhotoPath) {
		int targetW = pictureToScramble.getWidth();
		int targetH = pictureToScramble.getHeight();
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW / targetW, photoH / targetH);
		}

		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		
		int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
		Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);

		pictureToScramble.setImageBitmap(scaled);
		return bitmap;
	}	
}
