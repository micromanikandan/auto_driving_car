package auto_driving_car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Implementation of auto car prototype and run the simulation with user inputs.
 * 
 * Date: 4th Aug 24
 * Author: Manikandan
 */
public class AutoCarService {

	static List<Car> listOfCars = new ArrayList<>();
	static List<UserInputs> listofuserInputs = new ArrayList<>();
	static int plotXsize;
	static int plotYsize;

	// Get Inputs from user through console
	public static UserInputs getUserInputs(UserInputs usersInputs) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the name of the car: ");

		String carName = scanner.next();
		usersInputs.setCarName(carName);

		System.out.println("Please enter initial position of car " + carName + "  in x y Direction format: ");

		int xPosition = scanner.nextInt();
		int yPosition = scanner.nextInt();
		String direction = scanner.next();

		usersInputs.setxPosition(xPosition);
		usersInputs.setyPosition(yPosition);
		usersInputs.setDirection(direction);

		System.out.println("Please enter the commands for car " + carName + " : ");

		String commands = scanner.next();
		usersInputs.setCommands(commands);

		System.out.println("Your current list of cars are:\n" + "- " + carName + ",(" + xPosition + "," + yPosition
				+ ")," + direction + "," + commands + "\n" + "Please choose from the following options:\n"
				+ "[1] Add a car to field\n" + "[2] Run simulation");

		return usersInputs;

	}

	// Option #2: To run simulation from userInputs
	public static void runSimulation(UserInputs userInputs) {

		Scanner scanner = new Scanner(System.in);

		char[] charArray = userInputs.getCommands().toCharArray();
		Car car = new Car();
		car.setDirection(userInputs.getDirection());
		car.setxPoint(userInputs.getxPosition());
		car.setyPoint(userInputs.getyPosition());
		car.setName(userInputs.getCarName());

		for (int k = 0; k < charArray.length; k++) {

			car.setRides(String.valueOf(charArray[k]));
			car = getNavigationRules(car);

			if (Stream.of(car.getxPoint(), car.getyPoint())
					.anyMatch(x -> x < 0 || x > userInputs.getPlotXsize() - 1 || x > userInputs.getPlotYsize() - 1)) {
				System.out.println(" Car " + car.getName() + " goes beyond the boundaries ");
				System.out.println("Please choose from the following options:\n" + "[1] Start over\n" + "[2] Exit");

				int selected = scanner.nextInt();
				if (selected == 1) {
					startOver();
				} else if (selected == 2) {
					System.out.println("Thank you for running the simulation. Goodbye!");
					System.exit(0);
				}
			}
			for (Car listcar : listOfCars) {
				if (listcar.getxPoint() == car.getxPoint() && listcar.getyPoint() == car.getyPoint()) {
					int step = k + 1;
					System.out.println(car.getName() + ", collides with " + listcar.getName() + " at ("
							+ listcar.getxPoint() + "," + listcar.getyPoint() + ") at Step " + step);
					System.out.println(listcar.getName() + ", collides with " + car.getName() + " at ("
							+ listcar.getxPoint() + "," + listcar.getyPoint() + ") at Step " + step);
					System.out.print("Please choose from the following options:\n" + "[1] Start over\n" + "[2] Exit");
					int selected = scanner.nextInt();
					if (selected == 1) {
						startOver();
					} else if (selected == 2) {
						System.out.println("Thank you for running the simulation. Goodbye!");
						System.exit(0);
					}
				}
			}
		}

		listOfCars.add(car);
		listofuserInputs.add(userInputs);
		System.out.println("Your current list of cars are: ");
		listofuserInputs.forEach(user -> System.out.println("- " + user.getCarName() + ",(" + user.getxPosition() + ","
				+ user.getyPosition() + ")," + user.getDirection() + "," + user.getCommands() + " "));

		System.out.println("After simulation, the result is: ");
		System.out.println(car.getName() + ",(" + car.xPoint + "," + car.yPoint + ")," + car.getDirection());
		System.out.print("Please choose from the following options:\n" + "[1] Start over\n" + "[2] Exit");

		int selected = scanner.nextInt();
		if (selected == 1) {
			startOver();
		} else if (selected == 2) {
			System.out.println("Thank you for running the simulation. Goodbye!");
			System.exit(0);
		}

	}

	// Start point of the application
	public static UserInputs startOver() {

		Scanner scanner = new Scanner(System.in);
		UserInputs userInputs = new UserInputs();
		System.out.println(
				"Please choose from the following options:\n" + "[1] Add a car to field\n" + "[2] Run simulation");

		int selectedOption = scanner.nextInt();

		if (selectedOption == 1) {

			userInputs.setPlotXsize(plotXsize);
			userInputs.setPlotYsize(plotYsize);
			userInputs = getUserInputs(userInputs);
			int selectedSimulation = scanner.nextInt();

			if (selectedSimulation == 2) {
				runSimulation(userInputs);
			} else {
				System.out.println("Invalid Option");
			}
		}

		return userInputs;
	}

	public static void main(String[] args) {

		System.out.println("Welcome to Auto Driving Car Simulation!");
		System.out.println("Please enter the width and height of the simulation field in x y format");
		Scanner scanner = new Scanner(System.in);

		plotXsize = scanner.nextInt();
		plotYsize = scanner.nextInt();

		System.out.println("You have created a field of " + plotXsize + " X " + plotYsize);

		startOver();

	}

	// Apply the rules the navigation rules and get the car's position
	public static Car getNavigationRules(Car car) {

		String checks = car.getDirection() + car.getRides();

		switch (checks) {

		case "WF" -> {
			car.setDirection("W");
			car.setxPoint(car.getxPoint() - 1);
		}
		case "WR" -> car.setDirection("N");
		case "WL" -> car.setDirection("S");

		case "NF" -> {
			car.setDirection("N");
			car.setyPoint(car.getyPoint() + 1);
		}
		case "NR" -> car.setDirection("E");
		case "NL" -> car.setDirection("W");

		case "SF" -> {
			car.setDirection("S");
			car.setyPoint(car.getyPoint() - 1);
		}
		case "SR" -> car.setDirection("W");
		case "SL" -> car.setDirection("E");

		case "EF" -> {
			car.setDirection("E");
			car.setxPoint(car.getxPoint() + 1);
		}
		case "ER" -> car.setDirection("S");
		case "EL" -> car.setDirection("N");

		}

		return car;
	}

}
