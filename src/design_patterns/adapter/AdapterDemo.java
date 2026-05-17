package adapter;

// Target interface
interface MediaPlayer {
    void play(String filename);
}

// Native implementation
class Mp3Player implements MediaPlayer {
    @Override
    public void play(String filename) {
        System.out.println("MP3 Player: Playing " + filename);
    }
}

// External codec libraries (Adaptees)
class VlcCodec {
    public void playVlc(String filename) {
        System.out.println("VLC Codec: Decoding and playing " + filename);
    }
}

class Mp4Codec {
    public void playMp4(String filename) {
        System.out.println("MP4 Codec: Decoding and playing " + filename);
    }
}

// Adapters
class VlcPlayerAdapter implements MediaPlayer {
    private final VlcCodec codec;

    public VlcPlayerAdapter(VlcCodec codec) {
        this.codec = codec;
    }

    @Override
    public void play(String filename) {
        codec.playVlc(filename);
    }
}

class Mp4PlayerAdapter implements MediaPlayer {
    private final Mp4Codec codec;

    public Mp4PlayerAdapter(Mp4Codec codec) {
        this.codec = codec;
    }

    @Override
    public void play(String filename) {
        codec.playMp4(filename);
    }
}

// Client
class AudioPlayer {
    public void playFile(String filename) {
        MediaPlayer player;
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        switch (extension) {
            case "mp3":
                player = new Mp3Player();
                break;
            case "vlc":
                player = new VlcPlayerAdapter(new VlcCodec());
                break;
            case "mp4":
                player = new Mp4PlayerAdapter(new Mp4Codec());
                break;
            default:
                System.out.println("Unsupported format: " + extension);
                return;
        }

        player.play(filename);
    }
}

// Main
public class AdapterDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.playFile("song.mp3");
        player.playFile("movie.mp4");
        player.playFile("documentary.vlc");
        player.playFile("image.png");
    }
}

// The Adapter Design Pattern is a structural design pattern that allows
// incompatible interfaces to work together by converting the interface of one
// class into another that the client expects.

// In this example, we have a MediaPlayer interface that defines a play method.
// The Mp3Player class implements this interface directly, while the VlcCodec
// and Mp4Codec classes are external libraries that do not conform to the
// MediaPlayer interface. We create adapter classes (VlcPlayerAdapter and
// Mp4PlayerAdapter) to allow these codecs to be used as MediaPlayers. The
// AudioPlayer class acts as the client that uses the MediaPlayer interface to
// play different types of media files without needing to know about the
// underlying implementations.