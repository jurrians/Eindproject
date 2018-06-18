package e.gebruiker.eindproject.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import e.gebruiker.eindproject.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class BalanceFragment extends Fragment implements ScreenShotable{

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    public FloatingActionButton addCategoryButton;
    public View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_balance, container, false);

        addCategoryButton = (FloatingActionButton) rootView.findViewById(R.id.floatingBalanceButton);


        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            //            @Override
            public void onClick(View view) {
                Log.d("click", "click");

//                Fragment addCategoryFragment = new AddCategoryFragment();
                // getActivity.
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.content_frame, addCategoryFragment).commit();
            }
        });

        return rootView;
    }


    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }


}