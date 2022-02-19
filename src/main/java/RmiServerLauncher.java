import connector.passive.rmi.RmiServer;

public class RmiServerLauncher implements Runnable {
    @Override
    public void run() {
        RmiServer instance = RmiServer.INSTANCE;
    }
}
