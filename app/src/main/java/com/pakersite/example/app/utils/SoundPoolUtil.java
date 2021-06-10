package com.pakersite.example.app.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

/**
 * author: dan
 * time：2018/8/31
 * desc：播放较短音乐工具
 * fixed time：
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SoundPoolUtil {

    private static final int DEFAULT_CONTENT_TYPE = AudioAttributes.CONTENT_TYPE_MUSIC;
    private static final int DEFAULT_USAGE = AudioAttributes.USAGE_MEDIA;
    //设置允许同时播放的流的最大值
    private static final int DEFAULT_MAX_STREAM = 100;

    private SoundPool mSoundPool;
    private float mLeftVolume, mRightVolume;

    private int mPriority = 1;

    private SoundPoolUtil(SoundPool soundPool) {
        this.mSoundPool = soundPool;
    }


    public int load(String path) {
        return load(path, 1);
    }

    public int load(String path, int priority) {
        int soundId = -1;
        if (mSoundPool != null) {
            soundId = mSoundPool.load(path, priority);
        }
        return soundId;
    }

    public int load(Context context, int resId) {
        return load(context, resId, 1);
    }

    /**
     * @param context  context
     * @param resId    resId
     * @param priority priority
     * @return soundID
     */
    public int load(Context context, int resId, int priority) {
        int soundId = -1;
        if (mSoundPool != null) {
            soundId = mSoundPool.load(context, resId, priority);
        }
        return soundId;
    }

    /**
     * 卸载指定音频
     *
     * @param soundId 音频流
     */
    public void unload(int soundId) {
        if (mSoundPool != null) {
            mSoundPool.unload(soundId);
        }
    }

    /**
     * @param soundId  音频流id
     * @param priority 优先级
     * @param loop     循环次数
     * @param rate     频率 1.0 = normal playback, range 0.5 to 2.0
     * @return 返回streamId, 该值需要缓存后续使用
     */
    public int play(int soundId, float leftV, float rightV, int priority, int loop, float rate) {
        int streamId = 0;
        if (mSoundPool != null) {
            streamId = mSoundPool.play(soundId, leftV, rightV, priority, loop, rate);
        }
        return streamId;
    }

    public void stop(int streamId) {
        if (mSoundPool != null) {
            mSoundPool.stop(streamId);
        }
    }

    public void resume(int streamId) {
        if (mSoundPool != null) {
            mSoundPool.resume(streamId);
        }
    }

    public void pause(int streamId) {
        if (mSoundPool != null) {
            mSoundPool.pause(streamId);
        }
    }

    /**
     * 唤醒全部音频
     */
    public void autoResume() {
        if (mSoundPool != null) {
            mSoundPool.autoResume();
        }
    }

    /**
     * 暂停所有音频
     */
    public void autoPause() {
        if (mSoundPool != null) {
            mSoundPool.autoPause();
        }
    }

    public void setVolume(int streamId, float leftVolume, float rightVolume) {
        if (mSoundPool != null) {
            mLeftVolume = leftVolume;
            mRightVolume = rightVolume;
            mSoundPool.setVolume(streamId, leftVolume, rightVolume);
        }
    }

    public void setPriority(int streamId, int priority) {
        if (mSoundPool != null) {
            mSoundPool.setPriority(streamId, priority);
        }
    }

    public void setRate(int streamId, float rate) {
        if (mSoundPool != null) {
            mSoundPool.setRate(streamId, rate);
        }
    }

    /**
     * 设置加载监听器
     *
     * @param listener listener
     */
    public void setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener listener) {
        if (mSoundPool != null) {
            mSoundPool.setOnLoadCompleteListener(listener);
        }
    }


    /**
     * 设置循环播放次数
     *
     * @param streamId streamId
     * @param loop     0 no loop -1 loop forever
     */
    public void setLoop(int streamId, int loop) {
        if (mSoundPool != null) {
            mSoundPool.setLoop(streamId, loop);
        }
    }


    public void release() {
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    public static class Build {
        AudioAttributes.Builder builder;
        private int mMaxStream = DEFAULT_MAX_STREAM;


        public Build() {
            builder = new AudioAttributes.Builder();
            builder.setContentType(DEFAULT_CONTENT_TYPE);
            builder.setUsage(DEFAULT_USAGE);
        }

        public Build setContentType(int contentType) {
            builder.setContentType(contentType);
            return this;
        }

        public Build setUsage(int usage) {
            builder.setUsage(usage);
            return this;
        }

        public Build setFlag(int flag) {
            builder.setFlags(flag);
            return this;
        }

        public Build setLegacyStreamType(int streamType) {
            builder.setLegacyStreamType(streamType);
            return this;
        }

        public Build setMaxStream(int maxStream) {
            this.mMaxStream = maxStream;
            return this;
        }

        public SoundPoolUtil build() {
            SoundPool.Builder soundBuilder = new SoundPool.Builder();
            soundBuilder.setAudioAttributes(builder.build());
            soundBuilder.setMaxStreams(mMaxStream);
            return new SoundPoolUtil(soundBuilder.build());
        }

    }


}
