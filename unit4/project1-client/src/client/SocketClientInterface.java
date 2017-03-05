package client;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The interface for SocketClientInterface.
 */
public interface SocketClientInterface {
    /**
     * Open connection.
     */
    public boolean openConnection();

    /**
     * Handle Session.
     */
    public void handleSession();

    /**
     * Close session.
     */
    public void closeSession();
}
