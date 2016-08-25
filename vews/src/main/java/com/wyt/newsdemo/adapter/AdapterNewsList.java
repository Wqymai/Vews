package com.wyt.newsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyt.newsdemo.R;
import com.wyt.newsdemo.entity.NewsContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class AdapterNewsList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<NewsContent> data;

    public AdapterNewsList(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<NewsContent> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<NewsContent> getData() {
        return data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.item_news_list1, parent, false);
            return new FirstHolder(view);
        } else if (viewType == 2) {
            view = layoutInflater.inflate(R.layout.item_news_list2, parent, false);
            return new SecondHolder(view);
        } else if (viewType==3){
            view = layoutInflater.inflate(R.layout.item_news_list3, parent, false);
            return new ThirdHolder(view);
        }else {
            view=layoutInflater.inflate(R.layout.item_footer,parent,false);
            return new Footer(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NewsContent newsContent = data.get(position);
        if (holder instanceof FirstHolder) {
            ((FirstHolder) holder).tvTitle.setText(newsContent.getTitle());
            ((FirstHolder) holder).tvSource.setText(newsContent.getSource());
            ((FirstHolder) holder).tvPubDate.setText(newsContent.getPubDate());
        } else if (holder instanceof SecondHolder) {
            ((SecondHolder) holder).ivImg.setImageResource(R.mipmap.ic_crop_original_white_48dp);
            String url=newsContent.getImageurls().get(0).getUrl();
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_crop_original_white_48dp)
                    .crossFade()
                    .into(((SecondHolder) holder).ivImg);
            ((SecondHolder) holder).tvTitle.setText(newsContent.getTitle());
            ((SecondHolder) holder).tvSource.setText(newsContent.getSource());
            ((SecondHolder) holder).tvPubDate.setText(newsContent.getPubDate());

        } else if (holder instanceof ThirdHolder){
            List<ImageView> imageViews=new ArrayList<>();
            imageViews.add(((ThirdHolder) holder).ivImg1);
            imageViews.add(((ThirdHolder) holder).ivImg2);
            imageViews.add(((ThirdHolder) holder).ivImg3);
            for (int imgIndex=0;imgIndex<3;imgIndex++){
                imageViews.get(imgIndex).setImageResource(R.mipmap.ic_crop_original_white_48dp);
                String url=newsContent.getImageurls().get(imgIndex).getUrl();
                Glide.with(context)
                        .load(url)
                        .centerCrop()
                        .placeholder(R.mipmap.ic_crop_original_white_48dp)
                        .crossFade()
                        .into(imageViews.get(imgIndex));
            }
            ((ThirdHolder) holder).tvTitle.setText(newsContent.getTitle());
            ((ThirdHolder) holder).tvSource.setText(newsContent.getSource());
            ((ThirdHolder) holder).tvPubDate.setText(newsContent.getPubDate());
        }else {
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return -1;
        }
        if (data.get(position).getImageurls() == null || data.get(position).getImageurls().size() <= 0) {
            return 1;
        } else {
            int imgNum = data.get(position).getImageurls().size();
            if (imgNum >= 3) {
                return 3;
            } else {
                return 2;
            }
        }
    }

    public class Footer extends RecyclerView.ViewHolder{
        ProgressBar pbBar;
        public Footer(View itemView) {
            super(itemView);
            pbBar=(ProgressBar)itemView.findViewById(R.id.pbBar);
        }
    }

    public class FirstHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvPubDate;

        public FirstHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSource = (TextView) itemView.findViewById(R.id.tvSource);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
        }
    }

    public class SecondHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvPubDate;
        ImageView ivImg;

        public SecondHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSource = (TextView) itemView.findViewById(R.id.tvSource);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
        }
    }

    public class ThirdHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvPubDate;
        ImageView ivImg1, ivImg2, ivImg3;

        public ThirdHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSource = (TextView) itemView.findViewById(R.id.tvSource);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
            ivImg1 = (ImageView) itemView.findViewById(R.id.ivImg1);
            ivImg2 = (ImageView) itemView.findViewById(R.id.ivImg2);
            ivImg3 = (ImageView) itemView.findViewById(R.id.ivImg3);
        }
    }
}
