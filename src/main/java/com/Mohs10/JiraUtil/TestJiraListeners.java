package com.Mohs10.JiraUtil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.lang.exception.ExceptionUtils;

public class TestJiraListeners implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {

		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isTicketReady = jiraPolicy.logTicketReady();
		if (isTicketReady) {
			// raise jira ticket:
			System.out.println("is ticket ready for JIRA: " + isTicketReady);
			JiraServiceProvider jiraSp = new JiraServiceProvider("https://deepikamohs10.atlassian.net/",
					"deepika.vhande@mohs10.io",
					"ATATT3xFfGF0w2QBre_X6ggtGlyh_lpCNKsq0Py5Mzk3RHj9ArNsm9gqKx4XyBrzPA0IQ-2lZ-aEpLhTdr8XzdoeZ-5-8-K2nS8O-2p59NQkQau-Us_Dlhrc-XUXKCrLWW8A9dqy7QwOTFnfBBpN2eX8sQ3Nlr7JDgd_QZOYZp0B6FOYgzQEzdM=CAD97337",
					"PAIN");
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
					+ "got failed due to some assertion or exception";
			String issueDescription = result.getThrowable().getMessage() + "\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			jiraSp.createJiraTicket("Bug", issueSummary, issueDescription, "Deepika");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
