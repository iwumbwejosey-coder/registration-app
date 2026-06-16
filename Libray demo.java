public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        // 1. Create and populate 3 books
        Book b1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        Book b2 = new Book("978-0132350884", "Clean Code", "Robert C. Martin");
        Book b3 = new Book("978-0596009205", "Head First Design Patterns"); // Uses constructor overload 1
        
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // 2. Register 2 members
        Member m1 = new Member("M001", "Alice Nsubuga");
        Member m2 = new Member("M002", "Bob Okello");
        
        library.registerMember(m1);
        library.registerMember(m2);

        // Print initial state
        System.out.println("--- STARTING DEMO: INITIAL STATE ---");
        library.displayLibraryState();

        // 3. Execute successful sequence of operations
        System.out.println("--- TRANSACTION TRIGGER 1 ---");
        library.lendBook("978-0134685991", "M001"); // Alice borrows Effective Java

        System.out.println("\n--- TRANSACTION TRIGGER 2 ---");
        library.lendBook("978-0132350884", "M002"); // Bob borrows Clean Code

        // 4. Execute operation that is explicitly REJECTED (Business Rule Violation)
        System.out.println("\n--- TRANSACTION TRIGGER 3 (EXPECTED TO FAIL) ---");
        library.lendBook("978-0134685991", "M002"); // Bob tries to borrow Effective Java while Alice has it

        // Print state after lending actions
        library.displayLibraryState();

        // 5. Return process
        System.out.println("--- TRANSACTION TRIGGER 4 ---");
        library.returnBook("978-0134685991", "M001"); // Alice returns Effective Java

        // Final state verification
        System.out.println("\n--- FINAL STATE AFTER RETURN ---");
        library.displayLibraryState();
    }
}