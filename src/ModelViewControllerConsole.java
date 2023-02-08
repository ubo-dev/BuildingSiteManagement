import java.util.*;
public class ModelViewControllerConsole {

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();

		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();		
		router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
		router.put("Person", new Controller(new PersonView(), new PersonModel()));
		router.put("Manager", new Controller(new ManagerView(), new ManagerModel()));
		router.put("AssistantManager", new Controller(new AssistantManagerView(), new AssistantManagerModel()));
		router.put("Controller", new Controller(new ControllerAptView(), new ControllerAptModel()));
		router.put("Resident", new Controller(new ResidentView(), new ResidentModel()));
		router.put("Apartment", new Controller(new ApartmentView(),new ApartmentModel()));
		router.put("Decisions", new Controller(new DecisionsView(), new DecisionsModel()));
		router.put("Receipt", new Controller(new ReceiptView(), new ReceiptModel()));
		router.put("Expense", new Controller(new ExpenseView(), new ExpenseModel()));
		router.put("Payment", new Controller(new PaymentView(), new PaymentModel()));
		router.put("movedFlat", new Controller(new MovedFlatView(), new MovedFlatModel()));
		router.put("Subscription", new Controller(new SubscriptionView(), new SubscriptionModel()));
		router.put("Bill", new Controller(new BillView(), new BillModel()));
		router.put("Message", new Controller(new MessageView(), new MessageModel()));



		ViewData viewData = new ViewData("MainMenu", "");		
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");


		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "UBO:55161";
		//DatabaseUtilities.host = "DESKTOP-E6ERJ05\\SQLEXPRESS01";
		//DatabaseUtilities.host = "DESKTOP-M8BB118\\SQLEXPRESS:49670";
		DatabaseUtilities.databaseName = "BuildingSiteManagement";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
	
}
