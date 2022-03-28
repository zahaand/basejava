package com.zahaand.webapp;

import com.zahaand.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTestResumeData {

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

        Link companyLink1 = new Link("Java Online Projects", null);
        String position1 = "Автор проекта.";
        String description1 = "Создание, организация и проведение Java онлайн проектов и стажировок.";
        Experience workExperience1 = new Experience(position1, description1, LocalDate.of(2013, 10, 1), LocalDate.now());
        List<Experience> workExperiences1 = new ArrayList<>();
        workExperiences1.add(workExperience1);
        Organization organization1 = new Organization(companyLink1, workExperiences1);

        Link companyLink2 = new Link("Wrike", null);
        String position2 = "Старший разработчик (backend)";
        String description2 = "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        Experience workExperience2 = new Experience(position2, description2, LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1));
        List<Experience> workExperiences2 = new ArrayList<>();
        workExperiences2.add(workExperience2);
        Organization organization2 = new Organization(companyLink2, workExperiences2);

        Link companyLink3 = new Link("RIT Center", null);
        String position3 = "Java архитектор";
        String description3 = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        Experience workExperience3 = new Experience(position3, description3, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));
        List<Experience> workExperiences3 = new ArrayList<>();
        workExperiences3.add(workExperience3);
        Organization organization3 = new Organization(companyLink3, workExperiences3);

        Link companyLink4 = new Link("Luxoft (Deutsche Bank)", null);
        String position4 = "Ведущий программист";
        String description4 = "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.";
        Experience workExperience4 = new Experience(position4, description4, LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1));
        List<Experience> workExperiences4 = new ArrayList<>();
        workExperiences4.add(workExperience4);
        Organization organization4 = new Organization(companyLink4, workExperiences4);

        Link companyLink5 = new Link("Yota", null);
        String position5 = "Ведущий специалист";
        String description5 = "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)";
        Experience workExperience5 = new Experience(position5, description5, LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));
        List<Experience> workExperiences5 = new ArrayList<>();
        workExperiences5.add(workExperience5);
        Organization organization5 = new Organization(companyLink5, workExperiences5);

        Link companyLink6 = new Link("Enkata", null);
        String position6 = "Разработчик ПО";
        String description6 = "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).";
        Experience workExperience6 = new Experience(position6, description6, LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1));
        List<Experience> workExperiences6 = new ArrayList<>();
        workExperiences6.add(workExperience6);
        Organization organization6 = new Organization(companyLink6, workExperiences6);

        Link companyLink7 = new Link("Siemens AG", null);
        String position7 = "Java архитектор";
        String description7 = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        Experience workExperience7 = new Experience(position7, description7, LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1));
        List<Experience> workExperiences7 = new ArrayList<>();
        workExperiences7.add(workExperience7);
        Organization organization7 = new Organization(companyLink7, workExperiences7);

        Link companyLink8 = new Link("Alcatel", null);
        String position8 = "Инженер по аппаратному и программному тестированию";
        String description8 = "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).";
        Experience workExperience8 = new Experience(position8, description8, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));
        List<Experience> workExperiences8 = new ArrayList<>();
        workExperiences8.add(workExperience8);
        Organization organization8 = new Organization(companyLink8, workExperiences8);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);
        organizations.add(organization3);
        organizations.add(organization4);
        organizations.add(organization5);
        organizations.add(organization6);
        organizations.add(organization7);
        organizations.add(organization8);
        resume.setSectionData(SectionType.EXPERIENCE, new OrganizationsList(organizations));

        Link courseLink1 = new Link("Coursera", null);
        String courseName1 = "\"Functional Programming Principles in Scala\" by Martin Odersky";
        String courseDescription1 = null;
        Experience education1 = new Experience(courseName1, courseDescription1, LocalDate.of(2011, 3, 1), LocalDate.of(2013, 5, 1));
        List<Experience> finishedCourses1 = new ArrayList<>();
        finishedCourses1.add(education1);
        Organization educationalInstitution1 = new Organization(courseLink1, finishedCourses1);

        Link courseLink2 = new Link("Luxoft", null);
        String courseName2 = "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"";
        String courseDescription2 = null;
        Experience education2 = new Experience(courseName2, courseDescription2, LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1));
        List<Experience> finishedCourses2 = new ArrayList<>();
        finishedCourses2.add(education2);
        Organization educationalInstitution2 = new Organization(courseLink2, finishedCourses2);

        Link courseLink3 = new Link("Siemens AG", null);
        String courseName3 = "3 месяца обучения мобильным IN сетям (Берлин)";
        String courseDescription3 = null;
        Experience education3 = new Experience(courseName3, courseDescription3, LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1));
        List<Experience> finishedCourses3 = new ArrayList<>();
        finishedCourses3.add(education3);
        Organization educationalInstitution3 = new Organization(courseLink3, finishedCourses3);

        Link courseLink4 = new Link("Alcatel", null);
        String courseName4 = "6 месяцев обучения цифровым телефонным сетям (Москва)";
        String courseDescription4 = null;
        Experience education4 = new Experience(courseName4, courseDescription4, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));
        List<Experience> finishedCourses4 = new ArrayList<>();
        finishedCourses4.add(education4);
        Organization educationalInstitution4 = new Organization(courseLink4, finishedCourses4);

        Link courseLink5 = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", null);
        String courseName5 = "Аспирантура (программист С, С++)";
        String courseDescription5 = null;
        Experience education5 = new Experience(courseName5, courseDescription5, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));
        List<Experience> finishedCourses5 = new ArrayList<>();
        finishedCourses5.add(education5);

        String courseName6 = "Инженер (программист Fortran, C)";
        String courseDescription6 = null;
        Experience education6 = new Experience(courseName6, courseDescription6, LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1));
        finishedCourses5.add(education6);
        Organization educationalInstitution5 = new Organization(courseLink5, finishedCourses5);

        Link courseLink7 = new Link("Заочная физико-техническая школа при МФТИ", null);
        String courseName7 = "Закончил с отличием";
        String courseDescription7 = null;
        Experience education7 = new Experience(courseName7, courseDescription7, LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1));
        List<Experience> finishedCourses7 = new ArrayList<>();
        finishedCourses7.add(education7);
        Organization educationalInstitution7 = new Organization(courseLink7, finishedCourses7);

        List<Organization> educations = new ArrayList<>();
        educations.add(educationalInstitution1);
        educations.add(educationalInstitution2);
        educations.add(educationalInstitution3);
        educations.add(educationalInstitution4);
        educations.add(educationalInstitution5);
        educations.add(educationalInstitution7);
        resume.setSectionData(SectionType.EDUCATION, new OrganizationsList(educations));

        System.out.println(resume);
    }

    public Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

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

        Link companyLink1 = new Link("Java Online Projects", null);
        String position1 = "Автор проекта.";
        String description1 = "Создание, организация и проведение Java онлайн проектов и стажировок.";
        Experience workExperience1 = new Experience(position1, description1, LocalDate.of(2013, 10, 1), LocalDate.now());
        List<Experience> workExperiences1 = new ArrayList<>();
        workExperiences1.add(workExperience1);
        Organization organization1 = new Organization(companyLink1, workExperiences1);

        Link companyLink2 = new Link("Wrike", null);
        String position2 = "Старший разработчик (backend)";
        String description2 = "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        Experience workExperience2 = new Experience(position2, description2, LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1));
        List<Experience> workExperiences2 = new ArrayList<>();
        workExperiences2.add(workExperience2);
        Organization organization2 = new Organization(companyLink2, workExperiences2);

        Link companyLink3 = new Link("RIT Center", null);
        String position3 = "Java архитектор";
        String description3 = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        Experience workExperience3 = new Experience(position3, description3, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));
        List<Experience> workExperiences3 = new ArrayList<>();
        workExperiences3.add(workExperience3);
        Organization organization3 = new Organization(companyLink3, workExperiences3);

        Link companyLink4 = new Link("Luxoft (Deutsche Bank)", null);
        String position4 = "Ведущий программист";
        String description4 = "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.";
        Experience workExperience4 = new Experience(position4, description4, LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1));
        List<Experience> workExperiences4 = new ArrayList<>();
        workExperiences4.add(workExperience4);
        Organization organization4 = new Organization(companyLink4, workExperiences4);

        Link companyLink5 = new Link("Yota", null);
        String position5 = "Ведущий специалист";
        String description5 = "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)";
        Experience workExperience5 = new Experience(position5, description5, LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));
        List<Experience> workExperiences5 = new ArrayList<>();
        workExperiences5.add(workExperience5);
        Organization organization5 = new Organization(companyLink5, workExperiences5);

        Link companyLink6 = new Link("Enkata", null);
        String position6 = "Разработчик ПО";
        String description6 = "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).";
        Experience workExperience6 = new Experience(position6, description6, LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1));
        List<Experience> workExperiences6 = new ArrayList<>();
        workExperiences6.add(workExperience6);
        Organization organization6 = new Organization(companyLink6, workExperiences6);

        Link companyLink7 = new Link("Siemens AG", null);
        String position7 = "Java архитектор";
        String description7 = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        Experience workExperience7 = new Experience(position7, description7, LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1));
        List<Experience> workExperiences7 = new ArrayList<>();
        workExperiences7.add(workExperience7);
        Organization organization7 = new Organization(companyLink7, workExperiences7);

        Link companyLink8 = new Link("Alcatel", null);
        String position8 = "Инженер по аппаратному и программному тестированию";
        String description8 = "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).";
        Experience workExperience8 = new Experience(position8, description8, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));
        List<Experience> workExperiences8 = new ArrayList<>();
        workExperiences8.add(workExperience8);
        Organization organization8 = new Organization(companyLink8, workExperiences8);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);
        organizations.add(organization3);
        organizations.add(organization4);
        organizations.add(organization5);
        organizations.add(organization6);
        organizations.add(organization7);
        organizations.add(organization8);
        resume.setSectionData(SectionType.EXPERIENCE, new OrganizationsList(organizations));

        Link courseLink1 = new Link("Coursera", null);
        String courseName1 = "\"Functional Programming Principles in Scala\" by Martin Odersky";
        String courseDescription1 = null;
        Experience education1 = new Experience(courseName1, courseDescription1, LocalDate.of(2011, 3, 1), LocalDate.of(2013, 5, 1));
        List<Experience> finishedCourses1 = new ArrayList<>();
        finishedCourses1.add(education1);
        Organization educationalInstitution1 = new Organization(courseLink1, finishedCourses1);

        Link courseLink2 = new Link("Luxoft", null);
        String courseName2 = "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"";
        String courseDescription2 = null;
        Experience education2 = new Experience(courseName2, courseDescription2, LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1));
        List<Experience> finishedCourses2 = new ArrayList<>();
        finishedCourses2.add(education2);
        Organization educationalInstitution2 = new Organization(courseLink2, finishedCourses2);

        Link courseLink3 = new Link("Siemens AG", null);
        String courseName3 = "3 месяца обучения мобильным IN сетям (Берлин)";
        String courseDescription3 = null;
        Experience education3 = new Experience(courseName3, courseDescription3, LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1));
        List<Experience> finishedCourses3 = new ArrayList<>();
        finishedCourses3.add(education3);
        Organization educationalInstitution3 = new Organization(courseLink3, finishedCourses3);

        Link courseLink4 = new Link("Alcatel", null);
        String courseName4 = "6 месяцев обучения цифровым телефонным сетям (Москва)";
        String courseDescription4 = null;
        Experience education4 = new Experience(courseName4, courseDescription4, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));
        List<Experience> finishedCourses4 = new ArrayList<>();
        finishedCourses4.add(education4);
        Organization educationalInstitution4 = new Organization(courseLink4, finishedCourses4);

        Link courseLink5 = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", null);
        String courseName5 = "Аспирантура (программист С, С++)";
        String courseDescription5 = null;
        Experience education5 = new Experience(courseName5, courseDescription5, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));
        List<Experience> finishedCourses5 = new ArrayList<>();
        finishedCourses5.add(education5);

        String courseName6 = "Инженер (программист Fortran, C)";
        String courseDescription6 = null;
        Experience education6 = new Experience(courseName6, courseDescription6, LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1));
        finishedCourses5.add(education6);
        Organization educationalInstitution5 = new Organization(courseLink5, finishedCourses5);

        Link courseLink7 = new Link("Заочная физико-техническая школа при МФТИ", null);
        String courseName7 = "Закончил с отличием";
        String courseDescription7 = null;
        Experience education7 = new Experience(courseName7, courseDescription7, LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1));
        List<Experience> finishedCourses7 = new ArrayList<>();
        finishedCourses7.add(education7);
        Organization educationalInstitution7 = new Organization(courseLink7, finishedCourses7);


        List<Organization> educations = new ArrayList<>();
        educations.add(educationalInstitution1);
        educations.add(educationalInstitution2);
        educations.add(educationalInstitution3);
        educations.add(educationalInstitution4);
        educations.add(educationalInstitution5);
        educations.add(educationalInstitution7);
        resume.setSectionData(SectionType.EDUCATION, new OrganizationsList(educations));

        return resume;
    }

}


