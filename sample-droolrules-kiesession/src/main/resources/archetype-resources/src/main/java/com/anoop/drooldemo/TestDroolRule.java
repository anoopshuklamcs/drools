package com.anoop.drooldemo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class TestDroolRule {

	public static void main(String[] args) {
		PaymentOfferByState byStateTN = new PaymentOfferByState();
		byStateTN.setState("TN");

		TestDroolRule droolRule = new TestDroolRule();

		PaymentOfferByState byStateTX = new PaymentOfferByState();
		byStateTX.setState("TX");
		droolRule.executeDroolRule(byStateTX);
		droolRule.executeDroolRule(byStateTN);


	}

	public void executeDroolRule(PaymentOfferByState paymentOfferByState) {

		
		try{	
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("first-ksession-rule");
	
			FactHandle factHandler;
			
			factHandler = kSession.insert(paymentOfferByState);
			kSession.fireAllRules();
			System.out.println("The disount for the state executed using KIeSession :: " + paymentOfferByState.getState()
			+ "  is ::" + paymentOfferByState.getDiscount());
			
	
		    } catch(Exception e){
		    	e.printStackTrace();
		    }

		
		
		/*KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("first-ksession-rule");

		FactHandle factHandler;

		factHandler = kSession.insert(paymentOfferByState);
		kSession.fireAllRules();

		System.out.println("The disount for the state executed using KIeSession :: " + paymentOfferByState.getState()
				+ "  is ::" + paymentOfferByState.getDiscount());*/

	}

}
