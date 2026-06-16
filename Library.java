import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of books, members, and active loan policies.
 */
public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * Enforces the business rule: A book can be on at most one active loan.
     */
    public boolean lendBook(String isbn, String memberId) {
        Book targetBook = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                targetBook = b;
                break;
            }
        }

        Member targetMember = null;
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                targetMember = m;
                break;
            }
        }

        if (targetBook == null || targetMember == null) {
            System.out.println(">>> Error: Book or Member not found in system.");
            return false;
        }

        // Business Rule Enforcement
        if (!targetBook.isAvailable()) {
            System.out.println(">>> Transaction Rejected: \"" + targetBook.getTitle() + "\" is already out on loan!");
            return false;
        }

        // Create loan (Due in 14 days)
        LocalDate today = LocalDate.now();
        LocalDate due = today.plusDays(14);
        Loan loan = new Loan(targetMember, targetBook, today, due);

        targetBook.setAvailable(false);
        targetMember.addLoan(loan);
        System.out.println(">>> Success: \"" + targetBook.getTitle() + "\" successfully lent to " + targetMember.getName() + ".");
        return true;
    }

    public boolean returnBook(String isbn, String memberId) {
        Book targetBook = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                targetBook = b;
                break;
            }
        }

        Member targetMember = null;
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                targetMember = m;
                break;
            }
        }

        if (targetBook == null || targetMember == null) {
            System.out.println(">>> Error: Invalid return details.");
            return false;
        }

        Loan targetLoan = null;
        for (Loan loan : targetMember.getCurrentLoans()) {
            if (loan.getBook().getIsbn().equals(isbn)) {
                targetLoan = loan;
                break;
            }
        }

        if (targetLoan != null) {
            targetBook.setAvailable(true);
            targetMember.removeLoan(targetLoan);
            System.out.println(">>> Success: \"" + targetBook.getTitle() + "\" has been returned by " + targetMember.getName() + ".");
            return true;
        }

        System.out.println(">>> Error: No active record found matching this user and book.");
        return false;
    }

    public Book searchBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public void displayLibraryState() {
        System.out.println("\n========= CURRENT LIBRARY STATE =========");
        System.out.println("--- Books Inventory ---");
        for (Book b : books) {
            System.out.println("  " + b);
        }
        System.out.println("--- Registered Members ---");
        for (Member m : members) {
            System.out.println("  " + m);
            if (!m.getCurrentLoans().isEmpty()) {
                System.out.println("    Current Loans Breakdown:");
                for (Loan l : m.getCurrentLoans()) {
                    System.out.println("      * " + l);
                }
            }
        }
        System.out.println("=========================================\n");
    }
}