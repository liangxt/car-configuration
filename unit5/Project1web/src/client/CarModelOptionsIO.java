package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
     * socket.
     */
    private Socket sock0=null; 
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
    private String[] filename = { "model", "model2", "model3" };
    /**
     * socket.
     */
    private ServerSocket socket = null;
    /**
     * Properties.
     */
    private Properties[] prop = new Properties[3];
    /**
     * List for models.
     */
    private LinkedHashMap <String,Automobile> modelList = new LinkedHashMap<String,Automobile>();

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
   public CarModelOptionsIO() {}

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
        for (int i = 0; i < 3; i++) {
            Properties tmp = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename[i]);
            if (inputStream != null) {
                try {
                    tmp.load(inputStream);
                    prop[i] = tmp;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        sendOutput();
    }

    /**
     * Send properties to server.
     */
    public void sendOutput() {
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
            sock0.close();
            System.out.println("Close socket.");
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    public  LinkedHashMap <String,Automobile> connectServlet() {
        for (int i=0;i<3;i++){
            BuildAuto(filename[i],"properties");
        }
        return modelList;
        
//        try {
//            sock0 = new Socket(strHost,PORTD);
//            oos = new ObjectOutputStream(sock0.getOutputStream());
//            oos.writeObject(modelList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
//        try{
//            URL url=new URL("http://www.baidu.com");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            ObjectOutputStream out = new ObjectOutputStream(conn.getOutputStream());
//            out.writeObject(modelList);         
//            out.flush();
//            out.close();          
//            ObjectInputStream in = new ObjectInputStream(conn.getInputStream());
//            String message = (String) in.readObject();
//            in.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
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
        FileIO fileio = new FileIO();
        Automobile auto;
        try {
            String name="src\\"+filename+".properties";
            auto = fileio.readProperties(name, filetype);
            modelList.put(filename,auto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print Auto.
     * @param Modelname
     *            Model name
     */
    @Override
    public void printAuto(String Modelname) {
        for (int i = 0; i < modelList.size(); i++) {
            if (modelList.get(i) != null) {
                modelList.get(i).print();
            }
        }
    }

    /**
     * Main.
     * @param arg[]
     *            argument
     */
//    public static void main(String arg[]) {
//        String strLocalHost = "";
//        try {
//            strLocalHost = InetAddress.getLocalHost().getHostName();
//        } catch (UnknownHostException e) {
//            System.err.println("Unable to find local host");
//        }
//        CarModelOptionsIO d = new CarModelOptionsIO(strLocalHost, iDAYTIME_PORT);
//        d.start();
//
//    }
}
