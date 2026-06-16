import java.util.ArrayList;
import java.util.List;

/**
 * Represents a registered library member.
 */
public class Member {
    private String memberId;
    private String name;
    private List<Loan> currentLoans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.currentLoans = new ArrayList<>();
    }

    // Getters and Setters
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Loan> getCurrentLoans() {
        return currentLoans;
    }

    public void addLoan(Loan loan) {
        this.currentLoans.add(loan);
    }

    public void removeLoan(Loan loan) {
        this.currentLoans.remove(loan);
    }

    @Override
    public String toString() {
        return "Member [ID=" + memberId + ", Name=" + name + ", Active Loans Count=" + currentLoans.size() + "]";
    }
}