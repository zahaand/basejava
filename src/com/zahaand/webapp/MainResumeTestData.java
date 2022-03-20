package com.zahaand.webapp;

import com.zahaand.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "Профиль LinkedIn");
        resume.setContact(ContactType.GITHUB, "Профиль GitHub");
        resume.setContact(ContactType.STACKOVERFLOW, "Профиль Stackoverflow");
        resume.setContact(ContactType.HOMEPAGE, "Домашняя страница");

        AbstractSection objective = new SimpleLineSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSectionData(SectionType.OBJECTIVE, objective);

        AbstractSection personal = new SimpleLineSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSectionData(SectionType.PERSONAL, personal);

        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSectionData(SectionType.ACHIEVEMENT, new BulletedListSection(achievements));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.setSectionData(SectionType.QUALIFICATIONS, new BulletedListSection(qualifications));

        String companyName1 = "Java Online Projects";
        String position1 = "Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.";
        Experience experience1 = new Experience(companyName1, position1, LocalDate.of(2013, 10, 1), LocalDate.now());

        String companyName2 = "Wrike";
        String position2 = "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        Experience experience2 = new Experience(companyName2, position2, LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1));

        String companyName3 = "RIT Center";
        String position3 = "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        Experience experience3 = new Experience(companyName3, position3, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));

        String companyName4 = "Luxoft (Deutsche Bank)";
        String position4 = "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.";
        Experience experience4 = new Experience(companyName4, position4, LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1));

        String companyName5 = "Yota";
        String position5 = "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)\n";
        Experience experience5 = new Experience(companyName5, position5, LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));

        String companyName6 = "Enkata";
        String position6 = "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).";
        Experience experience6 = new Experience(companyName6, position6, LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1));

        String companyName7 = "Siemens AG";
        String position7 = "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        Experience experience7 = new Experience(companyName7, position7, LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1));

        String companyName8 = "Alcatel";
        String position8 = "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).";
        Experience experience8 = new Experience(companyName8, position8, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));

        List<Experience> experiences = new ArrayList<>();
        experiences.add(experience1);
        experiences.add(experience2);
        experiences.add(experience3);
        experiences.add(experience4);
        experiences.add(experience5);
        experiences.add(experience6);
        experiences.add(experience7);
        experiences.add(experience8);
        resume.setSectionData(SectionType.EXPERIENCE, new Organization(experiences));

        String schoolName1 = "Coursera";
        String courseName1 = "\"Functional Programming Principles in Scala\" by Martin Odersky";
        Experience education1 = new Experience(schoolName1, courseName1, LocalDate.of(2011, 3, 1), LocalDate.of(2013, 5, 1));

        String schoolName2 = "Luxoft";
        String courseName2 = "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"";
        Experience education2 = new Experience(schoolName2, courseName2, LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1));

        String schoolName3 = "Siemens AG";
        String courseName3 = "3 месяца обучения мобильным IN сетям (Берлин)";
        Experience education3 = new Experience(schoolName3, courseName3, LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1));

        String schoolName4 = "Alcatel";
        String courseName4 = "6 месяцев обучения цифровым телефонным сетям (Москва)";
        Experience education4 = new Experience(schoolName4, courseName4, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));

        String schoolName5 = "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики";
        String courseName5 = "Аспирантура (программист С, С++)";
        Experience education5 = new Experience(schoolName5, courseName5, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));

        String schoolName6 = "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики";
        String courseName6 = "Инженер (программист Fortran, C)";
        Experience education6 = new Experience(schoolName6, courseName6, LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1));

        String schoolName7 = "Заочная физико-техническая школа при МФТИ";
        String courseName7 = "Закончил с отличием";
        Experience education7 = new Experience(schoolName7, courseName7, LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1));

        List<Experience> educations = new ArrayList<>();
        educations.add(education1);
        educations.add(education2);
        educations.add(education3);
        educations.add(education4);
        educations.add(education5);
        educations.add(education6);
        educations.add(education7);
        resume.setSectionData(SectionType.EDUCATION, new Organization(educations));

        System.out.println(resume);
    }
}
