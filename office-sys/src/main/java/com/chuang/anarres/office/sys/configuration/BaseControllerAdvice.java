package com.chuang.anarres.office.sys.configuration;

import com.chuang.anarres.office.sys.exception.PageException;
import com.chuang.tauceti.support.MapResult;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.StringKit;
import com.chuang.tauceti.tools.third.servlet.HttpKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class BaseControllerAdvice {

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exception(Exception exception) throws PageException {
        boolean isPage = HttpKit.getRequest()
                .map(request -> {
                    Object b = request.getAttribute(PageOrRestInterceptor.PAGE_VIEW_ATTR);
                    return null != b && (Boolean) b;
                })
                .orElse(false);//如果没有记录，默认不是页面
        if(isPage) {
            throw new PageException(exception);
        }
        return jsonException(exception);
    }

    @ExceptionHandler(value = PageException.class)
    public Object exception(PageException exception) {
        return pageException((Exception) exception.getCause());
    }

    private Result<?> jsonException(Exception exception) {
        if(exception instanceof ShiroException) {
            return MapResult.fail( "您的权限不够：" + exception.getMessage())
                    .data("refreshInfo", true)
                    .toResult();
        } if (exception instanceof ConstraintViolationException) {
            Locale local = (Locale) SecurityUtils.getSubject().getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
            ConstraintViolationException ex = (ConstraintViolationException) exception;
            String msg = ex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessageTemplate)
                    .map(String::trim)
                    .map(_msg -> messageSource.getMessage(_msg.substring(1, _msg.length() - 1), null, local) + ",")
                    .collect(Collectors.joining());
            return Result.fail(msg);
        } else if (exception instanceof BindException) {
            BindingResult bindingResult = ((BindException) exception).getBindingResult();
            if (bindingResult.getFieldError() == null) {
                return Result.fail("参数校验失败");
            } else {
                return Result.fail(StringKit.nullToEmpty(bindingResult.getFieldError().getDefaultMessage()));
            }
        } else if(exception instanceof BusinessException |
                exception instanceof HttpRequestMethodNotSupportedException |
                exception instanceof MethodArgumentNotValidException) {
            return Result.fail(exception.getMessage());
        }

        String err = "Ex:" + UUID.randomUUID();
        log.error("controller异常, 错误号:" + err, exception);
        return Result.fail("系统异常,请联系开发人员!异常号:" + err);
    }

    private ModelAndView pageException(Exception exception) {
        ModelAndView view = new ModelAndView();
        view.setStatus(HttpStatus.BAD_REQUEST);
        view.setViewName("/500");
        view.addObject("error", exception.getMessage());
        return view;
    }

}
