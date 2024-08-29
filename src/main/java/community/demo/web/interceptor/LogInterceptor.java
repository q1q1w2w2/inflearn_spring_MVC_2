package community.demo.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("*** preHandle 호출 ***");
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, uuid);

        // @RequestMapping 을 활용한 핸들러 매핑을 사용하면 핸들러 정보로 HandlerMethod가 넘어옴
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; // 호출할 컨트롤러 메서드의 모든 정보 포함
        }

        log.info("REQUEST [{}][{}][{}][{}]", requestURI, uuid, handler, request.getDispatcherType());
        return true; // false 면 진행되지 않음
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // postHandler -> 예외 발생 시 호출되지 않음
        log.info("*** postHandle 호출 ***");
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // afterCompletion -> 예외 발생해도 호출됨
        log.info("*** afterCompletion 호출 ***");
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]", requestURI, logId, request.getDispatcherType());

        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }
    }
}
