package java7;

public class SwitchExample {
    public static void main(String[] args) {
        SwitchExample switchExample = new SwitchExample();
        System.out.println(switchExample.salaryIncreaseAmount("Engineer"));
    }

    public double salaryIncreaseAmount(String employeeLevel) {
        switch(employeeLevel) {
            case "CEO":
                return 10.0;
            case "Manager":
                return 15.0;
            case "Engineer":
            case "Developer":
                return 100.0;
        }
        return 0.0;
    }
}
