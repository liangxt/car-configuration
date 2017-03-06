package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;
import util.FileIO;

/**
 * Servlet implementation class AutoSelect
 */
@WebServlet("/GetAutoCarList.do")
public class AutoSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String[] filename = { "model.properties", "model2.properties" };
    private LinkedHashMap<String, Automobile> list = new LinkedHashMap<String, Automobile>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
        FileIO fileio = new FileIO();
        Automobile auto1;
        Automobile auto2;
        try {
            filename[0] = getServletContext().getRealPath(filename[0]);
            auto1 = fileio.readProperties(filename[0], "properties");
            list.put(auto1.getMake()+auto1.getModel(), auto1);
            filename[1] = getServletContext().getRealPath(filename[1]);
            auto2 = fileio.readProperties(filename[1], "properties");
            list.put(auto2.getMake()+auto2.getModel(), auto2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("autoList", list);
        request.getRequestDispatcher("/AutoCarList.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("autoCarKey");
        Automobile auto = list.get(key);
        request.getSession().setAttribute("auto", auto);        
        request.getRequestDispatcher("/WebCar.jsp").forward(request, response);

        

    }

}
