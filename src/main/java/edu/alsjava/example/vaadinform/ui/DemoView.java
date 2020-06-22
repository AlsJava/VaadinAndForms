package edu.alsjava.example.vaadinform.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Created by aluis on 5/17/20.
 */
@Route("app")
public class DemoView extends VerticalLayout {

    public DemoView() {
        add(new Text("Demo de formulario en Vaadin"));
        Button btnDialogForm = new Button("Dialog Form");
        btnDialogForm.addClickListener(event -> {
            FormDemoDialog formDemoDialog = new FormDemoDialog();
            formDemoDialog.open();
        });
        add(btnDialogForm);
    }
}
