package com.example.WifiSearch.ui.Wifisearch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WifiSearch.R;
import com.example.WifiSearch.databinding.FragmentSearchBinding;

import java.util.List;

public class WifiSearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private RecyclerView recycler_view;
    private WifiManager wifiManager;

    private WifiSearchAdapter adapter=new WifiSearchAdapter();
    private ProgressBar display;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            wifiScan();
        }
    };
    /**建立Runnable, 使掃描可被重複執行*/
    Runnable searchWifi = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
            handler.postDelayed(this, 5000);
        }
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recycler_view = (RecyclerView) root.findViewById(R.id.search_recycle_view);
        //設置RecyclerView為列表型態
//        recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        //設置隔線
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        recycler_view.setAdapter(adapter);

        checkLocatePermission();
        handler.post(searchWifi);

        return root;

    }

    @Override
    public void onStop() {
        super.onStop();
        /**跳出畫面則停止掃描*/
        handler.removeCallbacks(searchWifi);
    }

    private class WifiScanTask extends AsyncTask<Void, Void, List<ScanResult>> {
        @Override
        protected List<ScanResult> doInBackground(Void... voids) {
            wifiManager = (WifiManager) requireContext().getSystemService(Context.WIFI_SERVICE);
            wifiManager.startScan();
            BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context c, Intent intent) {
                    boolean success = intent.getBooleanExtra(
                            WifiManager.EXTRA_RESULTS_UPDATED, false);
                    if (success) {
                        scanSuccess();
                    } else {
                        // scan failure handling
                        scanFailure();
                    }
                }
            };

//            IntentFilter intentFilter = new IntentFilter();
//            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
//            requireContext().registerReceiver(wifiScanReceiver, intentFilter);
//
//            wifiManager.setWifiEnabled(true);
//            boolean success = wifiManager.startScan();
//            if (!success) {
//                // scan failure handling
//                scanFailure();
//            }
//            else {
//                wifiManager.startScan();
//                WifiInfo wi = wifiManager.getConnectionInfo();
//                List<ScanResult> results = wifiManager.getScanResults();
//                String a=results.get(0).SSID;
//            }
            return wifiManager.getScanResults();
        }

        @Override
        protected void onPostExecute(List<ScanResult> wifiList) {
            if (adapter != null) {
                adapter.addItem(wifiList);
            }
        }
    }
    private void wifiScan() {
        new WifiScanTask().execute();
        showPermissioninform();

    }

    private void showPermissioninform()
    {
//        int r=recycler_view.getAdapter().getItemCount();
        display=requireActivity().findViewById(R.id.displayInitial);
        if(recycler_view.getAdapter().getItemCount()==0){
            display.setVisibility(View.VISIBLE);
        }
        else{
            display.setVisibility(View.GONE);
        }
    }

    private void scanSuccess() {
        Log.v("WifiScan","Wifi scan successful");

    }

    private void scanFailure() {
        Log.v("WifiScan","Wifi scan fail");
    }

    public void checkLocatePermission(){
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                Log.v("LocatePermission","Give all Permission at Locate");
                                // Precise location access granted.

                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                Log.v("LocatePermission","Only approximate location access granted");
                                // Only approximate location access granted.
                            } else {
                                Log.v("LocatePermission","No location access granted");
                                // No location access granted.
                            }
                        }
                );
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

}
