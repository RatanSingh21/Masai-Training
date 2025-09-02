package in.teamPlan.secure_auth_api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.teamPlan.secure_auth_api.util.errorhandler.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorDetails errorDetails = new ErrorDetails(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                "Full authentication is required to access this resource",
                request.getRequestURI()
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
    }
}
