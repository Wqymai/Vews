package com.wyt.newsdemo.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/3/29.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener onItemClickListener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView,
                                     final OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                View childView =recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childView!=null&&onItemClickListener!=null){
                    onItemClickListener.onItemLongClick(childView,
                            recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView=rv.findChildViewUnder(e.getX(),e.getY());
        if (childView!=null&&onItemClickListener!=null&&gestureDetector.onTouchEvent(e)){
            onItemClickListener.onItemClick(childView,rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
