package in.ratansgh;

public class Member {

    private String memberID;
    private String name;

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberID='" + memberID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
