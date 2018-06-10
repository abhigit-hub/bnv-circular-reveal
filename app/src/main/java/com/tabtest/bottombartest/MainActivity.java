package com.tabtest.bottombartest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int FIRST_COLOR = Color.parseColor("#39dec5");
    private static final int SECOND_COLOR = Color.parseColor("#4648df");
    private static final int THIRD_COLOR = Color.parseColor("#d34836");
    private static final int FOURTH_COLOR = Color.parseColor("#72bc0a");

    private List<Integer> colorList = new ArrayList<>();


    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomBar;

    @BindView(R.id.background)
    View background;

    @BindView(R.id.reveal)
    View view;

    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_constraint);

        ButterKnife.bind(this);

        setUpColorList();

        setUp();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUp() {
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_workspace:
                        reveal1(0, item.getItemId());
                        break;
                    case R.id.action_message_box:
                        reveal1(1, item.getItemId());
                        break;
                    case R.id.action_draft:
                        reveal1(2, item.getItemId());
                        break;
                    case R.id.action_settings:
                        reveal1(3, item.getItemId());
                        break;
                }
                return false;
            }
        });
    }

    public void reveal(final int tabPosition, int itemId) {
        view.setVisibility(View.VISIBLE);
        int cx = view.getWidth();
        int cy = view.getHeight();

        View itemView = bottomBar.findViewById(itemId);

        x = itemView.getX() - bottomBar.getX() + itemView.getWidth()/2;
        y = itemView.getY() - bottomBar.getY() + itemView.getHeight()/2;

        float finalRadius = Math.max(cx, cy) * 1.2f;
        Animator reveal = ViewAnimationUtils
                .createCircularReveal(view, (int) x, (int) y, 0f, finalRadius);

        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                background.setBackgroundColor(colorList.get(tabPosition));
                view.setVisibility(View.INVISIBLE);
            }
        });


        view.setBackgroundColor(colorList.get(tabPosition));
        reveal.start();
    }

    public void reveal1(final int tabPosition, int itemId) {
        view.setVisibility(View.VISIBLE);
        int cx = view.getWidth();
        int cy = view.getHeight();

        View itemView = bottomBar.findViewById(itemId);

        x = itemView.getX() - bottomBar.getX() + itemView.getWidth()/2;
        y = itemView.getHeight()/2;

        float finalRadius = Math.max(cx, cy) * 1f;
        Animator reveal = ViewAnimationUtils
                .createCircularReveal(view, (int) x, (int) y, 0f, finalRadius);

        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                background.setBackgroundColor(colorList.get(tabPosition));
                view.setVisibility(View.INVISIBLE);
            }
        });


        view.setBackgroundColor(colorList.get(tabPosition));
        reveal.start();
    }

    private void setUpColorList() {
        colorList.add(FIRST_COLOR);
        colorList.add(SECOND_COLOR);
        colorList.add(THIRD_COLOR);
        colorList.add(FOURTH_COLOR);
    }
}
