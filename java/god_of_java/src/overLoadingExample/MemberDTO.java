package overLoadingExample;

public class MemberDTO {
    public String name;
    public String email;
    public String phone;

    public MemberDTO() {

    }

    public MemberDTO(String name) {
        this.name = name;
    }

    public MemberDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MemberDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public static void main(String[] args) {
        MemberDTO mem1 = new MemberDTO();
        MemberDTO mem2 = new MemberDTO("tomas");
        MemberDTO mem3 = new MemberDTO("tomas","tomas@gmail.com");
        System.out.println(mem1.name);
        System.out.println(mem2.name);
        System.out.println(mem3.email);


    }
}
