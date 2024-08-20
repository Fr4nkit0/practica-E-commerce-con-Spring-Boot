package com.example.config.authorization;

import com.example.persistence.entity.security.GrantedPermission;
import com.example.persistence.entity.security.Operation;
import com.example.persistence.entity.security.User;
import com.example.persistence.repository.security.OperationRepository;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final  OperationRepository operationRepository;
    private final  UserService userService;
    @Autowired
    public CustomAuthorizationManager(OperationRepository operationRepository, UserService userService) {
        this.operationRepository = operationRepository;
        this.userService = userService;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication,
                                       RequestAuthorizationContext requestContext) {


        HttpServletRequest request= requestContext.getRequest();
        String url = extractUrl(request);
        String method=request.getMethod();

        if (isPublic(url,method)) return new AuthorizationDecision(true);

        return new AuthorizationDecision(isGranted(url,method,authentication.get()));
    }
    private String extractUrl(HttpServletRequest request) {
        String contextPath= request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath,"");
        System.out.println(url);
        return url;
    }
    private boolean isPublic (String url,String method){
        return operationRepository.findByPublicAccess()
                .stream().anyMatch(getOperationPredicate(url,method));
    }
    private boolean isGranted (String url,String method, Authentication authentication){
        if (authentication == null) {
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }
        if (!(authentication instanceof UsernamePasswordAuthenticationToken) ) {
            System.out.println("Authentication instance: " + authentication.getClass().getName());
            throw new AuthenticationCredentialsNotFoundException("User not logged in, not instanceof UsernamePasswordAuthenticationToken");
        }
        boolean isGranted=obtainedOperations(authentication).stream()
                .anyMatch(getOperationPredicate(url, method));
        System.out.println("IS GRANTED: "+isGranted);
        return isGranted;
    }
    private List<Operation> obtainedOperations(Authentication authentication) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)  authentication;
        String username=(String) authenticationToken.getPrincipal();
        User user=userService.findOneByUsername(username);
        return  user.getRole().getPermissions()
                .stream().map(GrantedPermission::getOperation)
                .toList();
    }
    private static Predicate<Operation> getOperationPredicate(String url, String method) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();

            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));

            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(method);
        };
    }




}
