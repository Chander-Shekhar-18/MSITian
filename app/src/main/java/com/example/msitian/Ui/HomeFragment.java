package com.example.msitian.Ui;

import static com.example.msitian.R.drawable.msitmain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.msitian.R;
import com.example.msitian.SliderAdapter;
import com.example.msitian.adapters.BranchAdapter;
import com.example.msitian.adapters.BranchModel;
import com.example.msitian.adapters.ViewPagerAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;
    private WormDotsIndicator indicators;

    SliderView sliderView;
    int[] images = {msitmain,
            R.drawable.msit1,
            R.drawable.msit2,
            R.drawable.msit3,
            R.drawable.msit4,
            R.drawable.msit5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        indicators = view.findViewById(R.id.dotsIndicators);



        list = new ArrayList<>();
        list.add(new BranchModel("CSE", "The Computer Science and Engineering Department was established in the year 2000 with the vision to offer Bachelor of Engineering degree in Computer Science & Engineering and impart world class education to the students. A group of well qualified, experienced and highly motivated faculty is engaged in providing quality education to the future computer engineer and keeping them abreast of the fast technological development."));
        list.add(new BranchModel("IT", "Information Technology is most prominent and rapidly developing field in today's world. To maintain speed with latest trends in Information Technology industry, our Information Technology department is fully empowered. Our fully air-conditioned laboratories contain sufficient number of computer systems with latest hardware and software configuration. A very high speed internet connection is there in the labs."));
        list.add(new BranchModel("ECE", "The Department of Electronics & Communication Engineering which has a fine blend of experienced as well as young and dynamic personalities as faculty, is involved in providing quality education at Undergraduate (UG) level. They have an unceasing commitment towards students, helping them learn, grow, and develop and achieve their goals, whether it is becoming a professor, an entrepreneur, or joining industry."));
        list.add(new BranchModel("EEE", "The Department of Electrical & Electronics Engineering has a fine blend of experienced as well as young and dynamic personalities as faculty, is involved in providing quality education at Undergraduate (UG) level. They have an unceasing commitment towards students, helping them learn, grow, and develop and achieve their goals, whether it is becoming a professor, an entrepreneur, or joining industry."));
        list.add(new BranchModel("Applied Science", "The Department of Applied Sciences in Maharaja Surajmal Institute of Technology comprises of Physics, Chemistry, Workshop and Mathematics departments. All the departments are having well equipped spacious laboratories with pleasant working environment. All the labs have ultra new apparatus and equipments to expose the students to new trends in the field of technology and science. "));

        adapter = new BranchAdapter(getContext(),list);
        viewPager.setAdapter(adapter);
        indicators.setViewPager(viewPager);
        ImageView imageMap = view.findViewById(R.id.msitLocation);
        imageMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.FILL);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0,0?q=msit janakpuri");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}