import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

interface UserBase {
    void display();

    public static void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class PeriodPal {
    static {
        MoodUpdates.addmsgs();
        Nutrition.addDietData();
        JokesandMythBusters.addJokesandMyths();
    }

    public static boolean isUserSignedUp() {
        File file = new File("profile_log.txt");
        if (!file.exists())
            return false;
        try (BufferedReader reader = new BufferedReader(new

        FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    return true; // Profile file has a name => user signed up
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public static void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Phase p = new Phase();
        Scanner sc = new Scanner(System.in);
        System.out.println("\u001B[95m" + "========================================" + "\u001B[0m");
        System.out.println("\u001B[95m" + "\u001B[1m" + " Welcome to Period Pal! " + "\u001B[0m");

        System.out.println("\u001B[95m" + "========================================" + "\u001B[0m");
        Symptoms s = new Symptoms(p);
        System.out.println("\u001B[94;1m" + "\n\n What would you like to do?" + "\u001B[0m");
        System.out.println("\u001B[96;1m" + " 1. Signup \n 2. Login" + "\u001B[0m");
        int choice1 = sc.nextInt();
        sc.nextLine();
        switch (choice1) {
            case 1:
                p.accept(sc);
                System.out.println("\u001B[92;1m" + "\n\n You have signed up successfully!\n Please Refresh and Login!"
                        + "\u001B[92m");
                return;
            case 2:
                if (!isUserSignedUp()) {
                    System.out.println("\u001B[91m" + "Please signup before logging in" + "\u001B[0m");
                    return;
                }
                p.loadProfileFromFile();
                JokesandMythBusters jm = new JokesandMythBusters(p);
                MoodUpdates mu = new MoodUpdates(p);
                Nutrition n = new Nutrition(p);
                System.out.println();
                System.out.println("\u001B[38;5;93;1m" + " Welcome Back " + p.name + "!");
                while (true) {

                    System.out.println("\u001B[94;1m" + "\nPlease enter your choice from below options:" + "\u001B[0m");
                    System.out.println("\u001B[96m" + "------------------------------------------" + "\u001B[0m");

                    System.out.println("\u001B[94m" + " 1. Track my period" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 2. Mood Updates" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 3. Get a diet plan" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 4. Symptoms" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 5. Get a joke and a myth buster" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 6. Update Period Start Date" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 7. Check Data History" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 8. Edit Profile" + "\u001B[0m");
                    System.out.println("\u001B[94m" + " 9. Exit app" + "\u001B[0m");
                    System.out.print("\u001B[92m" + "Enter choice here: " + "\u001B[0m");
                    int choice2 = sc.nextInt();
                    sc.nextLine(); // Consume newline left-over

                    System.out.println();
                    switch (choice2) {
                        case 1:
                            System.out.println("\u001B[95m" + "---- Period Tracking ----" + "\u001B[0m");
                            p.checkPhase();
                            p.display(); // display Phase
                            System.out.println("\u001B[94m" + "------------------------" + "\u001B[0m");
                            delay(1000);
                            break;
                        case 2:
                            System.out.println("\u001B[95m" + "---- Mood Updates ----" + "\u001B[0m");
                            mu.displaymsg();
                            System.out.println("\u001B[94m" + "---------------------" + "\u001B[0m");
                            delay(1000);
                            break;
                        case 3:

                            System.out.println("\u001B[95m" + "---- Diet Plan ----" + "\u001B[0m");
                            n.display(); // display Diet
                            System.out.println("\u001B[94m" + "------------------" + "\u001B[0m");
                            delay(2000);
                            break;
                        case 4:
                            System.out.println("\u001B[95m" + "---- Symptoms ----" + "\u001B[0m");
                            s.symptoms(sc);
                            System.out.println("\u001B[94m" + "-----------------" + "\u001B[0m");
                            delay(2000);
                            break;
                        case 5:
                            System.out.println("\u001B[95m" + "---- Jokes and Myth Busters ----" + "\u001B[0m");
                            jm.displayJokesandMyths();
                            System.out.println("\u001B[94m" + "------------------------------" + "\u001B[0m");
                            break;
                        case 6:
                            updatePeriodDate(sc, p);
                            break;
                        case 7: {
                            System.out.println("\u001B[95m" + "---- Data History ----" + "\u001B[0m");
                            System.out.println();
                            p.displayLoggedPeriodDate();
                            System.out.println();
                            s.displayLoggedSymptoms();
                            System.out.println();
                            mu.displayLoggedMoods();
                            System.out.println();
                            n.displayLoggedDiet();
                            System.out.println();

                            jm.displayLoggedMyths();
                            System.out.println("\u001B[94m" + "---------------------" + "\u001B[0m");
                        }
                            break;
                        case 8:
                            p.displayLoggedProfile();
                            p.updateProfile(sc);
                            p.displayLoggedProfile();
                            n = new Nutrition(p);
                            break;
                        case 9:
                            System.out.println(
                                    "\u001B[95;1m" + " Thank you for using Period Pal! \n Stay well!" + "\u001B[0m");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("\u001B[91m" + "Invalid choice. Please try again." + "\u001B[0m");
                            break;
                    }
                }
        }
    }

    private static void updatePeriodDate(Scanner sc, Phase p) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        boolean validDate = false;
        LocalDate newDate = null;
        p.today = ZonedDateTime.now(p.zone);
        while (!validDate) {
            try {
                System.out.print("\u001B[94m" + "Enter the date when your period started (dd/mm/yy): " + "\u001B[0m");
                newDate = LocalDate.parse(sc.nextLine(), formatter);

                if (p.lastPeriodDate == null ||
                        newDate.isBefore(p.lastPeriodDate.toLocalDate()) ||
                        newDate.isAfter(LocalDate.now())) {
                    System.out.println("\u001B[91m"
                            + "Invalid date. Please enter a valid date not in the future or before your last period."
                            + "\u001B[0m");
                } else {
                    validDate = true;
                }
            } catch (DateTimeException e) {
                System.out.println("\u001B[91m" + "Invalid date format. Please use dd/mm/yy." + "\u001B[0m");
            }
        }
        p.updatePeriodsDate(newDate);
    }
}

class PrakritiIdentifier {
    class Question {
        String question;
        String optionA;
        String optionB;
        String optionC;

        Question(String question, String optionA, String optionB, String optionC) {
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
        }
    }

    String identifyPrakriti(Scanner sc) {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Your body frame is:", "Thin and light",
                "Medium and muscular", "Broad and heavy"));
        questions.add(new Question("Your skin tends to be:", "Dry and rough",
                "Warm and oily", "Smooth and cold"));

        questions.add(new Question("Your digestion is:", "Irregular", "Strong and quick", "Slow and steady"));
        questions.add(new Question("Your appetite is:", "Variable", "Strong", "Mild"));
        questions.add(new Question("Your response to stress is:", "Anxious", "Irritable", "Calm"));
        questions.add(new Question("Your sleep pattern is:", "Light", "Moderate", "Deep"));
        questions.add(new Question("Your energy levels are:", "Variable", "Steady", "Low"));
        questions.add(new Question("Your memory is:", "Quick to forget", "Sharp", "Slow but long"));
        questions.add(new Question("Your voice is:", "Thin and rough", "Clear and loud", "Deep and slow"));
        questions.add(new Question("Your personality is:", "Creative", "Ambitious", "Loyal"));
        int vata = 0, pitta = 0, kapha = 0;
        System.out.println("\u001B[94;1m" + "Answer each question by entering A, B, or C:" + "\u001B[0m");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println();
            System.out.println("\u001B[94;1m" + (i + 1) + ". " + q.question + "\u001B[0m");
            System.out.println("\u001B[96m" + " A. " + q.optionA + "\u001B[0m");
            System.out.println("\u001B[96m" + " B. " + q.optionB + "\u001B[0m");
            System.out.println("\u001B[96m" + " C. " + q.optionC + "\u001B[0m");
            String ans = "";
            while (true) {
                System.out.print("\u001B[95m" + "Your answer: " + "\u001B[0m");
                ans = sc.next().trim().toUpperCase();
                if (ans.equals("A") || ans.equals("B") || ans.equals("C")) {
                    break;
                } else {
                    System.out.println("\u001B[91m" + "Invalid input. Please enter A, B, or C." + "\u001B[0m");
                }
            }
            if (ans.equals("A")) {
                vata++;
            } else if (ans.equals("B")) {
                pitta++;
            } else {
                kapha++;
            }
        }
        System.out.println("\u001B[38;5;93m" + "\n--- Prakriti Result ---" + "\u001B[0m");
        System.out.println("\u001B[38;5;93m" + "Vata: " + vata + "\u001B[0m");
        System.out.println("\u001B[38;5;93m" + "Pitta: " + pitta + "\u001B[0m");
        System.out.println("\u001B[38;5;93m" + "Kapha: " + kapha + "\u001B[0m");
        String prakriti = "";
        if (vata > pitta && vata > kapha) {
            prakriti = "Vata";
        } else if (pitta > vata && pitta > kapha) {
            prakriti = "Pitta";
        } else if (kapha > vata && kapha > pitta) {
            prakriti = "Kapha";
        } else if (vata == pitta && vata > kapha) {
            prakriti = "Vata-Pitta";
        } else if (pitta == kapha && pitta > vata) {
            prakriti = "Pitta-Kapha";
        } else if (vata == kapha && vata > pitta) {
            prakriti = "Vata-Kapha";
        } else {
            prakriti = "Tridoshic (Balanced)";
        }
        System.out.println("\u001B[95;1m" + " Your Prakriti is: " + prakriti + "\u001B[0m");
        sc.close();
        return prakriti;
    }
}

abstract class UserInfo implements UserBase {

    String prakriti;
    long currentPeriod, nextPeriod;
    String name;
    final ZoneId zone;
    ZonedDateTime lastPeriodDate, firstPeriodDate, birthDate;
    ZonedDateTime today;
    long difference;
    int periodLength, cycleLength;

    public UserInfo() {
        zone = ZoneId.of("Asia/Kolkata");
        today = ZonedDateTime.now(zone);
    }

    public void accept(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        if (name == null) {
            System.out.println("\u001B[94m" + "\nHello Dear! \nHow should I call you?" + "\u001B[0m");
            name = sc.nextLine();
            System.out.print("\u001B[94m" + "Enter your birth date (dd/mm/yy): " + "\u001B[0m");
            boolean validDatebd = false;
            while (!validDatebd) {
                try {
                    LocalDate bd = LocalDate.parse(sc.nextLine(), formatter);
                    birthDate = bd.atStartOfDay(zone);
                    validDatebd = true;
                } catch (DateTimeException e) {
                    System.out.println("\u001B[91m" + "Invalid date format. Please use dd/mm/yy." + "\u001B[0m");
                }
            }
            System.out.println("\u001B[92m" + "Your birth date saved: " + birthDate + "\u001B[0m");

            System.out.print("\u001B[94m" + "\nEnter your last period date (dd/mm/yy): " + "\u001B[0m");
            boolean validDatelpd = false;
            while (!validDatelpd) {
                try {
                    LocalDate lpd = LocalDate.parse(sc.nextLine(), formatter);
                    lastPeriodDate = lpd.atStartOfDay(zone);
                    if (lastPeriodDate.isAfter(ZonedDateTime.now())) {

                        System.out.println("\u001B[91m"
                                + "Invalid date. Please enter a valid date not in the future or before our last period."
                                + "\u001B[0m");
                    } else {
                        validDatelpd = true;
                    }
                } catch (DateTimeException e) {
                    System.out.println("\u001B[91m" + "Invalid date format. Please use dd/mm/yy." + "\u001B[0m");
                }
            }
            System.out.print("\u001B[94m" + "\nEnter your period length (days): " + "\u001B[0m");
            periodLength = sc.nextInt();
            System.out.print("\u001B[94m" + "\nEnter your cycle length (days): " + "\u001B[0m");
            cycleLength = sc.nextInt();
            sc.nextLine();
            System.out.println("\u001B[95m" + "\nDo you know your Prakriti?(Answer yes/no)" + "\u001B[0m");
            while (true) {
                String choice3 = sc.nextLine();
                if (choice3.equalsIgnoreCase("yes")) {
                    System.out.print("\u001B[94m" + "Enter prakriti (Vata, Kapha, Pitta): " + "\u001B[0m");
                    prakriti = sc.nextLine();
                    break;

                } else if (choice3.equalsIgnoreCase("no")) {
                    PrakritiIdentifier pi = new PrakritiIdentifier();
                    prakriti = pi.identifyPrakriti(sc);
                    break;
                } else {
                    System.out.println("\u001B[94m" + "Please enter(yes / no)" + "\u001B[0m");
                }
            }
            savePeriodDateToFile(lastPeriodDate);
            saveProfileToFile();
            // sc.nextLine(); // Consume newline

        } else {
            return;
        }
    }

    public void loadProfileFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("profile_log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim(); // Extract name
                } else if (line.startsWith("Period Length: ")) {
                    periodLength = Integer.parseInt(line.substring(15).trim()); // Extract period length
                } else if (line.startsWith("Cycle Length: ")) {
                    cycleLength = Integer.parseInt(line.substring(14).trim()); // Extract cycle length
                } else if (line.startsWith("Last Period Date: ")) {
                    lastPeriodDate = ZonedDateTime.parse(line.substring(18).trim()); // Extract lpd
                } else if (line.startsWith("Prakriti: ")) {
                    prakriti = line.substring(10).trim(); // Extract prakriti
                }
            }

        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error loading profile: " + e.getMessage() + "\u001B[0m");
        }
    }

    void savePeriodDateToFile(ZonedDateTime lastPeriodDate) {
        DateTimeFormatter strformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Period_Date_log.txt", true))) {
            writer.write(lastPeriodDate.format(strformatter));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error saving period date: " + e.getMessage() + "\u001B[0m");
        }
    }

    void displayLoggedPeriodDate() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Period_Date_log.txt"))) {
            String line;
            System.out.println("\u001B[96m" + "Logged Period Dates: " + "\u001B[0m");
            System.out.println("--------------------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[38;5;93m" + " " + line + "\u001B[0m");
            }
            System.out.println("--------------------------------");
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Period dates haven't been logged yet."
                    + "\u001B[0m");
        }
    }

    void updatePeriodsDate(LocalDate newDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        lastPeriodDate = newDate.atStartOfDay(zone);

        System.out.println("\u001B[92m" + "Updated period date is: " +
                lastPeriodDate.format(formatter) + "\u001B[0m");
        difference = ChronoUnit.DAYS.between(lastPeriodDate, today);
        currentPeriod = periodLength - difference - 1;
        nextPeriod = cycleLength - difference;
        checkPhase();
        savePeriodDateToFile(lastPeriodDate);
        try {
            saveProfileToFile();
            modifyFile(lastPeriodDate);
            saveProfileToFile();
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    void modifyFile(ZonedDateTime updatedPeriodDate) throws IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss Z");
            String updatedDate = updatedPeriodDate.format(formatter);
            ArrayList<String> linesOfFile = new ArrayList<String>();
            // adding lines of file to arrayList
            BufferedReader reader = new BufferedReader(new FileReader("profile_log.txt"));
            String line;
            while (true) {
                line = reader.readLine();
                if (line != null) {
                    linesOfFile.add(line);
                } else {
                    break;
                }
            }
            if (linesOfFile.size() >= 4) {

                linesOfFile.set(3, "Last Period Date: " + updatedDate);
            } else {
                System.out.println("File does not contain enough info.");
            }
            // writing modified lines
            BufferedWriter writer = new BufferedWriter(new FileWriter("profile_log.txt"));
            for (String lines : linesOfFile) {
                System.out.println(lines);
                if (lines != null) {
                    writer.write(lines);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        } catch (NullPointerException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    void checkPhase() {
        difference = ChronoUnit.DAYS.between(lastPeriodDate, today);
        currentPeriod = periodLength - difference - 1;
        nextPeriod = cycleLength - difference;
        if (difference < periodLength) {
            System.out.println(
                    "\u001B[38;5;93m" + (periodLength - difference - 1) + " days left for periods to finish!"
                            + "\u001B[0m");
        } else if (difference < cycleLength) {
            System.out.println("\u001B[38;5;93m" + (cycleLength -
                    ChronoUnit.DAYS.between(lastPeriodDate, today)) + " days let for periods to come!" + "\u001B[0m");

        } else {
            System.out.println("\u001B[38;5;93m" + "Your period is late by "
                    + Math.abs(cycleLength - ChronoUnit.DAYS.between(lastPeriodDate,
                            today))
                    + " days!" + "\u001B[0m");

            System.out.println("\u001B[38;5;93m" + "Please update your period date!" + "\u001B[0m");
        }
    }

    private void saveProfileToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profile_log.txt", true))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Period Length: " + periodLength);
            writer.newLine();
            writer.write("Cycle Length: " + cycleLength);
            writer.newLine();
            writer.write("Last Period Date: " + lastPeriodDate);
            writer.newLine();
            writer.write("Prakriti: " + prakriti);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error saving profile: " + "\u001B[0m");
        }
    }

    void displayLoggedProfile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("profile_log.txt"))) {
            String line;
            System.out.println("\u001B[96m" + "Logged Profile: " + "\u001B[0m");
            System.out.println("--------------------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println(" " + line);
            }
            System.out.println("--------------------------------");
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Profile hasn't been logged yet." + "\u001B[0m");
        }
    }

    void updateProfile(Scanner sc) {

        System.out.println("\u001B[94;1m" + "What would you like to update?" + "\u001B[0m");
        System.out.println("\u001B[96m" + "1. Name" + "\u001B[0m");
        System.out.println("\u001B[96m" + "2. Prakriti" + "\u001B[0m");
        System.out.println("\u001B[96m" + "3. Period Length" + "\u001B[0m");
        System.out.println("\u001B[96m" + "4. Cycle Length" + "\u001B[0m");
        System.out.print("\u001B[92m" + "Enter your choice: " + "\u001B[0m");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("\u001B[94m" + "Enter your name: " + "\u001B[0m");
                name = sc.nextLine();
                saveProfileToFile();
                break;
            case 2:
                System.out.print(
                        "\u001B[94m" + "Enter your prakriti(vata, kapha, pitta, vata-kapha, vata-pitta,  Pitta-kapha: "
                                + "\u001B[0m");
                prakriti = sc.nextLine();
                saveProfileToFile();
                break;
            case 3:
                System.out.print("\u001B[94m" + "Enter your new period length: " + "\u001B[0m");
                periodLength = sc.nextInt();
                saveProfileToFile();
                break;
            case 4:
                System.out.print("\u001B[94m" + "Enter your new cycle length: " + "\u001B[0m");
                cycleLength = sc.nextInt();
                saveProfileToFile();
                break;
            default:
                System.out.println("\u001B[94m" + "Invalid choice. Please try again." + "\u001B[0m");
        }

    }
}

class Phase extends UserInfo {
    public void display() {
        if (nextPeriod == 14) {
            System.out.println("\u001B[91m" + "You are ovulating today!" +
                    "\u001B[0m");
        } else if (nextPeriod <= 5) {
            System.out.println("\u001B[91m" + "You are in the PMS phase!" +
                    "\u001B[0m");
        } else if (nextPeriod <= 19 && nextPeriod >= 13 && nextPeriod != 14) {
            System.out.println("\u001B[91m" + "You are in the fertile phase!" +
                    "\u001B[0m");
        } else if (nextPeriod >= 6 && nextPeriod <= 12) {
            System.out.println("\u001B[91m" + "You are in the luteal phase!" +
                    "\u001B[0m");
        } else if (nextPeriod >= 20 && nextPeriod <= (cycleLength - periodLength)) {
            System.out.println("\u001B[91m" + "You are in the follicular phase!" +
                    "\u001B[0m");
        } else {
            System.out.println("\u001B[91m" + "You are in the menstrual phase!" +
                    "\u001B[0m");
        }
    }
}

class JokesandMythBusters extends Phase {
    static ArrayList<String> jokes = new ArrayList<String>();
    static ArrayList<String> myths = new ArrayList<String>();
    Phase phase;

    JokesandMythBusters(Phase phase) {
        this.phase = phase;
    }

    static void addJokesandMyths() {
        jokes.add("Why don't skeletons fight each other? They don't have the guts, that's why!");
        jokes.add("What do you call fake spaghetti? An impasta");
        jokes.add("How many tickles does it take to make an octopus laugh? Ten-tickles");
        jokes.add("How does the moon cut his hair? Eclipse it");
        jokes.add("What do you call cheese that isn't yours? Nacho cheese");
        myths.add("Myth: Eating chocolate can cause acne. Fact: Chocolate doesn't cause acne.");
        myths.add(
                "Myth: Caffeine causes dehydration. Fact: Caffeine has a mild diuretic effect, but it also stimulates the production of urine.");
        myths.add("Myth: Eating ice cream makes you catch a cold. Fact: Ice cream doesn't cause colds.");
        myths.add(
                "Myth: Eating spicy food causes heartburn. Fact: Spicy food can cause heartburn, but it's not the spiciness that causes it.");
    }

    void displayJokesandMyths() {
        System.out.println();
        System.out.println("\u001B[92m" + "Here's a joke just for you, " +
                phase.name + "!\n" + "\u001B[0m");
        UserBase.delay(1000);
        Random random = new Random();
        int randomIndex = random.nextInt(jokes.size());
        String joke = jokes.get(randomIndex);
        System.out.println("\u001B[38;5;93m" + joke + "\u001B[0m");
        UserBase.delay(2000);
        System.out.println();
        System.out.println("\u001B[92m" + "Here's a myth buster just for you," +
                phase.name + "!\n" + "\u001B[0m");
        UserBase.delay(1000);
        randomIndex = random.nextInt(myths.size());
        String myth = myths.get(randomIndex);
        System.out.println("\u001B[38;5;93m" + myth + "\u001B[0m");
        saveMythsToFile(myth);
        UserBase.delay(2000);
    }

    private void saveMythsToFile(String myth) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("myths_log.txt", true))) {
            writer.write(myth);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error saving mood update: " +
                    e.getMessage() + "\u001B[0m");
        }
    }

    void displayLoggedMyths() {
        try (BufferedReader reader = new BufferedReader(new FileReader("myths_log.txt"))) {
            String line;
            System.out.println("\u001B[96m" + "Logged Myth Busters : " +
                    "\u001B[0m");
            System.out.println("-----------------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[38;5;93m" + " " + line + "\u001B[0m");
            }
            System.out.println("-----------------------------");
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error reading files : " + e.getMessage() +
                    "\u001B[0m");
        }
    }
}

class Nutrition implements UserBase {
    String prakriti;

    Nutrition(Phase phase) {
        this.prakriti = phase.prakriti;
    }

    static HashMap<String, ArrayList<String>> diet = new HashMap<String, ArrayList<String>>();
    // Added file path for diet saving
    private static final String DIET_LOG_FILE = "DIET_LOG_FILE.txt";
    static ArrayList<String> vataDiet = new ArrayList<String>();
    static ArrayList<String> kaphaDiet = new ArrayList<String>();

    static ArrayList<String> pittaDiet = new ArrayList<String>();
    static ArrayList<String> vataPittaDiet = new ArrayList<String>();
    static ArrayList<String> vataKaphaDiet = new ArrayList<String>();
    static ArrayList<String> pittaKaphaDiet = new ArrayList<String>();

    final static void addDietData() {
        vataDiet.add(
                "Start your morning with a glass of warm water or herbal tea.\nYou should add soups, stews and khichdi in your lunch or breakfast.\nDon’t skip meals — even small meals help your energy stay steady.");
        vataDiet.add(
                "Have a cup of turmeric powder added warm milk\nAdd a little ghee to your food for extra nourishment.\nEat cooked vegetablesand avoid raw vegetables.");
        vataDiet.add(
                "Eat soaked almonds & walnuts for strength and emotional balance.\nInclude well-cooked moong dal with soft veggies for a balanced meal.\nAdd spices like cumin and turmeric while cooking vegetables.");
        vataDiet.add(
                "Avoid spicy, bitter or sour foods.\n Leafy greens are good when cooked with mild spices.\nEat cooked vegetables like carrots, sweet potatoes, and pumpkins.");
        vataDiet.add(
                "You should avoid cold smoothies, Caffeine\n Avoid stir-fried or deep-fried vegetables — go for boiled instead.\nSpices like ginger and cumin are good for you.");
        kaphaDiet.add(
                "Start your day with warm ginger tea or warm water.\nAvoid starchy vegetables like potatoes and sweet potatoes.\nAvoid dairy-heavy vegetable dishes like creamy curries.");
        kaphaDiet.add(
                "Have 1 or 2 apricots in your breakfast.\nEat more cruciferous vegetables like cabbage and cauliflower.\nAdd garlic and turmeric to support digestion and reduce mucus.");
        kaphaDiet.add(
                "Avoid heavy dairy products like cheese\nEat light, dry vegetables like spinach, bitter gourd, and bottle gourd.\nTry dry vegetable stir-fries using minimal oil.");
        kaphaDiet.add(
                "Eat Bajra or Jowar roti with steamed vegetables in lunch.\nAvoid deep-fried vegetables — they increase heaviness.\nAvoid oily, cheesy, or creamy vegetable dishes.\nInclude small servings of raw salads in lunch, but skip them for dinner.");
        kaphaDiet.add(
                "Avoid fried, oily foods and heavy sweets.\nEat vegetables with light grains like millet or barley.\nAvoid cold or refrigerated left overs — eat freshly cooked vegetables.");
        pittaDiet.add(
                "Eat natural sweets.\nYou should also eat food that tastes bitter and astringent.\nEat vegetables like pumpkin, cucumber, carrot, leafy greens in your meal.");
        pittaDiet.add(
                "Have amla juice diluted with water in morning.\nAdd coriander, cardamom, mint in your daily breakfast and meals.\nAvoid coffee and alcohol.");
        pittaDiet.add(
                "Drink pomegranate juice, coconut water.\nAvoid too much corn or millet.\nEat at regular time to maintain digestive fire.");
        pittaDiet.add(
                "Have barley upma with light vegetables in dinner.\n Avoid heavy dinner.\nEat fruits like melons, pomegranate, apples, pears.");
        pittaDiet.add(
                "Avoid spicy, oily food specially red chillies and hot sauces.\nStay hydrated with with coconut water or have herbal teas.\nInclude mix of sweet, bitter and astringent tastes.");
        pittaDiet.add(
                "Cook with coconut oil or ghee to calm internal heat.\nSteam or lightly cook vegetables instead of frying them.\nAvoid overeating and have your meal on time.");
        vataPittaDiet.add(
                "Favor warm, moist, grounding foods like cooked rice, ghee, and root vegetables.\n Avoid spicy, oily food specially red chillies and hot sauces.\nInclude herbal teas like chamomile or fennel to calm digestion.");
        vataPittaDiet.add(
                "Eat cooked vegetables like carrots, sweet potatoes, and pumpkins.\nAvoid raw vegetables.\nAdd spices like ginger and cumin while cooking vegetables.");
        vataPittaDiet.add(
                "Eat soaked almonds & walnuts for strength and emotional balance.\nInclude well-cooked moong dal with so veggies for a balanced meal.\nAdd spices like cumin");
        vataKaphaDiet.add(
                "Add cinnamon and ajwain to your diet to ease bloating and menstrual pain.\nAvoid starchy vegetables like potatoes and sweet potatoes.\nStick to warm, light, and slightly oily meals like soups and khichdi.");
        vataKaphaDiet.add(
                "Avoid dairy-heavy vegetable dishes like creamy curries.\nEat light, dry vegetables like spinach, bitter gourd, and bottle gourd.\nUse warm soups with barley, carrots, and cumin to ease digestion.");
        vataKaphaDiet.add(
                "Avoid cold smoothies, Caffeine\n Avoid stir-fried or deep-fried vegetables — go for boiled instead.\nSpices like ginger and cumin are good");
        pittaKaphaDiet.add(
                "Add turmeric and ginger to meals to support liver and hormonal balance.\nEat light, warm, and mildly spiced meals with lots of leafy greens and barley.\nAvoid deep-fried vegetables — they increase heaviness.");
        pittaKaphaDiet.add(
                "Focus on light, warm, and dry foods like steamed veggies and millet.\nTurmeric and cinnamon help reduce inflammation and regulate flow.\nTry dry vegetable stir-fries using minimal oil.");
        pittaKaphaDiet.add(
                "Avoid fried, oily foods and heavy sweets.\nEat vegetables with light grains like millet or barley.\nAvoid cold or refrigerated left overs — eat freshly cooked vegetables.");
        diet.put("Vata", vataDiet);
        diet.put("Kapha", kaphaDiet);
        diet.put("Pitta", pittaDiet);
        diet.put("Vata-Pitta", vataPittaDiet);
        diet.put("Vata-Kapha", vataKaphaDiet);
        diet.put("Pitta-Kapha", pittaKaphaDiet);

    }

    public void display() {
        if (prakriti == null) {
            System.out.println("\u001B[91m" + "You did not enter a prakriti." + "\u001B[0m");
            return;
        }
        String selectedDiet = null;
        Random random = new Random();
        if (prakriti.equalsIgnoreCase("vata")) {
            int randomIndex = random.nextInt(vataDiet.size());
            System.out.println("\u001B[38;5;93m" + "According to your prakriti: Vata" + "\u001B[0m");
            selectedDiet = diet.get("Vata").get(randomIndex);
        } else if (prakriti.equalsIgnoreCase("kapha")) {
            int randomIndex = random.nextInt(kaphaDiet.size());
            System.out.println("\u001B[38;5;93m" + "According to your prakriti: Kapha" + "\u001B[0m");
            selectedDiet = diet.get("Kapha").get(randomIndex);
        } else if (prakriti.equalsIgnoreCase("pitta")) {
            int randomIndex = random.nextInt(pittaDiet.size());
            System.out.println("\u001B[38;5;93m" + "According to your prakriti: Pitta" + "\u001B[0m");
            selectedDiet = diet.get("Pitta").get(randomIndex);
        } else if (prakriti.equalsIgnoreCase("vata-pitta")) {
            int randomIndex = random.nextInt(vataPittaDiet.size());
            System.out.println("\u001B[38;5;93m" + "According to your prakriti: Vata-Pitta" + "\u001B[0m");
            selectedDiet = diet.get("Vata-Pitta").get(randomIndex);
        } else if (prakriti.equalsIgnoreCase("vata-kapha")) {
            int randomIndex = random.nextInt(vataKaphaDiet.size());
            System.out.println("\u001B[38;5;93m" + "According to your prakriti: Vata-Kapha" + "\u001B[0m");
            selectedDiet = diet.get("Vata-Kapha").get(randomIndex);
        } else if (prakriti.equalsIgnoreCase("pitta-kapha")) {
            int randomIndex = random.nextInt(pittaKaphaDiet.size());

            System.out.println("\u001B[38;5;93m" + "According to your prakriti: Pitta-Kapha" + "\u001B[0m");
            selectedDiet = diet.get("Pitta-Kapha").get(randomIndex);
        } else {
            System.out.println("\u001B[91m" + "Unknown Prakriti entered." + "\u001B[0m");
        }
        System.out.println("\u001B[36m" + selectedDiet + "\u001B[0m");
        saveDietToFile(selectedDiet);
    }

    private void saveDietToFile(String dietList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIET_LOG_FILE, true))) {
            writer.write("Diet plan for prakriti: " + prakriti);
            writer.newLine();
            writer.write(dietList);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error saving diet data: " +
                    e.getMessage() + "\u001B[0m");
        }
    }

    void displayLoggedDiet() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DIET_LOG_FILE))) {
            String line;
            System.out.println("\u001B[96m" + "Logged Diet :" + "\u001B[0m");
            System.out.println("-------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[38;5;93m" + " " + line + "\u001B[0m");
            }
            System.out.println("-------------------");
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error reading files : " + e.getMessage() +
                    "\u001B[0m");
        }

    }
}

class MoodUpdates extends Phase {
    Phase phase;

    MoodUpdates(Phase phase) {
        this.phase = phase;
    }

    String lastMoodMessage = "";
    static HashMap<Integer, ArrayList<String>> moods = new HashMap<Integer, ArrayList<String>>();
    Random random = new Random();

    final static void addmsgs() {
        ArrayList<String> Happymsgs = new ArrayList<String>();
        Happymsgs.add("So glad to see you smiling today, %s! Keep that energy glowing");
        Happymsgs.add("So glad to see you smiling today! Keep shining, %s! 🌟");
        Happymsgs.add("Your energy is contagious! Spread the joy, %s! ✨.");
        Happymsgs.add("You're glowing today, %s! — let the good vibes flow!");
        Happymsgs.add("Happiness looks beautiful on you, %s! 😊");
        moods.put(1, Happymsgs);
        ArrayList<String> msgs_sad = new ArrayList<String>();
        msgs_sad.add(
                "It’s okay to feel low, %s. Take a deep breath, grab your favorite snack, and know that you’re not alone.💛 ");
        msgs_sad.add("Cuddle up, rest, and let the feelings pass gently, %s ️");
        msgs_sad.add("It’s okay to feel sad sometimes. Be kind to yourself today, %s💛");
        msgs_sad.add("You don’t have to carry it all alone, %s. Take your time");
        msgs_sad.add("This too shall pass. One so moment at a time, %s 💫");

        moods.put(2, msgs_sad);
        ArrayList<String> msgs_demotivated = new ArrayList<String>();
        msgs_demotivated.add("Even small steps count. You're doing better than you think, %s💪");
        msgs_demotivated
                .add("You’ve overcome tough days before, and you will again, %s! One small step today is enough. 💪✨");
        msgs_demotivated.add("Rest if you must, but don’t give up, %s! 🌱");
        msgs_demotivated.add("You've made it through tough days before. You will again, %s! 💫.");
        msgs_demotivated.add("You're allowed to feel off. Just keep moving forward, %s!⏳");
        moods.put(4, msgs_demotivated);
        ArrayList<String> msgs_nervous = new ArrayList<String>();
        msgs_nervous.add(
                "Close your eyes, breathe in peace, breathe out tension. You are stronger than your worries, %s!💪✨💛 ");
        msgs_nervous.add("Breathe in... breathe out. You're safe and capable, %s!🌈");
        msgs_nervous.add("Your mind is stronger than your worries, %s! 🧘.");
        msgs_nervous.add("Everything will work out, %s! One moment at a time");
        msgs_nervous.add("You’re more prepared than you think — trust yourself, %s! 💖");
        moods.put(5, msgs_nervous);
        ArrayList<String> msgs_angry = new ArrayList<String>();
        msgs_angry.add(
                "Your feelings are valid. Let’s pause, breathe slowly, and let the storm pass— you’ve got this,%s! 🌿");
        msgs_angry.add("Take a deep breath, %s... You're allowed to feel this way ");
        msgs_angry.add("Anger shows you care, %s! — let’s channel it with calm power 🔥🌿");
        msgs_angry.add("You’re strong, %s! Let's breathe through the fire 💪🧘");
        msgs_angry.add("Pause. Sip some water. You’ve got this, %s!");
        moods.put(3, msgs_angry);
    }

    void displaymsg() {
        System.out.println("\u001B[94;1m" + "How are you feeling today, " + phase.name
                + "? Please select an option below:" + "\u001B[0m");
        System.out.println("\u001B[96m" + "1. Happy 😊 " + "\u001B[0m");
        System.out.println("\u001B[96m" + "2. Sad 😔 " + "\u001B[0m");
        System.out.println("\u001B[96m" + "3. Angry 😡 " + "\u001B[0m");
        System.out.println("\u001B[96m" + "4. Demotivated 😞 " + "\u001B[0m");
        System.out.println("\u001B[96m" + "5. Nervous 😟 " + "\u001B[0m");

        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[94m" + "Your mood choice: " + "\u001B[0m");
        int choice = sc.nextInt();
        sc.nextLine();
        String moodMessage = "";
        if (!moods.containsKey(choice)) {
            System.out.println("\u001B[91m" + "Invalid choice." + "\u001B[0m");
            return;
        }
        moodMessage = moods.get(choice).get(random.nextInt(5));
        moodMessage = String.format(moodMessage, phase.name);
        System.out.println("\u001B[95m" + moodMessage + "\u001B[0m");
        lastMoodMessage = moodMessage;
        saveMoodToFile(moodMessage);
    }

    private void saveMoodToFile(String mood) {
        if (mood.equals("Invalid choice."))
            return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("mood_log.txt", true))) {
            writer.write("Mood: " + mood + " - " + LocalDateTime.now());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("\u001B[91m" + "Error saving mood update: " +
                    e.getMessage() + "\u001B[0m");
        }
    }

    void displayLoggedMoods() {
        try (BufferedReader reader = new BufferedReader(new FileReader("mood_log.txt"))) {
            String line;
            System.out.println("\u001B[96m" + "Logged Moods : " + "\u001B[0m");
            System.out.println("----------------");
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[38;5;93m" + " " + line + "\u001B[0m");
            }
            System.out.println("----------------");

        } catch (IOException e) {
            System.out.println("\u001B[96m" + "Currently mood not updated, so no mood updates to log" + "\u001B[0m");
        }
    }

    public String getLastMood() {
        return lastMoodMessage;
    }
}

class Symptoms extends Phase {
    private Phase phase;

    Symptoms(Phase phase) {
        this.phase = phase;
    }

    void symptoms(Scanner sc) {
        while (true) {
            if (phase.today.isEqual(phase.lastPeriodDate)
                    ||
                    phase.today.isBefore(phase.lastPeriodDate.plusDays(phase.periodLength))) {
                // Question in Bright Blue
                System.out.println("\u001B[94;1m" + "Please select the symptoms you are facing:" + "\u001B[0m");
                // Options in Bright Cyan
                System.out.println("\u001B[96m" + "1. Cramps" + "\u001B[0m");
                System.out.println("\u001B[96m" + "2. Bloating" + "\u001B[0m");
                System.out.println("\u001B[96m" + "3. Mood Swings" + "\u001B[0m");
                System.out.println("\u001B[96m" + "4. Fatigue" + "\u001B[0m");
                System.out.println("\u001B[96m" + "5. Headaches" + "\u001B[0m");
                System.out.println("\u001B[96m" + "6. Nausea" + "\u001B[0m");
                System.out.println("\u001B[96m" + "7. Back Pain" + "\u001B[0m");
                System.out.println("\u001B[96m" + "8. Other" + "\u001B[0m");
                System.out.println("\u001B[96m" + "9. Exit Symptoms Section" +
                        "\u001B[0m");
                System.out.print("\u001B[94m" + "Your choice: " + "\u001B[0m");
                int symptomChoice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                String symptomLog = "";
                switch (symptomChoice) {
                    case 1:
                        System.out.println(
                                "\u001B[92m" + "You are experiencing cramps. Consider using a heating pad for relief."
                                        + "\u001B[0m");
                        symptomLog = "Cramps";
                        break;
                    case 2:
                        System.out.println("\u001B[92m"
                                + "Bloating can be uncomfortable. Try drinking herbal tea to ease it."
                                + "\u001B[0m");
                        symptomLog = "Bloating";
                        break;
                    case 3:
                        System.out.println(
                                "\u001B[92m" + "Mood swings are common. Take some time for self-care." + "\u001B[0m");
                        symptomLog = "Mood Swings";
                        break;
                    case 4:
                        System.out.println("\u001B[92m"
                                + "Fatigue is normal during this time. Ensure you get enough rest." + "\u001B[0m");
                        symptomLog = "Fatigue";
                        break;
                    case 5:
                        System.out.println(
                                "\u001B[92m" + "Headaches can be alleviated with hydration and rest." + "\u001B[0m");
                        symptomLog = "Headaches";
                        break;
                    case 6:
                        System.out.println("\u001B[92m"
                                + "Nausea can be tough. Ginger tea may help settle your stomach." + "\u001B[0m");
                        symptomLog = "Nausea";

                        break;
                    case 7:
                        System.out
                                .println("\u001B[92m" + "Back pain can be relieved with gentle stretching."
                                        + "\u001B[0m");
                        symptomLog = "Back Pain";
                        break;
                    case 8:
                        System.out.print("\u001B[94m" + "Please describe your symptoms: " +
                                "\u001B[0m");
                        String otherSymptoms = sc.nextLine();
                        System.out.println("\u001B[92m" + "You mentioned: " +
                                otherSymptoms
                                + ". It's always good to consult a healthcare provider." + "\u001B[0m");
                        symptomLog = "Other: " + otherSymptoms;
                        break;
                    case 9: // Exit option
                        System.out.println("\u001B[95m" + "Exiting symptoms section." +
                                "\u001B[0m");
                        return;
                    default:
                        System.out.println("\u001B[91m" + "Invalid choice. Please try again." +
                                "\u001B[0m");
                        continue;
                }
                logSymptom(symptomLog);
                System.out.println();
            } else {
                System.out.println("\u001B[94m"
                        + "You are not in your period right now. No symptoms to report currently." + "\u001B[0m");
                return;
            }
        }
    }

    private void logSymptom(String symptom) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("symptoms_log.txt", true))) {

            writer.write("Symptom: " + symptom + " at " + LocalDateTime.now());
            writer.newLine();
        } catch (IOException e) {
            System.out
                    .println("\u001B[96m" + "You are not in your period right now,so no symptoms to log" + "\u001B[0m");
        }
    }

    void displayLoggedSymptoms() {
        try (BufferedReader reader = new BufferedReader(new FileReader("symptoms_log.txt"))) {
            String line;
            System.out.println("\u001B[96m" + "Logged Symptoms :" + "\u001B[0m");
            System.out.println("-------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[38;5;93m" + " " + line + "\u001B[0m");
            }
            System.out.println("-------------------");
        } catch (IOException e) {
            System.out.println(
                    "\u001B[96m" + "You are not in your period right now, so no symptoms to log" + "\u001B[0m");
        }
    }
}