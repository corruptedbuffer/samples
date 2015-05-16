package com.jmg.sample.ui;

import java.util.function.Consumer;

import javax.inject.Inject;

import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.form.AbstractForm.ResetHandler;
import org.vaadin.viritin.form.AbstractForm.SavedHandler;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.jmg.sample.server.Employee;
import com.jmg.sample.server.EmployeeRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * 
 * @author Javier Moreno Garcia
 *
 */
@Title("Employee CRUD example")
@Theme("valo")
@SpringUI
public class EmployeeUI extends UI {

	private static final long serialVersionUID = -498330517314613891L;

	@Inject
	private EmployeeRepository repo;

	private MTable<Employee> list = new MTable<>(Employee.class)
			.withProperties("id", "firstname", "lastname", "birthDate",
					"gender")
			.withColumnHeaders("id", "First Name", "Last name", "Birthdate",
					"Gender")
			.setSortableProperties("firstname", "lastname", "birthDate",
					"gender").withFullWidth();

	private Button addNew = new MButton(FontAwesome.PLUS, new ClickListener() {

		@Override
		public void buttonClick(ClickEvent e) {
			add(e);

		}
	});

	private Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O,
			new ClickListener() {

				@Override
				public void buttonClick(ClickEvent e) {
					edit(e);

				}
			});
	private Button delete = new ConfirmButton(FontAwesome.TRASH_O,
			"Do you really want to delete this entry?", new ClickListener() {

				@Override
				public void buttonClick(ClickEvent e) {
					remove(e);

				}
			});

	@Override
	protected void init(VaadinRequest request) {
		setContent(new MVerticalLayout(new MHorizontalLayout(addNew, edit,
				delete), list).expand(list));
		listEntities();
		list.addMValueChangeListener(new MValueChangeListener<Employee>() {

			@Override
			public void valueChange(MValueChangeEvent<Employee> e) {
				adjustActionButtonState();
			}

		});
	}

	//
	// private methods

	private void add(ClickEvent clickEvent) {
		edit(new Employee());
	}

	private void edit(ClickEvent e) {
		edit(list.getValue());
	}

	private void remove(ClickEvent e) {
		repo.delete(list.getValue());
		list.setValue(null);
		listEntities();
	}

	private void adjustActionButtonState() {
		boolean hasSelection = list.getValue() != null;
		edit.setEnabled(hasSelection);
		delete.setEnabled(hasSelection);
	}

	private void listEntities() {

		list.setBeans(repo.findAll());

		adjustActionButtonState();

	}

	private void edit(final Employee otherEmployeeForm) {
		EmployeeForm employeeForm = new EmployeeForm(otherEmployeeForm);
		employeeForm.openInModalPopup();
		employeeForm.setSavedHandler(new SavedHandler<Employee>() {

			@Override
			public void onSave(Employee e) {
				saveEntry(e);
			}

		});

		employeeForm.setResetHandler(new ResetHandler<Employee>() {

			@Override
			public void onReset(Employee e) {
				resetEntry(e);
			}

		});
	}

	public void saveEntry(Employee entry) {
		repo.save(entry);
		listEntities();
		closeWindow();
	}

	public void resetEntry(Employee entry) {
		listEntities();
		closeWindow();
	}

	private void closeWindow() {
		getWindows().stream().forEach(new Consumer<Window>() {

			@Override
			public void accept(Window w) {
				removeWindow(w);

			}

		});
	}

}
