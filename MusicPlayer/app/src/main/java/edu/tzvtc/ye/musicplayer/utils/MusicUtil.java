package edu.tzvtc.ye.musicplayer.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.tzvtc.ye.musicplayer.MusicInfo;

public class MusicUtil {



    public static List<MusicInfo> getMp3Infos(Context context){
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        List<MusicInfo> musicInfos = new ArrayList<MusicInfo>();
        for (int i = 0;i < cursor.getCount();i++){
            cursor.moveToNext();
            MusicInfo musicInfo = new MusicInfo();
            //音乐Id
            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            //音乐标题
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            //艺术家
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            //专辑
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            //displayname???
            String displayName = cursor.getString(
                    cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            //专辑Id??
            long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            //时长
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            //文件大小
            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            //文件路径
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            //是否为音乐文件
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));

            //只把音乐添加进集合
            if (isMusic != 0){
                musicInfo.setId(id);
                musicInfo.setTitle(title);
                musicInfo.setArtist(artist);
                musicInfo.setAlbum(album);
                musicInfo.setDisplayName(displayName);
                musicInfo.setAlbumId(albumId);
                musicInfo.setDuration(duration);
                musicInfo.setSize(size);
                musicInfo.setUrl(url);
                musicInfos.add(musicInfo);
            }


        }
        return musicInfos;
    }

    public static List<HashMap<String,String>> getMusicMaps(List<MusicInfo> mp3Infos){
        List<HashMap<String,String>> mp3list = new ArrayList<HashMap<String, String>>();
        for (Iterator iterator = mp3Infos.iterator();iterator.hasNext();){
            MusicInfo musicInfo = (MusicInfo) iterator.next();
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("title",musicInfo.getTitle());
            map.put("Artist", musicInfo.getArtist());
            map.put("album", musicInfo.getAlbum());
            map.put("displayName", musicInfo.getDisplayName());
            map.put("albumId", String.valueOf(musicInfo.getAlbumId()));
            map.put("duration", formatTime(musicInfo.getDuration()));
            map.put("size", String.valueOf(musicInfo.getSize()));
            map.put("url", musicInfo.getUrl());
            mp3list.add(map);

        }
        return mp3list;
    }

    /**
     * 格式化时间，将毫秒转换为分:秒格式
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        // TODO Auto-generated method stub
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }

}
