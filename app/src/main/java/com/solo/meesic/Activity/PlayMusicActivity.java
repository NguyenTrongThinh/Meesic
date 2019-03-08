package com.solo.meesic.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import com.solo.meesic.Adapter.ViewPagerPlaylistNhac;
import com.solo.meesic.Fragment.DiaNhacFragment;
import com.solo.meesic.Fragment.SongListPlayMusicFragment;
import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.databinding.ActivityPlayMusicBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PlayMusicActivity extends AppCompatActivity {

    public static ArrayList<Song> songArrayList = new ArrayList<>();
    private ActivityPlayMusicBinding binding;
    public static ViewPagerPlaylistNhac adapternhac;
    private DiaNhacFragment diaNhacFragment;
    private SongListPlayMusicFragment songListPlayMusicFragment;
    private MediaPlayer mediaPlayer;
    private int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();

        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (songArrayList.size() > 0) {
                        diaNhacFragment.PlayMusic(songArrayList.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        binding.activityPlaymusicImgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconplay);
                    } else {
                        mediaPlayer.start();
                        binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconpause);
                    }
                } else {
                    new PlayMp3().execute(songArrayList.get(0).getLinkbaihat());
                    diaNhacFragment.PlayMusic(songArrayList.get(0).getHinhbaihat());
                    getSupportActionBar().setTitle(songArrayList.get(0).getTenbaihat());
                    binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        binding.activityPlaymusicImgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat) {
                    binding.activityPlaymusicImgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                } else {
                    if (checkrandom) {
                        checkrandom = false;
                        binding.activityPlaymusicImgSuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    binding.activityPlaymusicImgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }
            }
        });
        binding.activityPlaymusicImgSuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom) {
                    checkrandom = false;
                    binding.activityPlaymusicImgSuffle.setImageResource(R.drawable.iconsuffle);
                } else {
                    if (repeat) {
                        repeat = false;
                        binding.activityPlaymusicImgRepeat.setImageResource(R.drawable.iconrepeat);
                        binding.activityPlaymusicImgSuffle.setImageResource(R.drawable.iconshuffled);
                    } else  {
                        binding.activityPlaymusicImgSuffle.setImageResource(R.drawable.iconshuffled);
                    }

                    checkrandom = true;
                }
            }
        });
        binding.activityPlaymusicSeekbarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        binding.activityPlaymusicImgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songArrayList.size() > 0) {
                    position += 1;
                    if (position >= songArrayList.size() && repeat)
                        position = 0;
                    if (checkrandom) {
                        position = ThreadLocalRandom.current().nextInt(0, songArrayList.size());
                    }
                    if (mediaPlayer != null ) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                    if (position < songArrayList.size() && position >= 0) {
                        new PlayMp3().execute(songArrayList.get(position).getLinkbaihat());
                        diaNhacFragment.PlayMusic(songArrayList.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenbaihat());
                        binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconpause);
                    } else {
                        binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconplay);
                    }
                }
            }
        });
        binding.activityPlaymusicImgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songArrayList.size() > 0) {
                    position -= 1;
                    if (position < 0 && repeat)
                        position = songArrayList.size() - 1;
                    if (checkrandom) {
                        position = ThreadLocalRandom.current().nextInt(0, songArrayList.size());
                    }
                    if (mediaPlayer != null ) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                    if (position < songArrayList.size() && position >= 0) {
                        new PlayMp3().execute(songArrayList.get(position).getLinkbaihat());
                        diaNhacFragment.PlayMusic(songArrayList.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenbaihat());
                        binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconpause);
                    } else {
                        binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconplay);
                    }
                }
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songArrayList.clear();
        if (intent != null)
        {
            if (intent.hasExtra("cakhuc")) {
                Song song = intent.getParcelableExtra("cakhuc");
                songArrayList.add(song);
            } else if (intent.hasExtra("cacbaihat")) {
                songArrayList = intent.getParcelableArrayListExtra("cacbaihat");

            }
        }

    }

    private void init() {
        setSupportActionBar(binding.activityPlaymusicToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Music");
        binding.activityPlaymusicToolbar.setTitleTextColor(Color.WHITE);
        binding.activityPlaymusicToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                songArrayList.clear();
                finish();
            }
        });
        songListPlayMusicFragment = new SongListPlayMusicFragment();
        diaNhacFragment = new DiaNhacFragment();
        adapternhac = new ViewPagerPlaylistNhac(getSupportFragmentManager());
        adapternhac.addFragment(songListPlayMusicFragment);
        adapternhac.addFragment(diaNhacFragment);
        binding.activityPlaymusicViewpager.setAdapter(adapternhac);
        diaNhacFragment = (DiaNhacFragment) adapternhac.getItem(1);
        if (songArrayList.size() > 0) {
            getSupportActionBar().setTitle(songArrayList.get(0).getTenbaihat());
            new PlayMp3().execute(songArrayList.get(0).getLinkbaihat());
            binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconpause);

        }
    }
    private class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTimeSeekBar();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        binding.activityPlaymusicTxtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        binding.activityPlaymusicSeekbarSong.setMax(mediaPlayer.getDuration());

    }
    private void UpdateTimeSeekBar() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    binding.activityPlaymusicSeekbarSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    binding.activityPlaymusicTxtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;

                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (songArrayList.size() > 0) {
                        position += 1;
                        if (position >= songArrayList.size() && repeat)
                            position = 0;
                        if (checkrandom) {
                            position = ThreadLocalRandom.current().nextInt(0, songArrayList.size());
                        }
                        if (mediaPlayer != null ) {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                mediaPlayer = null;
                            }
                        }
                        if (position < songArrayList.size() && position >= 0) {
                            new PlayMp3().execute(songArrayList.get(position).getLinkbaihat());
                            diaNhacFragment.PlayMusic(songArrayList.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(songArrayList.get(position).getTenbaihat());
                            binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconpause);
                        } else {
                            binding.activityPlaymusicImgPlay.setImageResource(R.drawable.iconplay);
                        }
                    }
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
