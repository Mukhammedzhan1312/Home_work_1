import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int copies;

    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = copies;
    }

    public String getIsbn() {
        return isbn;
    }
    public int getCopies() {
        return copies;
    }

    public boolean borrowCopy() {
        if (copies > 0) {
            copies--;
            return true;
        }
        return false;
    }

    public void returnCopy() {
        copies++;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ") - Copies: " + copies;
    }
}


 class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Книга создана: " + book);
    }

    public boolean removeBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                System.out.println("Книга удалена: " + book);
                return true;
            }
        }
        return false;
    }


    public void registerReader(Reader reader) {
        readers.add(reader);
        System.out.println("Читатель зарегистрирован: " + reader);
    }


    public boolean unregisterReader(String readerId) {
        for (Reader reader : readers) {
            if (reader.getReader().equals(readerId)) {
                readers.remove(reader);
                System.out.println("Читатель удалён: " + reader);
                return true;
            }
        }
        return false;
    }

    public boolean borrowBook(String isbn, String readerId) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.borrowCopy()) {
                    System.out.println("Книга выдана читателю с ID: " + readerId);
                    return true;
                } else {
                    System.out.println("Книга недоступна.");
                    return false;
                }
            }
        }
        System.out.println("Книга с таким ISBN не найдена.");
        return false;
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.returnCopy();
                System.out.println("Книга возвращена: " + book);
                return;
            }
        }
        System.out.println("Книга с таким ISBN не найдена.");
    }

    public void listBooks() {
        System.out.println("Список книг:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listReaders() {
        System.out.println("Список читателей:");
        for (Reader reader : readers) {
            System.out.println(reader);
        }
    }
}

class Reader {
    private String name;
    private String readerId;

    public Reader(String name, String readerId) {
        this.name = name;
        this.readerId = readerId;
    }

    public String getReader() {
        return readerId;
    }

    @Override
    public String toString() {
        return name + " (ID: " + readerId + ")";
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить книгу");
            System.out.println("2 - Удалить книгу");
            System.out.println("3 - Зарегистрировать читателя");
            System.out.println("4 - Удалить читателя");
            System.out.println("5 - Выдать книгу читателю");
            System.out.println("6 - Вернуть книгу");
            System.out.println("7 - Показать все книги");
            System.out.println("8 - Показать всех читателей");
            System.out.println("0 - Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Завершение программы...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите название книги:");
                    String title = scanner.nextLine();
                    System.out.println("Введите автора:");
                    String author = scanner.nextLine();
                    System.out.println("Введите ISBN:");
                    String isbn = scanner.nextLine();
                    System.out.println("Введите количество экземпляров:");
                    int copies = scanner.nextInt();
                    library.addBook(new Book(title, author, isbn, copies));
                    break;

                case 2:
                    System.out.println("Введите ISBN книги для удаления:");
                    isbn = scanner.nextLine();
                    if (!library.removeBook(isbn)) {
                        System.out.println("Книга не найдена.");
                    }
                    break;

                case 3:
                    System.out.println("Введите имя читателя:");
                    String name = scanner.nextLine();
                    System.out.println("Введите идентификатор читателя:");
                    String readerId = scanner.nextLine();
                    library.registerReader(new Reader(name, readerId));
                    break;

                case 4:
                    System.out.println("Введите идентификатор читателя для удаления:");
                    readerId = scanner.nextLine();
                    if (!library.unregisterReader(readerId)) {
                        System.out.println("Читатель не найден.");
                    }
                    break;

                case 5:
                    System.out.println("Введите ISBN книги для выдачи:");
                    isbn = scanner.nextLine();
                    System.out.println("Введите идентификатор читателя:");
                    readerId = scanner.nextLine();
                    library.borrowBook(isbn, readerId);
                    break;

                case 6:
                    System.out.println("Введите ISBN книги для возврата:");
                    isbn = scanner.nextLine();
                    library.returnBook(isbn);
                    break;

                case 7:
                    library.listBooks();
                    break;

                case 8:
                    library.listReaders();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }
}

