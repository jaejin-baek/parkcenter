package com.apollo.android.cleanarchitecture.nplayer;



public enum TestStream {
    BIGBUCKBUNNY(
            "HLS: Big Buck Bunny",
            "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8",
            "http://a2.mzstatic.com/us/r30/Purple/v4/a5/d7/a1/a5d7a17b-1f36-e40f-63cb-bb0eb220cc59/screen320x480.jpeg"),

    APPLE_FMP4(
            "HLS fmp4: Apple Test Stream",
            "https://tungsten.aaplimg.com/VOD/bipbop_adv_fmp4_example/master.m3u8",
            "https://media.wired.com/photos/5933ed8286599a3834f7cdf0/master/w_1024,c_limit/bip.png"),

    BIGBUCKBUNNY_MP4(
            "HTTP Progressive: Big Buck Bunny (mp4)",
            "http://redirector.c.youtube.com/videoplayback?id=604ed5ce52eda7ee&itag=22&source=youtube&sparams=ip,ipbits,expire,source,id&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=513F28C7FDCBEC60A66C86C9A393556C99DC47FB.04C88036EEE12565A1ED864A875A58F15D8B5300&key=ik0",
            "http://a2.mzstatic.com/us/r30/Purple/v4/a5/d7/a1/a5d7a17b-1f36-e40f-63cb-bb0eb220cc59/screen320x480.jpeg"),

    RTSP_TEST1("RTSP: Big Buck Bunny",
            "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov", "http://a2.mzstatic.com/us/r30/Purple/v4/a5/d7/a1/a5d7a17b-1f36-e40f-63cb-bb0eb220cc59/screen320x480.jpeg"),

    WAV_PORTRAIT("HLS portrait video: WAV content",
            "http://dev.p.naverrmc.edgesuite.net/global/read/wav_2016_12_20_1/38601E35609C31E4AA3_muploader_p_360P_640_1228_128_adoptive.m3u8"),

    VR_4MINUTE("360 (VR): 4 minute - Canvas",
            "http://dev.p.naverrmc.edgesuite.net/global/read/v-tf_2016_11_15_1/925drGBWrajcb2QdxxTIvjrflQ_rmcvideo_1080P_1920_6144_192.mp4?__gda__=1533712389_5bfea4962fe1176888d59a1abe33ea79",
            "http://beta.v.phinf.naver.net/20161115_157/1479179408791hnI3M_JPEG/925drGBWrajcb2QdxxTIvjrflQ_rmcvideo_144P_256_100_32_logo.jpg", true),

    TEARS_OF_STEEL(
            "HLS: Tears of Steel",
            "http://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.m3u8",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Tos-poster.png/440px-Tos-poster.png"),

    AKAMAI1("HLS: Akamai 1",
            "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"),
    AKAMAI2("HLS: Akamai 2",
            "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"),

    TELEQUEBEC("HLS: Telequebec.tv", "https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8"),
    STREAMBOX("HLS: ARTE", "http://www.streambox.fr/playlists/test_001/stream.m3u8"),

    APPLE_TS("HLS TS: Apple Test Stream",
            "https://tungsten.aaplimg.com/VOD/bipbop_adv_example_v2/master.m3u8",
            "https://media.wired.com/photos/5933ed8286599a3834f7cdf0/master/w_1024,c_limit/bip.png"),

    RTSP_TEST2("RTSP: Unknown source", "rtsp://85.255.175.244:554/h264"),

    WAV_K_LAMAR("WAV (hls, K-Lamar)",
            "http://dev.p.naverrmc.edgesuite.net/global/read/wav_2016_12_30_1/cb89M1GE_bI6TZsUPPtYb3RWag_rmcvideo_360P_640_1228_128_adoptive.m3u8", "http://cache.umusic.com/_sites/kendricklamar.com/images/og.jpg"),
    WAV_K_LAMAR_AUDIO("WAV (hls, K-Lamar, Audio)",
            "http://dev.p.naverrmc.edgesuite.net/global/read/wav_2016_12_30_1/cb89M1GE_bI6TZsUPPtYb3RWag_rmcvideo_270P_480_400_96-acc.m3u8", "http://cache.umusic.com/_sites/kendricklamar.com/images/og.jpg"),
    ;

    public final String name;
    public final String url;
    public final String thumbnail;
    public final boolean vr360;

    TestStream(String name, String url) {
        this(name, url, null, false);
    }

    TestStream(String name, String url, String thumbnail) {
        this(name, url, thumbnail, false);
    }

    TestStream(String name, String url, String thumbnail, boolean vr360) {
        this.name = name;
        this.url = url;
        this.thumbnail = thumbnail;
        this.vr360 = vr360;
    }
}
