package lk.sliit.eleven.bakeryorder.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorHandler", urlPatterns = {"/error"})
public class ErrorHandler extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get error details
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        // Log the error
        logger.error("Error in {} while requesting {}: {}",
                servletName, requestUri, throwable != null ? throwable.getMessage() : "Unknown error");

        // Set default values if null
        servletName = servletName != null ? servletName : "Unknown";
        requestUri = requestUri != null ? requestUri : "Unknown";
        statusCode = statusCode != null ? statusCode : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

        // Set attributes for JSP
        request.setAttribute("statusCode", statusCode);
        request.setAttribute("servletName", servletName);
        request.setAttribute("requestUri", requestUri);
        request.setAttribute("exception", throwable);

        // Set proper status code
        response.setStatus(statusCode);

        // Forward to error page
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }
}