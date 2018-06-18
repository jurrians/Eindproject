package e.gebruiker.eindproject.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

    private FloatingActionButton addCategoryButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_balance, container, false);

//
//        addCategoryButton = (FloatingActionButton) getActivity().findViewById(R.id.floatingBalanceButton);
//        addCategoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
//
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(view.getId(), addCategoryFragment).commit();
//                return; addCategoryFragment;
//            }
//        });


        return rootView;
    }


//    public void onAddBalanceButtonClick(View view){
//
//        AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
//
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content_frame, addCategoryFragment).commit();
//
//        //        AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
////
////        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.content_frame, addCategoryFragment);
////        transaction.commit();
//
//    }





    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }


//
//    FloatingActionButton addCategoryButton = () findViewById(R.id.floatingBalanceButton);
//    addCategoryButton.setOnClickListener(new View.OnClickListener() {
//
//    public void onClick(View view) {
//        Log.d("click", "click");
//
//        AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
//
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content_frame, addCategoryFragment);
//        transaction.commit();
//
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content_frame, addCategoryFragment).commit();
//
//    }
//    });



}