package com.example.WifiSearch.ui.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WifiSearch.R;
import com.example.WifiSearch.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class homefragment extends Fragment {
    private FragmentHomeBinding binding;
    private ArrayList<String> mData=new ArrayList<>();
    private RecyclerView recycler_view;
    private MyHomeAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //準備資料,顯示四個主題區
        mData.clear();
        mData.add("Wifi搜尋");
        mData.add("Wifi控制");
        //連結元件
        recycler_view=(RecyclerView) root.findViewById(R.id.home_recycle_view);
        //設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        //設置隔線
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        //將資料交給adapter
        adapter=new MyHomeAdapter(mData);
        //設置adapter給recycler_view
        recycler_view.setAdapter(adapter);
//        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        // 初始化抽屉布局

//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

//        setSupportActionBar(binding.appBarMain.toolbar);


        adapter.setOnItemClickListener(new MyHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //需要設定彈出視窗為home...不然回不去home
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.nav_home, false, true) // 三個參數分別是popUpToId, popUpToInclusive, popUpToSaveState
                        .setLaunchSingleTop(true)
                        .setRestoreState(true)
                        .build(); // 如果需要自定义导航选项，可以在这里设置 NavOptions
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main); // 帶入目前啟動的active與hostfragment
                switch (position) {
                    case 0:
                        navController.navigate(R.id.nav_search, null, navOptions);
                        break;
                    case 1:
                        navController.navigate(R.id.nav_recommand, null, navOptions);
                        break;
                    default:
                        break;
                }
            }
        });

        return root;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}