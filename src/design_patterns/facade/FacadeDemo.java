package facade;

// --- Subsystems ---

class Amplifier {
    public void on() {
        System.out.println("Amplifier: Powering on.");
    }

    public void off() {
        System.out.println("Amplifier: Shutting down.");
    }

    public void setVolume(int level) {
        System.out.println("Amplifier: Volume set to " + level + ".");
    }
}

class DvdPlayer {
    public void on() {
        System.out.println("DVD Player: Powering on.");
    }

    public void off() {
        System.out.println("DVD Player: Shutting down.");
    }

    public void play(String movie) {
        System.out.println("DVD Player: Playing '" + movie + "'.");
    }

    public void stop() {
        System.out.println("DVD Player: Stopped.");
    }
}

class Projector {
    public void on() {
        System.out.println("Projector: Warming up.");
    }

    public void off() {
        System.out.println("Projector: Cooling down.");
    }

    public void wideScreenMode() {
        System.out.println("Projector: Widescreen mode enabled.");
    }
}

class SmartLights {
    public void dim(int level) {
        System.out.println("Lights: Dimmed to " + level + "%.");
    }

    public void on() {
        System.out.println("Lights: Full brightness.");
    }
}

class StreamingService {
    public void connect() {
        System.out.println("Streaming: Connected to service.");
    }

    public void disconnect() {
        System.out.println("Streaming: Disconnected.");
    }

    public void stream(String movie) {
        System.out.println("Streaming: Now streaming '" + movie + "'.");
    }
}

// --- Facade ---

class HomeTheaterFacade {
    private Amplifier amp;
    private DvdPlayer dvd;
    private Projector projector;
    private SmartLights lights;
    private StreamingService streaming;

    public HomeTheaterFacade(Amplifier amp, DvdPlayer dvd, Projector projector,
            SmartLights lights, StreamingService streaming) {
        this.amp = amp;
        this.dvd = dvd;
        this.projector = projector;
        this.lights = lights;
        this.streaming = streaming;
    }

    public void watchMovie(String movie) {
        System.out.println("\n--- Preparing to watch: " + movie + " ---");
        lights.dim(15);
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setVolume(20);
        streaming.connect();
        streaming.stream(movie);
        System.out.println("--- Enjoy the movie! ---\n");
    }

    public void endMovie() {
        System.out.println("\n--- Shutting down home theater ---");
        streaming.disconnect();
        amp.off();
        projector.off();
        lights.on();
        System.out.println("--- Home theater off ---\n");
    }
}

// --- Client ---

public class FacadeDemo {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        SmartLights lights = new SmartLights();
        StreamingService streaming = new StreamingService();

        HomeTheaterFacade theater = new HomeTheaterFacade(amp, dvd, projector, lights, streaming);

        theater.watchMovie("Interstellar");
        theater.endMovie();
    }
}

// The Facade Design Pattern is a structural design pattern that provides a
// single, simplified interface to a complex subsystem. Instead of forcing
// clients to coordinate many moving parts, a facade hides the internal
// complexity and exposes a clean, easy-to-use entry point.

// In this example, the HomeTheaterFacade class serves as the facade that
// simplifies the interaction with the various components of a home theater
// system. The client can simply call watchMovie() and endMovie() without
// needing to know the details of how the amplifier, DVD player, projector,
// lights, and streaming service work together.