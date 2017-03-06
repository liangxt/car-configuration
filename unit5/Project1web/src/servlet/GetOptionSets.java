package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Automobile;
import model.OptionSet;

/**
 * Servlet implementation class GetOptionSets Color:Sangria Red Clearcoat
 * Metallic Transmission:Automatic Brakes/Traction Control:ABS with Advance Trac
 * Side Impact Air Bags:not present Power Moonroof:present
 */
@WebServlet("/GetOptionSets.do")
public class GetOptionSets extends HttpServlet {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Automobile object.
     */
    private Automobile auto;
    /**
     * OptionSet name.
     */
    private ArrayList<String> setname;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOptionSets() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            auto = (Automobile) session.getAttribute("auto");
        }
        if (auto == null) {
            return;
        }

        // * Color:Sangria Red Clearcoat Metallic
        // Transmission:Automatic
        // Brakes/Traction Control:ABS with Advance Trac
        // Side Impact Air Bags:not present
        // Power Moonroof:present
        //

        String color = (String) request.getParameter("Color");
        String transmission = (String) request.getParameter("Transmission");
        String brakes = (String) request.getParameter("Brakes/Traction Control");
        String sideImpact = (String) request.getParameter("Side Impact Air Bags");
        String powerMoonRoot = (String) request.getParameter("Power Moonroof");

        float colorPrice = auto.getPriceByIndexAndKey(0, color);
        float transmissionPrice = auto.getPriceByIndexAndKey(1, transmission);
        float brakesPrice = auto.getPriceByIndexAndKey(2, brakes);
        float sideImpactPrice = auto.getPriceByIndexAndKey(3, sideImpact);
        float powerMoonPrice = auto.getPriceByIndexAndKey(4, powerMoonRoot);
        float basePrice = auto.getBasePrice();
        float total = colorPrice + transmissionPrice + brakesPrice + sideImpactPrice + powerMoonPrice + basePrice;

        request.setAttribute("model",auto.getMake() + auto.getModel());

        
        request.setAttribute("colorValue",color);
        request.setAttribute("transmissionValue",transmission);
        request.setAttribute("brakesValue",brakes);
        request.setAttribute("sideImpactValue",sideImpact);
        request.setAttribute("powerMoonRoofValue",powerMoonRoot);

        
        request.setAttribute("colorPrice",colorPrice);
        request.setAttribute("transmissionPrice",transmissionPrice);
        request.setAttribute("brakesPrice",brakesPrice);
        request.setAttribute("sideImpactPrice",sideImpactPrice);
        request.setAttribute("powerMoonRoofPrice",powerMoonPrice);
        request.setAttribute("totalPrice",total);
        request.setAttribute("basePrice",basePrice);

        request.getRequestDispatcher("/ModelSelect.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
