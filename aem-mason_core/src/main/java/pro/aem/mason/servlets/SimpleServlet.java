package pro.aem.mason.servlets;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import pro.aem.mason.services.RunJobService;

import java.io.IOException;

@SlingServlet(
        paths = {SimpleServlet.SERVLET_URL},
        methods = {"GET"},
        label = "Simple Servlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

    public static final String SERVLET_URL = "/services/bob";

    @Reference
    private RunJobService jobService;


    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
            throws IOException {
        String msg = request.getParameter("msg");
        if (msg == null || msg.isEmpty()) {
            msg = "empty msg!";
        }

        String talkMsg = jobService.startScheduledJob(msg);
        response.getWriter().write("20 minutes adventure! " + talkMsg);
    }
}
