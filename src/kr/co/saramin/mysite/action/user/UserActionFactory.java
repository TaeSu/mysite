package kr.co.saramin.mysite.action.user;

import kr.co.saramin.mvc.action.Action;
import kr.co.saramin.mvc.action.ActionFactory;
import kr.co.saramin.mysite.action.main.MainAction;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if ("joinfail".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("login".equals(actionName)) {
			action = new LoginAction();
		} else if ("auth".equals(actionName)) {
			action = new AuthAction();
		} else if ("logout".equals(actionName)) {
			action = new LogoutAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
