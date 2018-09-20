package edu.tzvtc.ye.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import edu.tzvtc.ye.musicplayer.utils.MusicListAdapter;
import edu.tzvtc.ye.musicplayer.utils.MusicUtil;


public class LocalMusicActivity extends AppCompatActivity {

    private ListView listView;
    private List<MusicInfo> musicInfos = null;
    private MusicListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        listView = (ListView) findViewById(R.id.lv_music);

        musicInfos = MusicUtil.getMp3Infos(LocalMusicActivity.this);
        listAdapter = new MusicListAdapter(this,musicInfos);
        listView.setAdapter(listAdapter);

    }

}
