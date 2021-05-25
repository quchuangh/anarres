package com.chuang.anarres.web.starter.log;//package com.chuang.anarres.rbac.aspect.log.operation;
//
//import com.chuang.tauceti.tools.third.servlet.HttpKit;
//import com.google.common.base.Strings;
//import com.google.common.collect.Maps;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.annotation.Order;
//import org.springframework.expression.Expression;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.expression.spel.support.StandardEvaluationContext;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Parameter;
//import java.time.LocalDateTime;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Slf4j
//@Getter
//@Setter
//@Aspect
//@Order
//public class OperationLogAspect {
//    private static final Pattern IS_EXPRESSION = Pattern.compile("#\\{(.*)}");
//    private static final String REPLACE_SENSITIVES = "****";
//
//    @Around(value = "@annotation(operationLog)")
//    public Object around(ProceedingJoinPoint point, OptLog operationLog) {
//        return HttpKit.getRequest().map(request -> {
//            Object result = null;
//            OptLog operationLogVO = new OperationLog();
//            try {
//                //  before
//                this.doBefore(point, operationLog, operationLogVO, request);
//                try {
//                    //  execution method
//                    result = point.proceed();
//                } finally {
//                    //  after
//                    this.doAfter(result, operationLog, operationLogVO, request);
//                }
//            } catch (Throwable throwable) {
//                //  throwing
//                this.doAfterThrowing(operationLog, operationLogVO, request, throwable);
//
//                //  returning throwing
//                return throwable;
//            }
//            //  returning
//            return result;
//        }).orElseGet(() -> {
//            try {
//                //  before
//                this.doBefore(point, operationLog, operationLogVO, request);
//                try {
//                    //  execution method
//                    result = point.proceed();
//                } finally {
//                    //  after
//                    this.doAfter(result, operationLog, operationLogVO, request);
//                }
//            } catch (Throwable throwable) {
//                //  throwing
//                this.doAfterThrowing(operationLog, operationLogVO, request, throwable);
//
//                //  returning throwing
//                return throwable;
//            }
//        });
//    }
//
//    protected void doBefore(ProceedingJoinPoint joinPoint,
//                            OptLog operationLog,
//                            OptLog operationLogVO,
//                            HttpServletRequest request) {
//        //  是否输出前置日志
//        if (!operationLogProperties.isBefore()) {
//            return;
//        }
//
//        //  SpEL 表达式中读取操作人
//        String operator = this.obtainOperator(joinPoint, operationLogVO.getOperator());
//        //  入参
//        String incomingParams = this.obtainIncomingParams(joinPoint);
//        String ipAddress = RequestUtils.getIpAddress(request);
//
//        log.info("(Theme-{}, Type-{}, Operator-{}), Enter the operation log aspect the before. " +
//                        "Host: {}, URI:{}, Method:{}, IP:{}, IncomingParams:{}",
//                operationLog.theme(), operationLog.type(), operator, request.getServerName(), request.getRequestURI(),
//                request.getMethod(), ipAddress, incomingParams);
//
//        //  是否持久化
//        if (operationLogProperties.isEndurance() && !operationLog.indefinite()) {
//            //  检查日志持久化接口是否现实
//            this.checkEnduranceInterface();
//
//            operationLogVO.setTheme(operationLog.theme());
//            operationLogVO.setOperator(operator);
//            operationLogVO.setOperationBeginTime(LocalDateTime.now());
//            operationLogVO.setIncomingParams(incomingParams);
//            operationLogVO.setClientIp(ipAddress);
//            operationLogVO.setRequestHost(request.getServerName());
//            operationLogVO.setRequestUrl(request.getRequestURI());
//            operationLogVO.setRequestMethod(request.getMethod());
//            operationLogVO.setRemark(operationLog.theme());
//
//            Integer id = this.operationLogService.save(operationLogVO);
//
//            operationLogVO.setId(id);
//        }
//    }
//
//    protected void doAfter(Object result,
//                           IOperationLog operationLog,
//                           OptLog operationLogVO,
//                           HttpServletRequest request) {
//
//        if (!operationLogProperties.isAfter()) {
//            return;
//        }
//
//        String ipAddress = RequestUtils.getIpAddress(request);
//        String resultParams = this.gson.toJson(result);
//
//        log.info("(Theme-{}, Type-{}, Operator-{}), Enter the operation log aspect the after. " +
//                        "Host: {}, URI:{}, Method:{}, IP:{}, ResultParams:{}",
//                operationLog.theme(), operationLog.type(), operationLogVO.getOperator(), request.getServerName(),
//                request.getRequestURI(), request.getMethod(), ipAddress, resultParams);
//
//        if (operationLogProperties.isEndurance() && !operationLog.indefinite()) {
//            this.checkEnduranceInterface();
//
//            OptLog operationLog1 = new OptLog();
//            operationLog1.setId(operationLogVO.getId());
//            operationLog1.setOperationEndTime(LocalDateTime.now());
//            operationLog1.setResultParams(resultParams);
//            operationLog1.setSuccess(Boolean.TRUE);
//
//            this.operationLogService.updateById(operationLog1);
//        }
//    }
//
//    protected void doAfterThrowing(IOperationLog operationLog,
//                                   OptLog operationLogVO,
//                                   HttpServletRequest request,
//                                   Throwable throwable) {
//
//        if (!operationLogProperties.isAfterThrowing()) {
//            return;
//        }
//
//        String ipAddress = RequestUtils.getIpAddress(request);
//        log.info("(Theme-{}, Type-{}, Operator-{}), Enter the operation log aspect the after throwing. " +
//                        "Host: {}, URI:{}, Method:{}, IP:{}, Message:{}",
//                operationLog.theme(), operationLog.type(), operationLogVO.getOperator(), request.getServerName(),
//                request.getRequestURI(), request.getMethod(), ipAddress, throwable.getMessage(), throwable);
//
//        if (operationLogProperties.isEndurance() && !operationLog.indefinite()) {
//            this.checkEnduranceInterface();
//
//            String message = throwable.getMessage();
//
//            OptLog operationLog1 = new OptLog();
//            operationLog1.setId(operationLogVO.getId());
//            operationLog1.setException(message);
//            operationLog1.setSuccess(Boolean.FALSE);
//
//            this.operationLogService.updateById(operationLog1);
//        }
//    }
//
//    private void checkEnduranceInterface() {
//        if (null == operationLogService) {
//            throw new SystemErrorException("Custom implementation interface: com.tellus.config.operationlog" +
//                    ".OperationLogService, Or set 'tellus.operation.endurance' to 'false'");
//        }
//    }
//
//    private String obtainIncomingParams(JoinPoint joinPoint) {
//        Parameter[] parameters = ((MethodSignature) joinPoint.getStaticPart().getSignature())
//                .getMethod().getParameters();
//        Object[] args = joinPoint.getArgs();
//
//        Map<String, Object> paramMap = Maps.newConcurrentMap();
//        for (int i = 0; i < parameters.length; i++) {
//            String name = parameters[i].getName();
//            if (sensitiveClasses.contains(parameters[i].getParameterizedType().getTypeName())) {
//                continue;
//            }
//
//            //  过滤信息
//            IOperationLog.Exclusion exclusion = parameters[i].getAnnotation(IOperationLog.Exclusion.class);
//            if (null != exclusion) {
//                paramMap.put(parameters[i].getName(), REPLACE_SENSITIVES);
//                continue;
//            }
//            if (this.operationLogProperties.getSensitives().contains(name)) {
//                paramMap.put(parameters[i].getName(), REPLACE_SENSITIVES);
//                continue;
//            }
//            paramMap.put(parameters[i].getName(), args[i]);
//        }
//
//        return gson.toJson(paramMap);
//    }
//
//    private String obtainOperator(ProceedingJoinPoint point, String expression) {
//        if (Strings.isNullOrEmpty(expression)) {
//            return expression;
//        }
//        Matcher matcher = IS_EXPRESSION.matcher(expression);
//        if (matcher.find()) {
//            String value = matcher.group(1).trim();
//            if (!Strings.isNullOrEmpty(value)) {
//                Expression exp = new SpelExpressionParser().parseExpression(value);
//                Signature signature = point.getSignature();
//                MethodSignature methodSignature = (MethodSignature) signature;
//                String[] names = methodSignature.getParameterNames();
//                Object[] args = point.getArgs();
//                StandardEvaluationContext context = new StandardEvaluationContext();
//                context.setVariable("args", args);
//                for (int i = 0; i < names.length; i++) {
//                    context.setVariable(names[i], args[i]);
//                }
//                return exp.getValue(context, String.class);
//            }
//        }
//        return expression;
//    }
//}
