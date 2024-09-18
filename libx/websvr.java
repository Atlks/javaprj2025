package libx;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@Component
@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class websvr implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑（可选）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 输出 888
        response.getWriter().write("777");
        response.getWriter().flush();

        // 不执行后续的请求处理
        // 如果需要继续执行请求，可以取消注释以下代码
        // chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理逻辑（可选）
    }
}
