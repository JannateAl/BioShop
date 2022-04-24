package com.example.BioShop.AOP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Aspect
@Component
public class AopLogger {

    private static final Logger logger = LogManager.getLogger(AopLogger.class);


    @AfterReturning(pointcut = "execution(* * ..controllers.*.create*(..)) && !execution(* * ..controllers.Authentication.create*(..))",returning = "object")
    public void afterCreating(JoinPoint joinPoint,Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            Long Id = (Long) object.getClass().getMethod("getId").invoke(object);

            String Nom = (String) object.getClass().getMethod("getNom").invoke(object);

            String message = joinPoint.getSignature().toString().split(" ")[0] + " created : " + " id = " + Id + "; nom = " + Nom;

            logger.warn(message);
    }

    @Before("execution(* * ..controllers.*.delete*(..) ) && args(id,..)")
    public void beforeDeleting(JoinPoint joinPoint, Long id){

        String name = joinPoint.getSignature().toString().split("delete")[1].split("\\(")[0];

        String message=name+" deleted : "+" id = "+id;

        logger.warn(message);
    }

    @Before("execution(* * ..controllers.*.getProduit*(..)) && args(catNom,..)")
    public void beforchingSearchingForProduct(JoinPoint joinPoint,String catNom){

        String name = joinPoint.getSignature().toString().split("Par")[1].split("\\(String")[0];

        String message = "Recherche par ";

        if(name.split("And").length == 2){

            String coop = joinPoint.getArgs()[1].toString();

            message = message + "la categorie \""+catNom+"\" et la cooperative \""+coop+"\"";
        }else{

            message = message + name + " : "+catNom;
        }

        logger.info(message);
    }
}