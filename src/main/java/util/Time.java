package util;

public class Time {
    //3 This is initialized as soon as the application started
    public static float timeStarted = System.nanoTime();

    //3 We find out the time elapsed in seconds from the start of the app
    public static float getTime() {
        return (float)((System.nanoTime() - timeStarted) * 1e-9);
        //3 this equation converts form nano seconds to seconds. returns the time we are right now
        // minus the time started which gives us the distance.
    }

}
