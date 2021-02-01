package proj.api.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(1)
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (validate(httpServletRequest)) {
            System.out.println(Arrays.stream(httpServletRequest.getCookies())
                    .filter(cookie -> cookie.getName().equals("AUTHORIZED"))
                    .findFirst()
                    .get().getMaxAge());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("http://localhost/auth");
        }
    }

    private boolean validate(HttpServletRequest request) {
        Optional<Cookie[]> cookies = Optional.ofNullable(request.getCookies());
        return cookies.isPresent() && Arrays.stream(cookies.get())
                .anyMatch(cookie -> cookie.getName().equals("AUTHORIZED"));
    }
}

