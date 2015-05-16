package com.jmg.sample.ui;

import java.util.Arrays;

import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.jmg.sample.server.Employee;
import com.jmg.sample.server.Gender;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;

/**
 * 
 * @author Javier Moreno Garcia
 *
 */
public class EmployeeForm extends AbstractForm<Employee> {

	private static final long serialVersionUID = -2634152568251823909L;

	private TextField firstname = new MTextField("First name");
	private TextField lastname = new MTextField("Last name");
	private DateField birthDate = new DateField("Birth date");
	private OptionGroup gender = new OptionGroup("Gender", Arrays.asList(Gender
			.values()));

	public EmployeeForm(Employee other) {
		setSizeUndefined();
		setEntity(other);
	}

	@Override
	protected Component createContent() {
		return new MVerticalLayout(new MFormLayout(firstname, lastname,
				birthDate, gender).withWidth(""), getToolbar()).withWidth("");
	}
}
