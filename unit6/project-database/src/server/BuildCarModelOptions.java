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
    private Socket sock5 = null;
    /**
     * socket.
     */
    private Socket sock6 = null;
    /**
     * socket.
     */
    private ServerSocket sock4 = null;
    /**
     * socket.
     */
    private ServerSocket sock7 = null;
    /**
     * socket.
     */
    private Socket sock8 = null;
    /**
     * socket.
     */
    private Socket sock9 = null;
    /**
     * Properties.
     */
    private Properties[] pros = new Properties[3];
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
    public BuildCarModelOptions(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }
    /**
     * Run.
     */
    public void run() {
        if (openConnection()) {
            handleSession();
            if (openOptionConnection()) {
                sendModel();
                handleOptionSession();
            }
//            closeSession();
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
     * Handle Session.
     */
    @Override
    public void handleSession() {
        try {
            System.out.println("Get input stream from client.");
            pros = (Properties[]) ois.readObject();
            FileIO fileIO = new FileIO();
            for (int i=0;i<3;i++){
                Automobile auto = fileIO.readProperties(pros[i]); 
                int tmp=i+1;
                addMap(auto,"model"+tmp);
            }           
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sendOutput();
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
     * Open Option's connection.
     */
    public boolean openOptionConnection() {
        try {
            sock4 = new ServerSocket(PORTA);
            System.out.println("Listen on port: " + PORTA + " ,waiting for connection.");
            sock5 = sock4.accept();
            ois = new ObjectInputStream(sock5.getInputStream());
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
        String[] name = { "model1","model2", "model3" };
        try {
            sock6 = new Socket(strHost, PORTB);
            oos = new ObjectOutputStream(sock6.getOutputStream());
            oos.writeObject(name);
        } catch (IOException socketError) {
            System.err.println("Could not listen on port: " + iPort);
            System.exit(1);
        }
    }
   
    /**
     * Handle Option Session.
     */
    public void handleOptionSession() {
        try {
            sock7 = new ServerSocket(iOption_PORT);
            sock8 = sock7.accept();
            ois = new ObjectInputStream(sock8.getInputStream());
            String modelname = (String) ois.readObject();
            optionAuto = modelList.get(modelname);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sendOptionOutput();
    }
   
    /**
     * Send Option's output.
     */
    public void sendOptionOutput() {
        try {
            sock9 = new Socket(strHost, PORTC);
            oos = new ObjectOutputStream(sock9.getOutputStream());
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
            sock4.close();
            sock5.close();
            sock6.close();
            sock7.close();
            sock8.close();
            sock9.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }
    /**
     * Main.
     * @param args argument
     */
    public static void main(String[] args) {
        String strLocalHost = "";
        try {
            strLocalHost = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
        }
        BuildCarModelOptions d = new BuildCarModelOptions(strLocalHost,iDAYTIME_PORT);
        d.start();
    }
}
