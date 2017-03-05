package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;
import util.FileIO;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The interface for BuildCarModelOptions.
 */
public class BuildCarModelOptions extends Thread implements AutoServer, SocketClientInterface, SocketClientConstants {
    /**
     * socket.
     */
    private ServerSocket sock1 = null;
    /**
     * Host.
     */
    private String strHost;
    /**
     * Port number.
     */
    private int iPort;
    /**
     * ObjectInputStream.
     */
    private ObjectInputStream ois;
    /**
     * ObjectOutputStream.
     */
    private ObjectOutputStream oos;
    /**
     * Automobile object.
     */
    private Automobile auto;
    /**
     * Automobile object.
     */
    private Automobile optionAuto;
    /**
     * socket.
     */
    private Socket sock2 = null;
    /**
     * socket.
     */
    private Socket sock3 = null;
    /**
     * socket.
     */
    private ServerSocket sock5 = null;
    /**
     * socket.
     */
    private Socket sock6 = null;
    /**
     * socket.
     */
    private Socket sock4 = null;
    /**
     * Properties.
     */
    private Properties pros = new Properties();
    /**
     * Client choice.
     */
    private String clientChoice;
    /**
     * List for models.
     */
    private LinkedHashMap<String, Automobile> modelList = new LinkedHashMap<String, Automobile>();
    /**
     * Constructor.
     * @param strHost host
     * @param iPort port number
     * @param clientChoice Client choice
     */
    public BuildCarModelOptions(String strHost, int iPort, String clientChoice) {
        setPort(iPort);
        setHost(strHost);
        this.clientChoice = clientChoice;
    }
    /**
     * Run.
     */
    public void run() {
        if (clientChoice.equals("SelectCarOption")) {
            modelList();
            if (openOptionConnection()) {
                sendModel();
                handleOptionSession();
                closeOptionSession();
            }
        } else {
            if (openConnection()) {
                handleSession();
                closeSession();
            }
        }
    }
    /**
     * Set host.
     * @param strHost host
     */
    public void setHost(String strHost) {
        this.strHost = strHost;
    }
    /**
     * Set port.
     * @param iPort port number
     */
    public void setPort(int iPort) {
        this.iPort = iPort;
    }
    /**
     * Add map.
     * @param mobile Automobile object
     * @param modlename model's name
     */
    public void addMap(Automobile mobile, String modelname) {
        BuildAuto build = new BuildAuto();
        build.addMap(modelname, mobile);
        modelList.put(modelname, mobile);
        System.out.println("Successfully add into the map.");
    }
    /**
     * Open connection.
     */
    @Override
    public boolean openConnection() {
        try {
            sock1 = new ServerSocket(iPort);
            System.out.println("Listen on port: " + iPort + " ,waiting for connection.");
            sock2 = sock1.accept();
            ois = new ObjectInputStream(sock2.getInputStream());
        } catch (IOException socketError) {
            System.err.println("Could not listen on port: " + iPort);
            System.exit(1);
            return false;
        }
        return true;
    }
    /**
     * Open Option's connection.
     */
    public boolean openOptionConnection() {
        try {
            sock1 = new ServerSocket(PORTA);
            System.out.println("Listen on port: " + iPort + " ,waiting for connection.");
            sock2 = sock1.accept();
            ois = new ObjectInputStream(sock2.getInputStream());
        } catch (IOException socketError) {
            System.err.println("Could not listen on port: " + iPort);
            System.exit(1);
            return false;
        }
        return true;
    }
    /**
     * Send Model.
     */
    public void sendModel() {
        String[] name = { "model2", "model3" };
        try {
            sock4 = new Socket(strHost, PORTB);
            oos = new ObjectOutputStream(sock4.getOutputStream());
            oos.writeObject(name);
        } catch (IOException socketError) {
            System.err.println("Could not listen on port: " + iPort);
            System.exit(1);
        }
    }
    /**
     * Handle Session.
     */
    @Override
    public void handleSession() {
        try {
            System.out.println("Get input stream from client.");
            pros = (Properties) ois.readObject();
            FileIO fileIO = new FileIO();
            auto = fileIO.readProperties(pros);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        addMap(auto, "model1");
        System.out.println("Successfully create Automobile!");
        auto.print();
        sendOutput();
    }
    /**
     * Handle Option Session.
     */
    public void handleOptionSession() {
        try {
            sock5 = new ServerSocket(iOption_PORT);
            sock6 = sock5.accept();
            ois = new ObjectInputStream(sock6.getInputStream());
        } catch (IOException socketError) {
            System.err.println("Could not listen on port: " + iPort);
            System.exit(1);
        }
        try {
            System.out.println("Get input stream from client.");
            String modelname = (String) ois.readObject();
            optionAuto = modelList.get(modelname);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sendOptionOutput();
    }
    /**
     * Send output.
     */
    public void sendOutput() {
        try {
            sock3 = new Socket(strHost, iSMTP_PORT);
            oos = new ObjectOutputStream(sock3.getOutputStream());
            String str = "Successfully create Automobile!";
            oos.writeObject(str);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    /**
     * Send Option's output.
     */
    public void sendOptionOutput() {
        try {
            sock3 = new Socket(strHost, PORTC);
            oos = new ObjectOutputStream(sock3.getOutputStream());
            oos.writeObject(optionAuto);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    /**
     * Close session.
     */
    @Override
    public void closeSession() {
        try {
            ois.close();
            oos.close();
            sock3.close();
            sock2.close();
            sock1.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }
    /**
     * Close Option session.
     */
    public void closeOptionSession() {
        try {
            ois.close();
            oos.close();
            sock3.close();
            sock2.close();
            sock1.close();
            sock4.close();
            sock5.close();
            sock6.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }
    /**
     * Add file into map.
     */
    public void modelList() {
        String filename1 = "src/model2.properties";
        String filename2 = "src/model3.properties";
        FileIO fileio = new FileIO();
        Automobile auto1;
        Automobile auto2;
        try {
            auto1 = fileio.readProperties(filename1, "properties");
            auto2 = fileio.readProperties(filename2, "properties");
            modelList.put("model2", auto1);
            modelList.put("model3", auto2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Main.
     * @param args argument
     */
    public static void main(String[] args) {
        String strLocalHost = "";
        
        // String str = "SelectCarOption";
        // int port1=PORTA;
        /**
         * Uncomment two lines of codes above and comment two lines of codes
         * below. 
         * When str is "Upload", the server communicates with
         * CarModelOptionsIO. If the communication is successful, this class
         * will output Automobile's information and CarModelOptionsIO will
         * output "Successfully create Automobile!" which is sent from the
         * server. 
         * When str is "SelectCarOption", the server communicates with
         * the SelectCarOption client. If the communication is successful, the
         * SelectCarOption will output Options for every OptionSet. The two
         * properties file are two models for client to choose.
         */
        String str = "Upload";
        int port1 = iDAYTIME_PORT;
        try {
            strLocalHost = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
        }
        BuildCarModelOptions d = new BuildCarModelOptions(strLocalHost, port1, str);
        d.start();
    }
}
