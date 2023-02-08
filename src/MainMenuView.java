import java.util.*;

class MainMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		String function = null;
		do {
			System.out.println("------------Welcome to Building Site Management --------------");
			System.out.println("1. Show all tables");
			System.out.println("2. Show reports");
			System.out.println("3. Quit");

			choice = getInteger("Enter your choice : ", false);

			switch (choice) {
				case 1 -> {
					do
					{
						System.out.println("""
							---------- Table Menu ----------
							0. Return to Upper Menu
							1. Person
							2. Manager
							3. Asistant Manager
							4. Controller
							5. Resident
							6. Apartment
							7. Receipt
							8. Payment
							9. Expense
							10. Decisions
							11. MovedFlat
							12. Subscription
							13. Bill
							14. Message
								""");
						choice = getInteger("Enter your choice : ", false);
						if (choice > 14 || choice < 1) return new ViewData(null, null);

						function = optionMenu(choice);
						choice = getInteger("Enter your choice : ", false);
						if (function.equals("Manager") && choice == 4) choice = 6;
						else if (function.equals("Manager") && choice == 5) choice = 7;

						if (function.equals("AssistantManager") && choice == 4) choice = 6;
						else if (function.equals("AssistantManager") && choice == 5) choice = 7;

						if (function.equals("Controller") && choice == 4) choice = 6;
						else if (function.equals("Controller") && choice == 5) choice = 7;

						if (function.equals("Resident") && choice == 4) choice = 6;
						else if (function.equals("Resident") && choice == 5) choice = 7;
					}while(choice == 6);

				}

				case 2 -> {
					System.out.println("""
							---------- Report Menu ----------
							0. Exit
							1. Expenses in period
							2. Moved Residents
							3. Unpaid Dues
							4. Average expenses in period
							5. Subscriptions in apartment
							6. Expense/due balance in period
								""");

					choice = getInteger("Enter your choice : ", false);
					function = reportMenu(choice);
					System.out.println(function);
					executeReport(function);
					//if (choice > 6 || choice < 1) return new ViewData(null, null);
				}
				default -> {
					return new ViewData(null, null);
				}
			}


		}
		while (choice == null || choice < 1 || choice > 6);

		if (function.equals("Expenses in period")) return new ViewData(null, null);
		else if (function.equals("Moved Residents")) return new ViewData(null, null);
		else if (function.equals("Unpaid Dues")) return new ViewData(null, null);
		else if (function.equals("Average expenses in period")) return new ViewData(null, null);
		else if (function.equals("Subscriptions in apartment")) return new ViewData(null, null);

		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choice);
		if (function.equals("Manager"))
		{
			switch (choice) {
				case 1 -> operationName = "select";
				case 2 -> operationName = "select.gui";
				case 3 -> operationName = "update.gui";
				default -> {
					return new ViewData(null, null);
				}
			}
		}
		else if (function.equals("AssistantManager"))
		{
			switch (choice) {
				case 1 -> operationName = "select";
				case 2 -> operationName = "select.gui";
				case 3 -> operationName = "update.gui";
				default -> {
					return new ViewData(null, null);
				}
			}
		}
		else if (function.equals("Controller"))
		{
			switch (choice) {
				case 1 -> operationName = "select";
				case 2 -> operationName = "select.gui";
				case 3 -> operationName = "update.gui";
				default -> {
					return new ViewData(null, null);
				}
			}
		}
		else if (function.equals("Resident"))
		{
			switch (choice) {
				case 1 -> operationName = "select";
				case 2 -> operationName = "select.gui";
				case 3 -> operationName = "update.gui";
				default -> {
					return new ViewData(null, null);
				}
			}
		}
		else if (function.equals("Expense in period")) return new ViewData(null, null);
		else if (function.equals("Moved Residents")) return new ViewData(null, null);
		else if (function.equals("Unpaid Dues")) return new ViewData(null, null);
		else if (function.equals("Average expenses in period")) return new ViewData(null, null);
		else if (function.equals("Subscriptions in apartment")) return new ViewData(null, null);
		else
		{
			switch (choice) {
				case 1 -> operationName = "select";
				case 2 -> operationName = "select.gui";
				case 3 -> operationName = "insert.gui";
				case 4 -> operationName = "update.gui";
				case 5 -> operationName = "delete.gui";
				default -> {
					return new ViewData(null, null);
				}
			}
		}


		return new ViewData(function, operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Main Menu View";
	}		
}
