package proj.api.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Order(1)
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        int responseCode = getResponseCode(method);
        if (responseCode == 401) {
            ((HttpServletResponse) servletResponse).sendRedirect("http://localhost/auth/register");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private int getResponseCode(String method) throws IOException {
        URL url = new URL("http://authentication:8080/validate" + "?param=" + (method.equals("POST") ? "OK" : "NOTOK"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        System.out.println(con.getResponseCode());
        return con.getResponseCode();
    }
}

