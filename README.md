# World Rally Cross Championship Management (Java GUI)

This repository contains a Java FX GUI application for managing a World Rally Cross Championship. The system supports adding, deleting, updating driver details, viewing standings, simulating races, and saving/loading data.

## Objective

The objective is to create a GUI application for World Rally Cross Championship management using Java and OOP concepts.

## Features of the System

When launched, the system provides the following functions via a Java FX GUI:

- **Add Driver Details**
- **Delete Driver Details**
- **Update Driver Details**
- **View Rally Cross Standings Table**
- **Simulate a Random Race**
- **View Race Table Sorted by Date**
- **Save Data to a Text File**
- **Load Data from a Saved Text File**
- **Exit the Program**

## Sections

### Adding Driver Details

Allows the user to enter the following driver details:
- Name
- Age
- Team
- Car
- Current points

**Example:**
```plaintext
Travis Pastrana, 38, Subaru Motorsports, Subaru WRX STi, 10
```

### Deleting Driver Details

Allows the user to delete a driver by searching by name.

### Updating Driver Details

Allows the user to update driver details by searching by name.

### Viewing the Rally Cross Standings Table

Displays the championship standings ordered by points in descending order. The table is formatted neatly with all details.

### Simulating a Random Race

Simulates a random race and assigns points to each driver accordingly:
- 1st place: 10 points
- 2nd place: 7 points
- 3rd place: 5 points

Race details stored include:
- Date of the race
- Location of the race (Nyirád, Höljes, Montalegre, Barcelona, Rīga, Norway)
- Each driver’s position and points

### Viewing Race Table Sorted by Date

Displays all races in the championship sorted by date using a custom sorting algorithm.

### Saving Data to a Text File

Saves the current data to a text file in a format that can be easily retrieved. No database usage is allowed for storing data.

### Loading Data from a Saved Text File

Loads the current data from a text file to enable resume capabilities.

## Project Structure

- **HelloApplication.java**: The main Java file containing the implementation of the championship management system GUI.

## How to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/nisal2002/ChampionshipManagement_Java.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd ChampionshipManagement_Java
   ```

3. **Compile and run the Java program:**

   ```bash
   javac HelloApplication.java
   java HelloApplication
   ```

   The program will launch a GUI with options to add, delete, update, view standings, simulate races, view race table, save data, load data, and exit the program.

## Technologies Used

- Java
- Java FX

## Author

- [Author](nisal2002) - Initial work

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
