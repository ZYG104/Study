package edu.tzvtc.ye.musicplayer.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.tzvtc.ye.musicplayer.MusicInfo;
import edu.tzvtc.ye.musicplayer.R;

public class MusicListAdapter extends BaseAdapter{

    private Context context;
    private List<MusicInfo> musicInfos;
    private MusicInfo musicInfo;

    public MusicListAdapter(Context context, List<MusicInfo> mp3Infos) {
        this.context = context;
        this.musicInfos = mp3Infos;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView =LayoutInflater.from(context)
                    .inflate(R.layout.activity_local_music,parent,false);
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.tv_musicName);
            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.tv_musicArtist);
            //表示给View添加一个格外的数据
            convertView.setTag(viewHolder);
        }else{
            //通过getTag的方法将数据取出来
            viewHolder = (ViewHolder)convertView.getTag();
        }

        musicInfo = musicInfos.get(position);
        //显示标题
        viewHolder.musicTitle.setText(musicInfo.getTitle());
        //显示艺术家
        viewHolder.musicArtist.setText(musicInfo.getArtist());
        //显示时长
        viewHolder.musicDuration.setText(MusicUtil.formatTime(musicInfo.getDuration()));
        return convertView;
    }

    public class ViewHolder{
        //所有控件对象引用
        //专辑图片
        public ImageView albumImage;
        //音乐标题
        public TextView musicTitle;
        //音乐时长
        public TextView musicDuration;
        //音乐艺术家
        public TextView musicArtist;
    }
}
