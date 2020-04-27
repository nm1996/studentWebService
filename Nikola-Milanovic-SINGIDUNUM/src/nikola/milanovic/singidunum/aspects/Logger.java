package nikola.milanovic.singidunum.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
	@Pointcut("execution(* nikola.milanovic.singidunum.dao.CityDAO.*(..))")
    public void cityDAO() {
    }
    @Before("cityDAO()")
    public void beforeCity() {
        System.out.println("City transaction began");
    }
    @After("cityDAO()")
    public void afterCity() {
        System.out.println("City transaction finished");
    }

    @AfterThrowing("cityDAO()")
    public void throwCity() {
        System.out.println("City transaction throw");
    }
    
    @Pointcut("execution(* nikola.milanovic.singidunum.dao.TitleDAO.*(..))")
    public void titleDAO() {
    }
    @Before("titleDAO()")
    public void beforeTitle() {
        System.out.println("Title transaction began");
    }
    @After("titleDAO()")
    public void afterTitle() {
        System.out.println("Title transaction finished");
    }

    @AfterThrowing("titleDAO()")
    public void throwTitle() {
        System.out.println("Title transaction throw");
    }
    
    @Pointcut("execution(* nikola.milanovic.singidunum.dao.StudentDAO.*(..))")
    public void studentDAO() {
    }
    @Before("studentDAO()")
    public void beforeStudent() {
        System.out.println("Student transaction began");
    }
    @After("studentDAO()")
    public void afterStudent() {
        System.out.println("Student transaction finished");
    }

    @AfterThrowing("studentDAO()")
    public void throwStudent() {
        System.out.println("Student transaction throw");
    }
    
    @Pointcut("execution(* nikola.milanovic.singidunum.dao.ProfessorDAO.*(..))")
    public void professorDAO() {
    }
    @Before("professorDAO()")
    public void beforeProfessor() {
        System.out.println("Professor transaction began");
    }
    @After("professorDAO()")
    public void afterProfessor() {
        System.out.println("Professor transaction finished");
    }

    @AfterThrowing("professorDAO()")
    public void throwProfessor() {
        System.out.println("Professor transaction throw");
    }
    
    @Pointcut("execution(* nikola.milanovic.singidunum.dao.ExamDAO.*(..))")
    public void examDAO() {
    }
    @Before("examDAO()")
    public void beforeExam() {
        System.out.println("Exam transaction began");
    }
    @After("examDAO()")
    public void afterExam() {
        System.out.println("Exam transaction finished");
    }

    @AfterThrowing("examDAO()")
    public void throwExam() {
        System.out.println("Exam transaction throw");
    }
    
    @Pointcut("execution(* nikola.milanovic.singidunum.dao.MarkDAO.*(..))")
    public void markDAO() {
    }
    @Before("markDAO()")
    public void beforeMark() {
        System.out.println("Mark transaction began");
    }
    @After("markDAO()")
    public void afterMark() {
        System.out.println("Mark transaction finished");
    }

    @AfterThrowing("markDAO()")
    public void throwMark() {
        System.out.println("Mark transaction throw");
    }
    
    @Pointcut("execution(* nikola.milanovic.singidunum.dao.SubjectDAO.*(..))")
    public void subjectDAO() {
    }
    @Before("subjectDAO()")
    public void beforeSubject() {
        System.out.println("Subject transaction began");
    }
    @After("subjectDAO()")
    public void afterSubject() {
        System.out.println("Subject transaction finished");
    }

    @AfterThrowing("subjectDAO()")
    public void throwSubject() {
        System.out.println("Subject transaction throw");
    }
}
