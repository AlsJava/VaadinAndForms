package edu.alsjava.example.vaadinform.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;
import edu.alsjava.example.vaadinform.model.User;

import java.util.concurrent.TimeUnit;

/**
 * Created by aluis on 5/17/20.
 */
public class FormDemoDialog extends Dialog {

    private final Binder<User> binder = new Binder<>();

    public FormDemoDialog() {
        VerticalLayout vlMainlayout = new VerticalLayout();

        Button btnOk = new Button("OK");
        btnOk.addClickListener(event -> {
            User user = new User();
            try {
                binder.writeBean(user);
                Notification.show(user.toString(), (int) TimeUnit.SECONDS.toMillis(8), Notification.Position.MIDDLE);
                close();
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(event -> close());

        HorizontalLayout hlMenu = new HorizontalLayout(btnCancel, btnOk);
        hlMenu.setWidthFull();

        vlMainlayout.add(prepareForm(), hlMenu);
        add(vlMainlayout);
    }

    private FormLayout prepareForm() {
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2));
//                new FormLayout.ResponsiveStep("40em", 3));

        TextField tfUsername = new TextField("Username");
        PasswordField pfPassword = new PasswordField("Password");
        TextArea taDescription = new TextArea("Description");
        DatePicker dpExpiration = new DatePicker("Expiration");

        formLayout.add(tfUsername, pfPassword, dpExpiration, taDescription);
//        formLayout.add(taDescription, 2);
        formLayout.setColspan(taDescription, 2);

        binder.forField(tfUsername).asRequired().bind(User::getUsername, User::setUsername);
        binder.forField(pfPassword).asRequired().withValidator(new StringLengthValidator("Debe tener al menos 8 caracteres", 8, 64)).bind(User::getPassword, User::setPassword);
        binder.forField(taDescription).bind(User::getDescription, User::setDescription);
        binder.forField(dpExpiration).bind(User::getExpiration, User::setExpiration);

        return formLayout;
    }
}
