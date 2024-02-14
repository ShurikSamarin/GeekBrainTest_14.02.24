
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Animal {
    protected String name;
    protected String birthDate;
    protected List<String> commands;

    public Animal(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void displayCommands() {
        System.out.println("Commands for " + name + ":");
        for (String command : commands) {
            System.out.println(command);
        }
    }
}

class Pets extends Animal {
    public Pets(String name, String birthDate) {
        super(name, birthDate);
    }
}

class PackAnimals extends Animal {
    public PackAnimals(String name, String birthDate) {
        super(name, birthDate);
    }
}

class Dog extends Pets {
    public Dog(String name, String birthDate) {
        super(name, birthDate);
    }
}

class Cat extends Pets {
    public Cat(String name, String birthDate) {
        super(name, birthDate);
    }
}

class Hamster extends Pets {
    public Hamster(String name, String birthDate) {
        super(name, birthDate);
    }
}

class Horse extends PackAnimals {
    public Horse(String name, String birthDate) {
        super(name, birthDate);
    }
}

class Camel extends PackAnimals {
    public Camel(String name, String birthDate) {
        super(name, birthDate);
    }
}

class Donkey extends PackAnimals {
    public Donkey(String name, String birthDate) {
        super(name, birthDate);
    }
}

public class Animal_Registry {
    private static Map<String, Integer> animalCounter = new HashMap<>();

    public static void main(String[] args) {
        try (Counter counter = new Counter()) {
            Scanner scanner = new Scanner(System.in);
            List<Animal> animals = new ArrayList<>();

            while (true) {
                System.out.println("");
                System.out.println("1. Add new animal");
                System.out.println("2. List commands for an animal");
                System.out.println("3. Add new command to an animal");
                System.out.println("4. List animals by birth date");
                System.out.println("5. Display lists of animals");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                System.out.println("");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addNewAnimal(scanner, animals);
                        break;
                    case 2:
                        listCommands(scanner, animals);
                        break;
                    case 3:
                        addNewCommand(scanner, animals);
                        break;
                    case 4:
                        listAnimalsByBirthDate(animals);
                        break;
                    case 5:
                    animalCounter.entrySet().forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void addNewAnimal(Scanner scanner, List<Animal> animals) {
        System.out.print("Enter animal type (dog, cat, hamster, horse, camel, donkey): ");
        String type = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter birth date: ");
        String birthDate = scanner.nextLine();

        Animal animal;
        if (type.equals("dog")) {
            animal = new Dog(name, birthDate);
        } else if (type.equals("cat")) {
            animal = new Cat(name, birthDate);
        } else if (type.equals("hamster")) {
            animal = new Hamster(name, birthDate);
        } else if (type.equals("horse")) {
            animal = new Horse(name, birthDate);
	} else if (type.equals("camel")) {
            animal = new Camel(name, birthDate);
        } else if (type.equals("donkey")) {
            animal = new Donkey(name, birthDate);
        } else {
            System.out.println("Invalid animal type.");
            return;
        }

        animals.add(animal);
        animalCounter.put(type, animalCounter.getOrDefault(type, 0) + 1);
        System.out.println("New animal added successfully.");
    }

    private static void listCommands(Scanner scanner, List<Animal> animals) {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        for (Animal animal : animals) {
            if (animal.name.equalsIgnoreCase(name)) {
                animal.displayCommands();
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    private static void addNewCommand(Scanner scanner, List<Animal> animals) {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        for (Animal animal : animals) {
            if (animal.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new command: ");
                String command = scanner.nextLine();
                animal.addCommand(command);
                System.out.println("New command added successfully.");
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    private static void listAnimalsByBirthDate(List<Animal> animals) {
        animals.sort((a1, a2) -> a1.birthDate.compareTo(a2.birthDate));
        for (Animal animal : animals) {
            System.out.println(animal.name + " - " + animal.birthDate);
        }
    }

    static class Counter implements AutoCloseable {

        public Counter() {
            
        }

        @Override
        public void close() throws Exception {
            
            if (!animalCounter.isEmpty()) {
                throw new Exception("Resource not closed properly");
            }
        }
    }
}
