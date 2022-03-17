package com.zahaand.webapp;

import com.zahaand.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact(ResumeContacts.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContact(ResumeContacts.SKYPE, "grigory.kislin");
        resume.setContact(ResumeContacts.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ResumeContacts.LINKEDIN, "Профиль LinkedIn");
        resume.setContact(ResumeContacts.GITHUB, "Профиль GitHub");
        resume.setContact(ResumeContacts.STACKOVERFLOW, "Профиль Stackoverflow");
        resume.setContact(ResumeContacts.HOMEPAGE, "Домашняя страница");

        AbstractSection objective = new SimpleLineSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSectionData(ResumeSections.OBJECTIVE, objective);

        AbstractSection personal = new SimpleLineSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSectionData(ResumeSections.PERSONAL, personal);

        List<String> achievementList = new ArrayList<>() {{
            add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
            add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n");
            add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n");
            add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n");
            add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n");
            add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");
        }};
        AbstractSection achievement = new BulletedListSection(achievementList);
        resume.setSectionData(ResumeSections.ACHIEVEMENT, achievement);

        List<String> qualificationsList = new ArrayList<>() {{
            add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n");
            add("Version control: Subversion, Git, Mercury, ClearCase, Perforce\n");
            add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,\n");
            add("MySQL, SQLite, MS SQL, HSQLDB\n");
            add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n");
            add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n");
            add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
            add("Python: Django.\n");
            add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n");
            add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n");
            add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\n");
            add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,\n");
            add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.\n");
            add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования\n");
            add("Родной русский, английский \"upper intermediate\"");
        }};
        AbstractSection qualifications = new BulletedListSection(qualificationsList);
        resume.setSectionData(ResumeSections.QUALIFICATIONS, qualifications);

        String companyName1 = "Java Online Projects";
        String text1 = "Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.";
        TimeIntervalSection timeInterval1 = new TimeIntervalSection(companyName1, text1, LocalDate.of(2013, 10, 1), LocalDate.now());

        String companyName2 = "Wrike";
        String text2 = "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        TimeIntervalSection timeInterval2 = new TimeIntervalSection(companyName2, text2, LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1));

        String companyName3 = "RIT Center";
        String text3 = "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        TimeIntervalSection timeInterval3 = new TimeIntervalSection(companyName3, text3, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));

        String companyName4 = "Luxoft (Deutsche Bank)";
        String text4 = "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.";
        TimeIntervalSection timeInterval4 = new TimeIntervalSection(companyName4, text4, LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1));

        String companyName5 = "Yota";
        String text5 = "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)\n";
        TimeIntervalSection timeInterval5 = new TimeIntervalSection(companyName5, text5, LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));

        String companyName6 = "Enkata";
        String text6 = "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).";
        TimeIntervalSection timeInterval6 = new TimeIntervalSection(companyName6, text6, LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1));

        String companyName7 = "Siemens AG";
        String text7 = "Java архитектор\n" + "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        TimeIntervalSection timeInterval7 = new TimeIntervalSection(companyName7, text7, LocalDate.of(2005, 1, 1), LocalDate.of(2007, 02, 1));

        String companyName8 = "Alcatel";
        String text8 = "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).";
        TimeIntervalSection timeInterval8 = new TimeIntervalSection(companyName8, text8, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));

        List<TimeIntervalSection> experience = new ArrayList<>() {{
            add(timeInterval1);
            add(timeInterval2);
            add(timeInterval3);
            add(timeInterval4);
            add(timeInterval5);
            add(timeInterval6);
            add(timeInterval7);
            add(timeInterval8);
        }};
        resume.setExperience(experience);
    }
}
