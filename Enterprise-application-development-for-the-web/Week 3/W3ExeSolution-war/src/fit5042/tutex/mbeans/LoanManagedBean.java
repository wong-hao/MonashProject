/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.mbeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;

import fit5042.tutex.repository.entities.Loan;
import fit5042.tutex.calculator.MonthlyPaymentCalculator;

/**
 *
 * @author: originally created by Eddie. The following code has been changed in
 * order for students to practice.
 */
@ManagedBean(name = "loanManagedBean", eager = true) //If eager = "true" then managed bean is created before it is requested for the first time otherwise "lazy" initialization is used in which bean will be created only when it is requested.
@SessionScoped
public class LoanManagedBean implements Serializable {

    @EJB
    MonthlyPaymentCalculator calculator;
	
	private Loan loan;

    public LoanManagedBean() {
        this.loan = new Loan();

    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public String calculate() {
        double monthlyPayment; 
        monthlyPayment = this.calculator.calculate(loan.getPrinciple(), loan.getNumberOfYears(), loan.getInterestRate());
        //You will need to modify the monthlyPayment value and set it as the monthly payment attribute value into the loan instance
        //Please complete this method starts from here
        this.loan.setMonthlyPayment(monthlyPayment);
        return "index";
    }
}
