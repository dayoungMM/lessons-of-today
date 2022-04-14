package polymorphism.member;

public class MemberManagerImpl implements MemberManager{
    @Override
    public boolean addMember(MemberDTO member) {
        System.out.println("add Member");
        return false;
    }
}
