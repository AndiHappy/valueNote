package servlet.jetty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {
 
 private final Server server;
 private final String address;
 private final int port;
 private final int idleTimeout;
 private final String commandUrl;
 ServerConnector connector = null;
 
 public JettyServer() throws Exception {
  address = "127.0.0.1";
  port = 8011;
  idleTimeout = 3000;
  commandUrl = "/*";
  server = new Server();
  connector = new ServerConnector(server);
  
  connector.setHost(address);
  connector.setPort(port+1);
  connector.setIdleTimeout(idleTimeout);

  server.addConnector(connector);

  ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
  context.setContextPath("/*");
  server.setHandler(context);
  server.start();
  context.addServlet(new ServletHolder(new CommandServlet()), commandUrl);
  
 }
 
 private class CommandServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
      // Capture the command name from the URL
      String cmd = request.getPathInfo();
      if (cmd == null || cmd.equals("/")) {
          // No command specified, print links to all commands instead
          for (String link : commandLinks()) {
              response.getWriter().println(link);
              response.getWriter().println("<br/>");
          }
          return;
      }
      // Strip leading "/"
      cmd = cmd.substring(1);

      // Extract keyword arguments to command from request parameters
      @SuppressWarnings("unchecked") Map<String, String[]> parameterMap = request.getParameterMap();
      Map<String, String> kwargs = new HashMap<String, String>();
      for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
          kwargs.put(entry.getKey(), entry.getValue()[0]);
      }

      // Run the command
      CommandResponse cmdResponse = new CommandResponse("test", "test");

      // Format and print the output of the command
      JsonOutputter outputter = new JsonOutputter();
      response.setStatus(HttpServletResponse.SC_OK);
      response.setContentType(outputter.getContentType());
      outputter.output(cmdResponse, response.getWriter());
  }

}

/**
* Returns a list of URLs to each registered Command.
*/
private List<String> commandLinks() {
  List<String> links = new ArrayList<String>();
  List<String> commands = Arrays.asList("aa","bb");
  Collections.sort(commands);
  for (String command : commands) {
      String url = commandUrl + "/" + command;
      links.add(String.format("<a href=\"%s\">%s</a>", url, command));
  }
  return links;
}
 
 public static void main(String[] args) throws Exception {
  
  JettyServer server = new JettyServer();
  Thread.sleep(10000000);
  
 }

}
