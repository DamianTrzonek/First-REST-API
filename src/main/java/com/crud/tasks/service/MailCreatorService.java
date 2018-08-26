package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private CompanyDetails companyDetails;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("previewMessage", "TRELLO TASK ADDED!");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://damiantrzonek.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbyeMessage", "Hope to see you soon" + adminConfig.getAdminName());
        context.setVariable("companyDetails", companyDetails);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildCountTasksEmail (String message){
        List<String> productivityTips = new ArrayList<>();
        productivityTips.add("Tackle your challenging tasks before lunch");
        productivityTips.add("Use morning to focus on yourself");
        productivityTips.add("Stop confusing productivity with laziness");

        Context context = new Context();
        context.setVariable("preview_Message", "YOUR DAILY TASK COUNT!");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://damiantrzonek.github.io");
        context.setVariable("button", "View tasks");
        context.setVariable("company_details", companyDetails);
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Good luck with fulfilling your tasks. ");
        context.setVariable("productivity_tips", productivityTips);
        context.setVariable("is_friend", false);
        return templateEngine.process("mail/db-task-count-mail", context);
        }
    }
