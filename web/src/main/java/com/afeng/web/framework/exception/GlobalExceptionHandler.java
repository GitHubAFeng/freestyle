package com.afeng.web.framework.exception;

import com.afeng.web.common.util.ServletUtils;
import com.afeng.web.framework.core.constant.ApiResult;
import com.afeng.web.framework.filter.ApiInterceptor;
import com.afeng.web.module.admin.model.AjaxResult;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常捕捉处理
     */
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        log.debug(e.getMessage(), e);
        if (request.getRequestURI().startsWith("/" + ApiInterceptor.API_PATH)) {
            ModelAndView view = new ModelAndView();
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            jsonView.setAttributesMap(JSON.parseObject(JSON.toJSONString(ApiResult.error("异常错误"))));
            view.setView(jsonView);
            return view;
        } else {
            if (ServletUtils.isAjaxRequest(request)) {
                ModelAndView view = new ModelAndView();
                MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
                jsonView.setAttributesMap(JSON.parseObject(JSON.toJSONString(AjaxResult.error("异常错误"))));
                view.setView(jsonView);
                return view;
            } else {
                ModelAndView view = new ModelAndView();
                view.setViewName("redirect:/500.html");
                return view;
            }
        }
    }

    /**
     * 处理API自定义异常
     */
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ApiResult handleApiException(ApiException e) {
        log.debug(e.getMessage(), e);
        return ApiResult.error(e.getMsg());
    }

    /**
     * 处理ADMIN自定义异常
     */
    @ExceptionHandler(AdminException.class)
    public ModelAndView handleAdminException(HttpServletRequest request, AdminException e) {
        log.debug(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView();
        if (e.getCode() == HttpStatus.FORBIDDEN.value()) {
            if (ServletUtils.isAjaxRequest(request)) {
                ModelAndView view = new ModelAndView();
                MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
                jsonView.setAttributesMap(JSON.parseObject(JSON.toJSONString(AjaxResult.error(e.getMsg()))));
                view.setView(jsonView);
                return view;
            } else {
                ModelAndView view = new ModelAndView();
                view.setViewName("redirect:/403.html");
                return view;
            }
        }

        return modelAndView;
    }

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    public ApiResult handleDuplicateKeyException(DuplicateKeyException e) {
        log.debug(e.getMessage(), e);
        return ApiResult.error("数据库中已存在该记录");
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult handlerNoFoundException(NoHandlerFoundException e) {
        log.debug(e.getMessage(), e);
        return ApiResult.error("路径不存在，请检查路径是否正确");
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult handleException(HttpRequestMethodNotSupportedException e) {
        log.debug(e.getMessage(), e);
        return ApiResult.error("请求方法错误");
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ApiResult handle(ValidationException exception) {
        log.debug(exception.getMessage(), exception);
        String msg = "参数错误";
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                if (StringUtils.isNotEmpty(item.getMessage())) {
                    msg = item.getMessage();
                    break;
                }
            }
        } else {
            msg = exception.getMessage();
        }
        return ApiResult.error(msg);
    }

}
