package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The interface for CarModelOptionsIO.
 */
public class SelectCarOption extends Thread implements SocketClientConstants, SocketClientInterface {
    private int port;
    /**
     * socket.
     */
    private Socket sock3 = null;
    private String strHost;
    /**
     * socket.
     */
    private Socket sock1 = null;
    /**
     * socket.
     */
    private ServerSocket sock2 = null;
    /**
     * socket.
     */
    private ServerSocket sock4 = null;
    /**
     * socket.
     */
    private Socket sock5 = null;
    /**
     * socket.
     */
    private Socket sock6 = null;
    /**
     * ObjectInputStream.
     */
    private ObjectInputStream ois;
    /**
     * ObjectOutputStream.
     */
    private ObjectOutputStream oos;
    /**
     * Model name choice.
     */
    private String[] modelname;
    /**
     * Automobile object.
     */
    private Automobile auto;
    /**
     * List for option.
     */
    private ArrayList<String> list = new ArrayList<String>();
    /**
     * Optionset name.
     */
    private String[] setname = { "Color", "Transmission", "Brakes/Traction Control", "Side Impact Air Bags",
            "Power Moonroof" };

    /**
     * Constructor.
     * @param strHost
     *            host
     * @param iPort
     *            port number
     */
    public SelectCarOption(String host, int port) {
        this.port = port;
        this.strHost = host;
    }

    /**
     * Run.
     */
    public void run() {
        if (openConnection()) {
            handleSession();
            setOption();
            displayOption();
            closeSession();
        }
    }

    /**
     * Open connection.
     */
    public boolean openConnection() {
        try {
            sock1 = new Socket(strHost, PORTA);
            System.out.println("Connect to " + strHost);
            oos = new ObjectOutputStream(sock1.getOutputStream());
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
        String request = "Please send me option.";
        sendRequest(request);
    }

    /**
     * Send request to server.
     */
    public void sendRequest(String request) {
        try {
            oos.writeObject(request);
            System.out.println("Send file to " + strHost);
        } catch (IOException e) {
            if (DEBUG)
                System.out.println("Error writing to " + strHost);
        }
        getModel();
    }

    /**
     * Get model's name from server.
     */
    public void getModel() {
        try {
            sock2 = new ServerSocket(PORTB);
            sock3 = sock2.accept();
            ois = new ObjectInputStream(sock3.getInputStream());
            modelname = (String[]) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sendOutput(modelname[0]);
    }

    /**
     * Send model choice to server.
     * @param modelname
     *            model's name
     */
    public void sendOutput(String modelname) {
        try {
            sock6 = new Socket(strHost, iOption_PORT);
            oos = new ObjectOutputStream(sock6.getOutputStream());
            oos.writeObject(modelname);
            System.out.println("Send file to " + strHost);
        } catch (IOException e) {
            if (DEBUG)
                System.out.println("Error writing to " + strHost);
        }
        getResponse();
    }

    /**
     * Get response from server.
     */
    public void getResponse() {
        try {
            sock4 = new ServerSocket(PORTC);
            sock5 = sock4.accept();
            ois = new ObjectInputStream(sock5.getInputStream());
            auto = (Automobile) ois.readObject();
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
            sock1.close();
            sock2.close();
            sock3.close();
            sock4.close();
            sock5.close();
            sock6.close();
            System.out.println("Close socket.");
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    /**
     * Set option.
     */
    public void setOption() {
        try {
            /**
             * The OptionChoice.txt is the client's choice for every OptionSet.
             */
            FileReader file = new FileReader("src/OptionChoice.txt");
            BufferedReader buff = new BufferedReader(file);
            String line = buff.readLine();
            System.out.println(line);
            if (line == null || line.length() == 0) {
                System.out.println("Error!");
                buff.close();
                return;
            }
            String[] option = line.split("\\,");
            for (int i = 0; i < 5; i++) {
                auto.setOptionChoice(setname[i], option[i].trim());
                list.add(option[i]);
            }
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print option.
     */
    public void displayOption() {
        System.out.println("Selected Option:");
        for (int i = 0; i < setname.length; i++) {
            System.out.println(setname[i] + ": " + list.get(i));
        }
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
        SelectCarOption d = new SelectCarOption(strLocalHost, PORTA);
        d.start();
    }
}
