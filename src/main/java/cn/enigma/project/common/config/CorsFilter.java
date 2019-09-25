package cn.enigma.project.common.config;

import cn.enigma.project.common.config.cors.CorsConfig;
import cn.enigma.project.common.util.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 
 * @author luzh
 * createTime 2017年5月23日 下午2:58:47
 */
@Configuration
public class CorsFilter extends OncePerRequestFilter {

	private CorsConfig corsConfig;

	public CorsFilter(CorsConfig corsConfig) {
		this.corsConfig = corsConfig;
	}

	private static final String HEADER = "Origin,X-Requested-With,Content-Type,Access-Control,Accept";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PUT,OPTIONS");
		// 这块要注意
		response.setHeader("Access-Control-Allow-Headers", StringUtil.isEmpty(corsConfig.getHeaders()) ? HEADER : HEADER + "," + corsConfig.getHeaders());
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setHeader("Access-Control-Max-Age", "3600");  
		filterChain.doFilter(request, response);
	}

}
