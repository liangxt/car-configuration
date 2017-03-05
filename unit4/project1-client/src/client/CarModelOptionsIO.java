package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import adapter.CreateAuto;
import model.Automobile;
import util.FileIO;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The interface for CarModelOptionsIO.
 */
public class CarModelOptionsIO extends Thread implements SocketClientInterface, SocketClientConstants, CreateAuto {
    /**
     * socket.
     */
    private Socket sock;
    /**
     * socket.
     */
    private Socket newsock = null;
    /**
     * Host.
     */
    private String strHost;
    /**
     * port number.
     */
    private int iPort;
    /**
     * ObjectOutputStream.
     */
    private ObjectOutputStream oos;
    /**
     * ObjectInputStream.
     */
    private ObjectInputStream ois;
    /**
     * File name.
     */
    private String filename = "model.properties";
    /**
     * Automobile object.
     */
    private Automobile auto;
    /**
     * socket.
     */
    private ServerSocket socket = null;
    /**
     * Properties.
     */
    private Properties prop = new Properties();

    /**
     * Constructor.
     * @param strHost
     *            host
     * @param iPort
     *            port number
     */
    public CarModelOptionsIO(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }

    /**
     * Run.
     */
    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }

    /**
     * Open connection.
     */
    public boolean openConnection() {
        try {
            sock = new Socket(strHost, iPort);
            System.out.println("Connect to " + strHost);
            oos = new ObjectOutputStream(sock.getOutputStream());
        } catch (IOException socketError) {
            if (DEBUG)
                System.err.println("Unable to connect to " + strHost);
            return false;
        }
        return true;
    }

    /**
     * Handle Session.
     */
    public void handleSession() {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sendOutput(prop);
    }

    /**
     * Send properties to server.
     */
    public void sendOutput(Properties prop) {
        try {
            oos.writeObject(prop);
            System.out.println("Send file to " + strHost);
        } catch (IOException e) {
            if (DEBUG)
                System.out.println("Error writing to " + strHost);
        }
        getResponse();
    }

    /**
     * Get response from the server.
     */
    public void getResponse() {
        try {
            socket = new ServerSocket(iSMTP_PORT);
            newsock = socket.accept();
            ois = new ObjectInputStream(newsock.getInputStream());
            String str = (String) ois.readObject();
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close session.
     */
    public void closeSession() {
        try {
            oos.close();
            ois.close();
            sock.close();
            socket.close();
            newsock.close();
            System.out.println("Close socket.");
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    /**
     * Set host.
     * @param strHost
     *            host
     */
    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    /**
     * Set port.
     * @param iPort
     *            port number
     */
    public void setPort(int iPort) {
        this.iPort = iPort;
    }

    /**
     * Build Auto.
     * @param filename
     *            File name
     */
    @Override
    public void BuildAuto(String filename, String filetype) {
        if (filename == null || filetype == null) {
            return;
        }
        FileIO fileio = new FileIO();
        filetype = filetype.trim().toLowerCase();
        if (filetype.equals("properties")) {
            try {
                auto = fileio.readProperties(filename, "properties");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                auto = fileio.buildAutoObject(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Print Auto.
     * @param Modelname
     *            Model name
     */
    @Override
    public void printAuto(String Modelname) {
        auto.print();
    }

    /**
     * Main.
     * @param arg[]
     *            argument
     */
    public static void main(String arg[]) {
        String strLocalHost = "";
        try {
            strLocalHost = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
        }
        CarModelOptionsIO d = new CarModelOptionsIO(strLocalHost, iDAYTIME_PORT);
        d.start();

    }
}
