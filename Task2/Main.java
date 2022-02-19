public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Avdyushina", "12345");
        phonebook.add("Bushueva", "23456");
        phonebook.add("Guryeva", "34567");
        phonebook.add("Avdyushina", "45678");
        phonebook.add("Feldman", "56789");

        System.out.println("Avdyushina");
        System.out.println(phonebook.get("Avdyushina"));
        System.out.println("Bushueva");
        System.out.println(phonebook.get("Bushueva"));
        System.out.println("Guryeva");
        System.out.println(phonebook.get("Guryeva"));

        System.out.println("Alexandrov");
        System.out.println(phonebook.get("Alexandrov"));

        phonebook.add("Avdyushina", "12345");
        System.out.println("Avdyushina");
        System.out.println(phonebook.get("Avdyushina"));
    }
}