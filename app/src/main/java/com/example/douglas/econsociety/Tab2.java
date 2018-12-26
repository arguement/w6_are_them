package com.example.douglas.econsociety;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Tab2 extends Fragment {
    private Button scan_btn;
    private View viewer;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        scan_btn = (Button) rootView.findViewById(R.id.scan_btn);
        final Activity activity = getActivity();
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                Log.i("test", "onActivityResult: ");
                IntentIntegrator.forSupportFragment(Tab2.this).initiateScan();
                //integrator.initiateScan();
              //  Toast.makeText(viewer.getContext(), "You reached here", Toast.LENGTH_LONG).show();
            }
        });
        viewer = rootView;
        return rootView;
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(viewer.getContext(), "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(viewer.getContext(), result.getContents(),Toast.LENGTH_LONG).show();
                String content = result.getContents();
                String[] contentsArr = content.split(","); // splits data taken from barcode into array
                LOFE.add(contentsArr[0],contentsArr[1],contentsArr[2]);
                //Bundle args = new Bundle();
                //Tab1 tab1 = new Tab1();
               /* args.putStringArray("data",content.split(","));
                tab1.setArguments(args);*/

              /*  FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.tabItem, tab1);
                ft.addToBackStack("");*/
            /*  final  Fragment ft = getFragmentManager().findFragmentById(R.id.recycleView);
              FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
              fragmentTransaction.detach(ft);
              fragmentTransaction.attach(ft);
              fragmentTransaction.commit();*/
                //ft.commit();

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
