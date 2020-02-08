package android.example.musicalapp;

/**
 * {@link Music} represents a song object.
 */
public class Music {

    private int mSongId;
    private int mImageId;
    private String mSongName;
    private String mArtistName;

    /**
     * Create a new Music object.
     *
     * @param songId is the ID of the song
     *
     * @param imageId is the ID of the song picture
     *
     * @param songName is the name of the song
     *
     * @param artistName   is the name of the artist
     */
    public Music(int songId, int imageId, String songName, String artistName) {
        mSongId = songId;
        mImageId = imageId;
        mSongName = songName;
        mArtistName = artistName;
    }

    int getSongId(){
        return mSongId;
    }

    int getImageId() {
        return mImageId;
    }

    public String getSongName() {
        return mSongName;
    }

    public String getArtistName() {
        return mArtistName;
    }

}
