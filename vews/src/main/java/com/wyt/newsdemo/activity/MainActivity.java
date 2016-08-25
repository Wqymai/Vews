package com.wyt.newsdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wyt.newsdemo.R;
import com.wyt.newsdemo.adapter.AdapterNewsList;
import com.wyt.newsdemo.entity.NewsContent;
import com.wyt.newsdemo.listener.RecyclerItemClickListener;
import com.wyt.newsdemo.presenter.NewsListPresenter;
import com.wyt.newsdemo.view.NewsListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NewsListView, NavigationView.OnNavigationItemSelectedListener {

    NewsListPresenter newsListPresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvNewslist;
    AdapterNewsList adapterNewsList;
    List<NewsContent> contentList;
    String channelId,channelName;
    int pageNum=1;
    boolean isBusying=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariable();
        initView();
        newsListPresenter.getLatestNewsList(channelId);
    }

    @Override
    public void initVariable() {
        channelId="5572a108b3cdc86cf39001cd";
        channelName="国内焦点";
        newsListPresenter = new NewsListPresenter(this);
        adapterNewsList =new AdapterNewsList(this);
        contentList=new ArrayList<>();
    }

    @Override
    public void initView() {
        //设置标题
        setTitle(channelName);

        //设置toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置悬浮按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //设置侧滑菜单
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //设置下拉刷新
        swipeRefreshLayout= (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //获取最新新闻列表
                if (!isBusying){
                    isBusying = true;
                    newsListPresenter.getLatestNewsList(channelId);
                }
            }
        });

        rvNewslist=(RecyclerView)findViewById(R.id.rvNewslist);
        rvNewslist.setLayoutManager(new LinearLayoutManager(this));
        rvNewslist.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                rvNewslist, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //前往新闻详情页
                toNewsPage(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));
        rvNewslist.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvNewslist.getLayoutManager();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                if (lastVisibleItem == totalItemCount - 1) {
                    //获取更多新闻列表
                    if (!isBusying) {
                        isBusying = true;
                        newsListPresenter.getMoreNewsList(channelId, ++pageNum);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        rvNewslist.setAdapter(adapterNewsList);
    }

    @Override
    public void getLatestNewsList(List<NewsContent> contentList) {
        pageNum=1;
        isBusying = false;
        this.contentList = contentList;
        adapterNewsList.setData(this.contentList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getMoreNewsList(List<NewsContent> contentList) {
        isBusying = false;
        this.contentList.addAll(contentList);
         adapterNewsList.setData(this.contentList);
    }

    @Override
    public void toNewsPage(int position) {

        Intent newsWebView = new Intent();
        newsWebView.setClass(MainActivity.this, NewsWebViewActivity.class);
        newsWebView.putExtra("newsLink", contentList.get(position).getLink());
        newsWebView.putExtra("newsSource", contentList.get(position).getSource());
        startActivity(newsWebView);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String channelIdTemp="";
        String chanelNameTemp="";
        if (id == R.id.navGnjd) {
            channelIdTemp="5572a108b3cdc86cf39001cd";
            chanelNameTemp="国内焦点";
        } else if (id == R.id.navGjjd) {
            channelIdTemp="5572a108b3cdc86cf39001ce";
            chanelNameTemp="国际焦点";
        } else if (id == R.id.navJsjd) {
            channelIdTemp="5572a108b3cdc86cf39001cf";
            chanelNameTemp="军事焦点";
        } else if (id == R.id.navCjjd) {
            channelIdTemp="5572a108b3cdc86cf39001d0";
            chanelNameTemp="财经焦点";
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

        if (!channelIdTemp.equals(channelId)){
            swipeRefreshLayout.setRefreshing(true);
            channelId=channelIdTemp;
            channelName=chanelNameTemp;
            setTitle(channelName);
            //获取最新新闻列表
            newsListPresenter.getLatestNewsList(channelId);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
